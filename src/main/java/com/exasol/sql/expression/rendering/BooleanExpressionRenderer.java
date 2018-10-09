package com.exasol.sql.expression.rendering;

import com.exasol.sql.expression.*;
import com.exasol.sql.rendering.StringRendererConfig;

public class BooleanExpressionRenderer extends AbstractExpressionRenderer implements BooleanExpressionVisitor {
    public BooleanExpressionRenderer(final StringRendererConfig config) {
        super(config);
    }

    public BooleanExpressionRenderer() {
        this(new StringRendererConfig.Builder().build());
    }

    @Override
    public void visit(final Not not) {
        connect(not);
        appendKeyword("NOT");
        startParenthesis();
    }

    @Override
    public void leave(final Not not) {
        endParenthesis(not);
    }

    @Override
    public void visit(final And and) {
        connect(and);
        this.connectorStack.push(" AND ");
        if (!and.isRoot()) {
            startParenthesis();
        }
    }

    @Override
    public void leave(final And and) {
        if (!and.isRoot()) {
            endParenthesis(and);
        }
        this.connectorStack.pop();
    }

    @Override
    public void visit(final Or or) {
        connect(or);
        this.connectorStack.push(" OR ");
        if (!or.isRoot()) {
            startParenthesis();
        }
    }

    @Override
    public void leave(final Or or) {
        if (!or.isRoot()) {
            endParenthesis(or);
        }
        this.connectorStack.pop();
    }

    @Override
    public void visit(final Literal literal) {
        connect(literal);
        appendLiteral(literal.toString());
    }

    @Override
    public void leave(final Literal literal) {
        // intentionally empty
    }

    @Override
    public void visit(final Comparison comparison) {
        connect(comparison);
        if (!comparison.isRoot()) {
            startParenthesis();
        }
        comparison.getLeftOperand().accept(this);
        this.builder.append(" ");
        this.builder.append(comparison.getOperator().toString());
        this.builder.append(" ");
        comparison.getRightOperand().accept(this);
    }

    @Override
    public void leave(final Comparison comparison) {
        if (!comparison.isRoot()) {
            endParenthesis(comparison);
        }
    }
}