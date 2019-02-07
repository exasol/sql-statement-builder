package com.exasol.sql.expression.rendering;

import com.exasol.sql.expression.*;
import com.exasol.sql.rendering.StringRendererConfig;

public class BooleanExpressionRenderer extends AbstractExpressionRenderer implements BooleanExpressionVisitor {
    public BooleanExpressionRenderer(final StringRendererConfig config) {
        super(config);
    }

    public BooleanExpressionRenderer() {
        this(StringRendererConfig.builder().build());
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
    public void visit(final BooleanLiteral literal) {
        connect(literal);
        appendBooleanLiteral(literal);
    }

    @Override
    public void leave(final BooleanLiteral literal) {
        // intentionally empty
    }

    @Override
    public void visit(final Comparison comparison) {
        connect(comparison);
        if (!comparison.isRoot()) {
            startParenthesis();
        }
        appendOperand(comparison.getLeftOperand());
        this.builder.append(" ");
        this.builder.append(comparison.getOperator().toString());
        this.builder.append(" ");
        appendOperand(comparison.getRightOperand());
    }

    protected void appendOperand(final StringLiteral leftOperand) {
        final ValueExpressionRenderer leftExpressionRenderer = new ValueExpressionRenderer(this.config);
        leftOperand.accept(leftExpressionRenderer);
        this.builder.append(leftExpressionRenderer.render());
    }

    @Override
    public void leave(final Comparison comparison) {
        if (!comparison.isRoot()) {
            endParenthesis(comparison);
        }
    }
}