package com.exasol.sql.expression.rendering;

import com.exasol.datatype.type.DataType;
import com.exasol.sql.ColumnsDefinition;
import com.exasol.sql.UnnamedPlaceholder;
import com.exasol.sql.expression.*;
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
public class ValueExpressionRenderer extends AbstractExpressionRenderer
        implements ValueExpressionVisitor, LiteralVisitor, FunctionVisitor {
    public ValueExpressionRenderer(final StringRendererConfig config) {
        super(config);
    }

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
        final BooleanExpressionRenderer expressionRenderer = new BooleanExpressionRenderer(this.config);
        booleanExpression.accept(expressionRenderer);
        append(expressionRenderer.render());
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
    public void visit(final BooleanLiteral literal) {
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
        this.visit(function.getValueExpressions().toArray(ValueExpression[]::new));
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
        castFunction.getValue().accept(this);
        appendKeyword(" AS");
        final DataType type = castFunction.getType();
        final ColumnsDefinitionRenderer columnsDefinitionRenderer = getColumnsDefinitionRenderer();
        type.accept(columnsDefinitionRenderer);
        append(columnsDefinitionRenderer.render());
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
        appendOperand(expression.getLeft());
        append(expression.getStringOperatorRepresentation());
        appendOperand(expression.getRight());
        endParenthesis();
    }

    private void appendOperand(final ValueExpression operand) {
        operand.accept(this);
    }

    private ColumnsDefinitionRenderer getColumnsDefinitionRenderer() {
        return new ColumnsDefinitionRenderer(this.config);
    }
}