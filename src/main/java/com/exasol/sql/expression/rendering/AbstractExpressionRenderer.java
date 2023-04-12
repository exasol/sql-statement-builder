package com.exasol.sql.expression.rendering;

import com.exasol.sql.expression.literal.BooleanLiteral;
import com.exasol.sql.rendering.StringRendererConfig;
import com.exasol.util.QuotesApplier;

/**
 * Common base class for expression renderers.
 */
public abstract class AbstractExpressionRenderer {
    /** Configuration that controls string rendering options */
    protected final StringRendererConfig config;
    /** Builder that holds the fragments of the rendered string */
    protected final StringBuilder builder = new StringBuilder();
    private final QuotesApplier quotesApplier;

    /**
     * Create a new instance of a {@link AbstractExpressionRenderer}.
     *
     * @param config configuration that controls string rendering options
     */
    protected AbstractExpressionRenderer(final StringRendererConfig config) {
        this.config = config;
        this.quotesApplier = new QuotesApplier(config);
    }

    /**
     * Append a SQL keyword (like {@code SELECT}).
     *
     * @param keyword SQL keyword
     */
    protected void appendKeyword(final String keyword) {
        this.builder.append(this.config.useLowerCase() ? keyword.toLowerCase() : keyword);
    }

    /**
     * Append a boolean literal (i.e. {@code true} of {@code false}).
     *
     * @param literal boolean literal
     */
    protected void appendBooleanLiteral(final BooleanLiteral literal) {
        this.builder.append(this.config.useLowerCase() ? literal.toString().toLowerCase() : literal.toString());
    }

    /**
     * Start a parenthesis.
     */
    protected void startParenthesis() {
        this.builder.append("(");
    }

    /**
     * End a parenthesis.
     */
    protected void endParenthesis() {
        this.builder.append(")");
    }

    /**
     * Append a string.
     *
     * @param string string to append
     */
    protected void append(final String string) {
        this.builder.append(string);
    }

    /**
     * Append a SQL identifier with the right form of quotes.
     *
     * @param identifier SQL identifier to be appended
     */
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
