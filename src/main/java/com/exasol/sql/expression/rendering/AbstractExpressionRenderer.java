package com.exasol.sql.expression.rendering;

import java.util.ArrayDeque;
import java.util.Deque;

import com.exasol.sql.expression.*;
import com.exasol.sql.rendering.StringRendererConfig;
import com.exasol.util.QuotesApplier;

/**
 * Common base class for expression renderers.
 */
public abstract class AbstractExpressionRenderer {
    protected final StringRendererConfig config;
    protected final StringBuilder builder = new StringBuilder();
    protected final Deque<String> connectorDeque = new ArrayDeque<>();
    private final QuotesApplier quotesApplier;
    private ValueExpression lastVisited;

    public AbstractExpressionRenderer(final StringRendererConfig config) {
        this.config = config;
        this.lastVisited = null;
        this.quotesApplier = new QuotesApplier(config);
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
        if (!this.connectorDeque.isEmpty()) {
            appendKeyword(this.connectorDeque.peek());
        }
    }

    protected void appendBooleanLiteral(final BooleanLiteral literal) {
        this.builder.append(this.config.useLowerCase() ? literal.toString().toLowerCase() : literal.toString());
    }

    protected void startParenthesis() {
        this.builder.append("(");
    }

    protected void endParenthesis() {
        this.builder.append(")");
    }

    protected void append(final String string) {
        this.builder.append(string);
    }

    protected void appendAutoQuoted(final String identifier) {
        final String autoQuotedIdentifier = this.quotesApplier.getAutoQuoted(identifier);
        append(autoQuotedIdentifier);
    }

    protected void appendCommaWhenNeeded(final ValueExpression valueExpression) {
        if (this.lastVisited != null && !(valueExpression.getParent() instanceof BinaryArithmeticExpression)) {
            if (this.lastVisited.isSibling(valueExpression) || (valueExpression.getParent() != this.lastVisited
                    && this.lastVisited.getClass().equals(valueExpression.getClass()))) {
                append(", ");
            }
        }
    }

    protected void setLastVisited(final ValueExpression valueExpression) {
        this.lastVisited = valueExpression;
    }

    /**
     * Render expression to a string
     *
     * @return rendered string
     */
    public String render() {
        return this.builder.toString();
    }
}