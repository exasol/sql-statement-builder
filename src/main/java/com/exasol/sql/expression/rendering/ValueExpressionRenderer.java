package com.exasol.sql.expression.rendering;

import com.exasol.sql.UnnamedPlaceholder;
import com.exasol.sql.expression.*;
import com.exasol.sql.expression.function.Function;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * Renderer for common value expressions
 */
public class ValueExpressionRenderer extends AbstractExpressionRenderer implements ValueExpressionVisitor {
    public ValueExpressionRenderer(final StringRendererConfig config) {
        super(config);
    }

    @Override
    public void visit(final StringLiteral literal) {
        appendCommaWhenNeeded(literal);
        append("'");
        append(literal.toString());
        append("'");
        setLastVisited(literal);
    }

    @Override
    public void visit(final IntegerLiteral literal) {
        appendCommaWhenNeeded(literal);
        append(literal.toString());
        setLastVisited(literal);
    }

    @Override
    public void visit(final LongLiteral literal) {
        appendCommaWhenNeeded(literal);
        append(literal.toString());
        setLastVisited(literal);
    }

    @Override
    public void visit(final DoubleLiteral literal) {
        appendCommaWhenNeeded(literal);
        append(literal.toString());
        setLastVisited(literal);
    }

    @Override
    public void visit(final FloatLiteral literal) {
        appendCommaWhenNeeded(literal);
        append(literal.toString());
        setLastVisited(literal);
    }

    @Override
    public void visit(final BooleanLiteral literal) {
        appendCommaWhenNeeded(literal);
        append(literal.toString());
        setLastVisited(literal);
    }

    @Override
    public void visit(final ColumnReference columnReference) {
        appendCommaWhenNeeded(columnReference);
        appendAutoQuoted(columnReference.toString());
        setLastVisited(columnReference);
    }

    @Override
    public void visit(final UnnamedPlaceholder unnamedPlaceholder) {
        append("?");
        setLastVisited(unnamedPlaceholder);
    }

    @Override
    public void visit(final DefaultValue defaultValue) {
        appendKeyword("DEFAULT");
        setLastVisited(defaultValue);
    }

    @Override
    public void visit(final Function function) {
        appendCommaWhenNeeded(function);
        appendKeyword(function.getFunctionName());
        if (function.hasParenthesis()) {
            startParenthesis();
        }
        setLastVisited(function);
    }

    @Override
    public void leave(final Function function) {
        if (function.hasParenthesis()) {
            endParenthesis();
        }
        setLastVisited(function);
    }

    @Override
    public void visit(final BinaryArithmeticExpression expression) {
        startParenthesis();
        appendOperand(expression.getLeft());
        append(expression.getStringOperatorRepresentation());
        appendOperand(expression.getRight());
        endParenthesis();
        setLastVisited(expression);
    }

    protected void appendOperand(final ValueExpression operand) {
        final ValueExpressionRenderer expressionRenderer = new ValueExpressionRenderer(this.config);
        operand.accept(expressionRenderer);
        this.builder.append(expressionRenderer.render());
    }

    @Override
    public void visit(final NullLiteral nullLiteral) {
        appendCommaWhenNeeded(nullLiteral);
        appendKeyword("NULL");
        setLastVisited(nullLiteral);
    }

    @Override
    public void visit(final BooleanExpression booleanExpression) {
        final BooleanExpressionRenderer expressionRenderer = new BooleanExpressionRenderer(this.config);
        booleanExpression.accept(expressionRenderer);
        append(expressionRenderer.render());
    }
}