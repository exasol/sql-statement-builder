package com.exasol.sql.expression.rendering;

import java.util.List;

import com.exasol.datatype.type.DataType;
import com.exasol.sql.ColumnsDefinition;
import com.exasol.sql.UnnamedPlaceholder;
import com.exasol.sql.expression.*;
import com.exasol.sql.expression.comparison.Comparison;
import com.exasol.sql.expression.comparison.ComparisonVisitor;
import com.exasol.sql.expression.comparison.LikeComparison;
import com.exasol.sql.expression.comparison.SimpleComparison;
import com.exasol.sql.expression.function.AbstractFunction;
import com.exasol.sql.expression.function.Function;
import com.exasol.sql.expression.function.FunctionVisitor;
import com.exasol.sql.expression.function.exasol.CastExasolFunction;
import com.exasol.sql.expression.function.exasol.ExasolFunction;
import com.exasol.sql.expression.function.exasol.ExasolUdf;
import com.exasol.sql.expression.literal.*;
import com.exasol.sql.rendering.ColumnsDefinitionRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * Renderer for common value expressions.
 */
public class ValueExpressionRenderer extends AbstractExpressionRenderer implements BooleanExpressionVisitor,
        ComparisonVisitor, ValueExpressionVisitor, LiteralVisitor, FunctionVisitor {
    int nestedLevel = 0;

    /**
     * Create a new instance of {@link ValueExpressionRenderer}.
     * 
     * @param config render configuration
     */
    public ValueExpressionRenderer(final StringRendererConfig config) {
        super(config);
    }

    @Override
    public void visit(final Not not) {
        appendKeyword("NOT");
        startParenthesis();
        not.getOperand().accept((BooleanExpressionVisitor) this);
        endParenthesis();
    }

    @Override
    public void visit(final And and) {
        startParenthesisIfNested();
        append(" AND ", and.getOperands());
        endParenthesisIfNested();
    }

    private void append(final String separator, final List<BooleanExpression> expressions) {
        boolean isFirst = true;
        for (final BooleanExpression expression : expressions) {
            if (!isFirst) {
                appendKeyword(separator);
            }
            isFirst = false;
            expression.accept((BooleanExpressionVisitor) this);
        }
    }

    @Override
    public void visit(final Or or) {
        startParenthesisIfNested();
        append(" OR ", or.getOperands());
        endParenthesisIfNested();
    }

    @Override
    public void visit(final BooleanLiteral literal) {
        appendBooleanLiteral(literal);
    }

    @Override
    public void visit(final Comparison comparison) {
        comparison.accept((ComparisonVisitor) this);
    }

    /*
     * Comparison renderer
     */
    @Override
    public void visit(final SimpleComparison simpleComparison) {
        openComparison(simpleComparison);
        closeComparison();
    }

    @Override
    public void visit(final LikeComparison like) {
        openComparison(like);
        if (like.hasEscape()) {
            this.builder.append(" ESCAPE ");
            this.builder.append("'");
            this.builder.append(like.getEscape());
            this.builder.append("'");
        }
        closeComparison();
    }

    private void openComparison(final Comparison comparison) {
        startParenthesisIfNested();
        appendOperand(comparison.getLeftOperand());
        this.builder.append(" ");
        this.builder.append(comparison.getOperator().toString());
        this.builder.append(" ");

        appendOperand(comparison.getRightOperand());
    }

    private void closeComparison() {
        endParenthesisIfNested();
    }

    /* Value expression visitor */

    public void visit(final ValueExpression... valueExpression) {
        boolean isFirst = true;
        for (final ValueExpression parameter : valueExpression) {
            if (!isFirst) {
                append(", ");
            }
            isFirst = false;
            parameter.accept(this);
        }
    }

    @Override
    public void visit(final ColumnReference columnReference) {
        appendAutoQuoted(columnReference.toString());
    }

    @Override
    public void visit(final Literal literal) {
        literal.accept((LiteralVisitor) this);
    }

    @Override
    public void visit(final Function function) {
        function.accept((FunctionVisitor) this);
    }

    @Override
    public void visit(final BooleanExpression booleanExpression) {
        booleanExpression.accept((BooleanExpressionVisitor) this);
    }

    @Override
    public void visit(final UnnamedPlaceholder unnamedPlaceholder) {
        append("?");
    }

    @Override
    public void visit(final DefaultValue defaultValue) {
        appendKeyword("DEFAULT");
    }

    /** Literal visitor **/
    @Override
    public void visit(final StringLiteral literal) {
        append("'");
        append(literal.toString());
        append("'");
    }

    @Override
    public void visit(final IntegerLiteral literal) {
        append(literal.toString());
    }

    @Override
    public void visit(final LongLiteral literal) {
        append(literal.toString());
    }

    @Override
    public void visit(final DoubleLiteral literal) {
        append(literal.toString());
    }

    @Override
    public void visit(final FloatLiteral literal) {
        append(literal.toString());
    }

    @Override
    public void visit(final BigDecimalLiteral literal) {
        append(literal.toString());
    }

    @Override
    public void visit(final NullLiteral nullLiteral) {
        appendKeyword("NULL");
    }

    /** Function visitor */

    @Override
    public void visit(final ExasolFunction function) {
        renderFunction(function);
    }

    private void renderFunction(final AbstractFunction function) {
        appendKeyword(function.getFunctionName());
        if (function.hasParenthesis()) {
            startParenthesis();
        }
        ++this.nestedLevel;
        this.visit(function.getParameters().toArray(ValueExpression[]::new));
        --this.nestedLevel;
        if (function.hasParenthesis()) {
            endParenthesis();
        }
    }

    @Override
    public void visit(final ExasolUdf function) {
        renderFunction(function);
        appendEmitsWhenNecessary(function);
    }

    @Override
    public void visit(final CastExasolFunction castFunction) {
        appendKeyword("CAST");
        startParenthesis();
        ++this.nestedLevel;
        castFunction.getValue().accept(this);
        appendKeyword(" AS");
        final DataType type = castFunction.getType();
        final ColumnsDefinitionRenderer columnsDefinitionRenderer = getColumnsDefinitionRenderer();
        type.accept(columnsDefinitionRenderer);
        append(columnsDefinitionRenderer.render());
        --this.nestedLevel;
        endParenthesis();
    }

    @SuppressWarnings("squid:S3655")
    // We do check if optional is present using a method hasEmitsColumnsDefinition().
    private void appendEmitsWhenNecessary(final ExasolUdf function) {
        if (function.hasEmitsColumnsDefinition()) {
            appendKeyword(" EMITS");
            append(" ");
            final ColumnsDefinition columnsDefinition = function.getEmitsColumnsDefinition().get();
            final ColumnsDefinitionRenderer columnsDefinitionRenderer = getColumnsDefinitionRenderer();
            columnsDefinition.accept(columnsDefinitionRenderer);
            this.builder.append(columnsDefinitionRenderer.render());
        }
    }

    @Override
    public void visit(final BinaryArithmeticExpression expression) {
        startParenthesis();
        ++this.nestedLevel;
        appendOperand(expression.getLeft());
        append(expression.getStringOperatorRepresentation());
        appendOperand(expression.getRight());
        endParenthesis();
        --this.nestedLevel;
    }

    private void appendOperand(final ValueExpression operand) {
        operand.accept(this);
    }

    private ColumnsDefinitionRenderer getColumnsDefinitionRenderer() {
        return new ColumnsDefinitionRenderer(this.config);
    }

    private void startParenthesisIfNested() {
        if (this.nestedLevel > 0) {
            startParenthesis();
        }
        ++this.nestedLevel;
    }

    private void endParenthesisIfNested() {
        --this.nestedLevel;
        if (this.nestedLevel > 0) {
            endParenthesis();
        }
    }
}