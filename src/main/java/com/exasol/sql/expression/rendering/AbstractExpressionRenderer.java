package com.exasol.sql.expression.rendering;

import java.util.Stack;

import com.exasol.sql.expression.BooleanExpression;
import com.exasol.sql.expression.BooleanLiteral;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * Common base class for expression renderers
 */
public class AbstractExpressionRenderer {
    protected final StringRendererConfig config;
    protected final StringBuilder builder = new StringBuilder();
    protected final Stack<String> connectorStack = new Stack<>();

    public AbstractExpressionRenderer(final StringRendererConfig config) {
        this.config = config;
    }

    protected void appendKeyword(final String keyword) {
        this.builder.append(this.config.useLowerCase() ? keyword.toLowerCase() : keyword);
    }

    protected void connect(final BooleanExpression expression) {
        if (expression.isChild() && !expression.isFirstSibling()) {
            appendConnector();
        }
    }

    private void appendConnector() {
        if (!this.connectorStack.isEmpty()) {
            appendKeyword(this.connectorStack.peek());
        }
    }

    protected void appendStringLiteral(final String value) {
        this.builder.append(value);
    }

    protected void appendBooleanLiteral(final BooleanLiteral literal) {
        this.builder.append(this.config.useLowerCase() ? literal.toString().toLowerCase() : literal.toString());
    }

    protected void startParenthesis() {
        this.builder.append("(");
    }

    protected void endParenthesis(final BooleanExpression expression) {
        this.builder.append(")");
    }

    /**
     * Render expression to a string
     *
     * @return rendered string
     */
    public String render() {
        return this.builder.toString();
    }

    protected void append(final String string) {
        this.builder.append(string);
    }
}