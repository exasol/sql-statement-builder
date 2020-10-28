package com.exasol.sql.expression.rendering;

import com.exasol.sql.expression.literal.BooleanLiteral;
import com.exasol.sql.rendering.StringRendererConfig;
import com.exasol.util.QuotesApplier;

/**
 * Common base class for expression renderers.
 */
public abstract class AbstractExpressionRenderer {
    protected final StringRendererConfig config;
    protected final StringBuilder builder = new StringBuilder();
    private final QuotesApplier quotesApplier;

    public AbstractExpressionRenderer(final StringRendererConfig config) {
        this.config = config;
        this.quotesApplier = new QuotesApplier(config);
    }

    protected void appendKeyword(final String keyword) {
        this.builder.append(this.config.useLowerCase() ? keyword.toLowerCase() : keyword);
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

    /**
     * Render expression to a string
     *
     * @return rendered string
     */
    public String render() {
        return this.builder.toString();
    }
}