package com.exasol.sql.expression.rendering;

import java.util.Arrays;
import java.util.List;

import com.exasol.datatype.type.DataType;
import com.exasol.sql.ColumnsDefinition;
import com.exasol.sql.UnnamedPlaceholder;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.dql.select.rendering.SelectRenderer;
import com.exasol.sql.expression.*;
import com.exasol.sql.expression.comparison.*;
import com.exasol.sql.expression.function.*;
import com.exasol.sql.expression.function.exasol.*;
import com.exasol.sql.expression.function.exasol.AnalyticFunction.Keyword;
import com.exasol.sql.expression.literal.*;
import com.exasol.sql.expression.predicate.*;
import com.exasol.sql.rendering.ColumnsDefinitionRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * Renderer for common value expressions.
 */
public class ValueExpressionRenderer extends AbstractExpressionRenderer implements BooleanExpressionVisitor,
        ComparisonVisitor, FunctionVisitor, LiteralVisitor, PredicateVisitor, ValueExpressionVisitor {
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
            appendKeyword(" ESCAPE ");
            append("'");
            this.builder.append(like.getEscape());
            append("'");
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

    /* Predicate visitor */

    @Override
    public void visit(final Predicate predicate) {
        predicate.accept((PredicateVisitor) this);
    }

    @Override
    public void visit(final IsNullPredicate isNullPredicate) {
        startParenthesisIfNested();
        appendOperand(isNullPredicate.getOperand());
        append(" ");
        append(isNullPredicate.getOperator().toString());
        endParenthesisIfNested();
    }

    @Override
    public void visit(final InPredicate inPredicate) {
        startParenthesisIfNested();
        appendOperand(inPredicate.getExpression());
        append(" ");
        append(inPredicate.getOperator().toString());
        append(" (");
        if (inPredicate.hasSelectQuery()) {
            appendSelect(inPredicate.getSelectQuery());
        } else {
            visit(inPredicate.getOperands());
        }
        append(")");
        endParenthesisIfNested();
    }

    @Override
    public void visit(final ExistsPredicate existsPredicate) {
        startParenthesisIfNested();
        append(existsPredicate.getOperator().toString());
        append(" (");
        appendSelect(existsPredicate.getSelectQuery());
        append(")");
        endParenthesisIfNested();
    }

    @Override
    public void visit(final BetweenPredicate betweenPredicate) {
        startParenthesisIfNested();
        appendOperand(betweenPredicate.getExpression());
        append(" ");
        append(betweenPredicate.getOperator().toString());
        append(" ");
        appendOperand(betweenPredicate.getStartExpression());
        appendKeyword(" AND ");
        appendOperand(betweenPredicate.getEndExpression());
        endParenthesisIfNested();
    }

    private void appendSelect(final Select select) {
        final SelectRenderer selectRenderer = SelectRenderer.create(this.config);
        select.accept(selectRenderer);
        append(selectRenderer.render());
    }

    /**
     * Visit expressions.
     *
     * @param valueExpressions value expressions to visit
     */
    public void visit(final List<ValueExpression> valueExpressions) {
        boolean isFirst = true;
        for (final ValueExpression parameter : valueExpressions) {
            if (!isFirst) {
                append(", ");
            }
            isFirst = false;
            parameter.accept(this);
        }
    }

    /**
     * Visit a value expression.
     *
     * @param valueExpressions value expression to visit
     */
    public void visit(final ValueExpression... valueExpressions) {
        visit(Arrays.asList(valueExpressions));
    }

    @Override
    public void visit(final ColumnReference columnReference) {
        if (columnReference.hasSubSelect()) {
            append(" (");
            appendSelect(columnReference.getSubSelect());
            append(")");
        } else {
            appendAutoQuoted(columnReference.toString());
        }
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
        renderFunction(function, null);
    }

    private void renderFunction(final AbstractFunction function, final Keyword keyword) {
        appendKeyword(function.getFunctionName());
        if (function.hasParenthesis()) {
            startParenthesis();
        }
        ++this.nestedLevel;
        if (keyword != null) {
            this.appendKeyword(keyword.name());
        }
        this.visit(function.getParameters().toArray(ValueExpression[]::new));
        --this.nestedLevel;
        if (function.hasParenthesis()) {
            endParenthesis();
        }
    }

    @Override
    public void visit(final ExasolUdf function) {
        renderFunction(function, null);
        appendEmitsWhenNecessary(function);
    }

    @Override
    public void visit(final AnalyticFunction analyticFunction) {
        renderFunction(analyticFunction, analyticFunction.getKeyword());
        if (analyticFunction.getOverClause() != null) {
            renderOverClause(analyticFunction.getOverClause());
        }
    }

    private void renderOverClause(final OverClause overClause) {
        final OverClauseRenderer overClauseRenderer = new OverClauseRenderer(this.config);
        overClauseRenderer.visit(overClause);
        this.append(overClauseRenderer.render());
    }

    @Override
    public void visit(final CastExasolFunction castFunction) {
        appendKeyword("CAST");
        startParenthesis();
        ++this.nestedLevel;
        castFunction.getValue().accept(this);
        appendKeyword(" AS ");
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
        --this.nestedLevel;
        endParenthesis();
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
