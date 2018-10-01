package com.exasol.sql.expression.rendering;

import java.util.Stack;

import com.exasol.sql.expression.*;
import com.exasol.sql.rendering.StringRendererConfig;

public class BooleanExpressionRenderer implements BooleanExpressionVisitor {
    private final StringRendererConfig config;
    private final StringBuilder builder = new StringBuilder();
    private final Stack<String> connectorStack = new Stack<>();

    public BooleanExpressionRenderer(final StringRendererConfig config) {
        this.config = config;
    }

    public BooleanExpressionRenderer() {
        this.config = new StringRendererConfig.Builder().build();
    }

    private void appendKeyword(final String keyword) {
        this.builder.append(this.config.produceLowerCase() ? keyword.toLowerCase() : keyword);
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

    private void connect(final BooleanExpression expression) {
        if (expression.isChild() && !expression.isFirstSibling()) {
            appendConnector();
        }
    }

    @Override
    public void leave(final Literal literal) {
        // intentionally empty
    }

    private void appendConnector() {
        if (!this.connectorStack.isEmpty()) {
            appendKeyword(this.connectorStack.peek());
        }
    }

    private void appendLiteral(final String string) {
        this.builder.append(string);
    }

    private void startParenthesis() {
        this.builder.append("(");
    }

    private void endParenthesis(final BooleanExpression expression) {
        this.builder.append(")");
    }

    public String render() {
        return this.builder.toString();
    }
}