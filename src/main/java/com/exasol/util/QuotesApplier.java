package com.exasol.util;

import com.exasol.sql.rendering.StringRendererConfig;

/**
 * This class handles quotes in a string identifier.
 */
public class QuotesApplier {
    private final StringRendererConfig config;

    /**
     * Create a new instance of a {@link QuotesApplier}.
     *
     * @param config renderer configuration
     */
    public QuotesApplier(final StringRendererConfig config) {
        this.config = config;
    }

    /**
     * Applies quotes if it ie required in the configuration. Otherwise returns unquoted identifier back.
     * 
     * @param identifier a string identifier
     * @return identifier with quotation rules applied
     */
    // [impl->dsn~rendering.add-double-quotes-for-schema-table-and-column-identifiers~1]
    public String getAutoQuoted(final String identifier) {
        if (this.config.useQuotes()) {
            return appendQuoted(identifier);
        } else {
            return identifier;
        }
    }

    private String appendQuoted(final String identifier) {
        final StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        for (final String part : identifier.split("\\.")) {
            if (!first) {
                stringBuilder.append(".");
            }
            quoteIdentifierPart(part, stringBuilder);
            first = false;
        }
        return stringBuilder.toString();
    }

    private void quoteIdentifierPart(final String part, final StringBuilder stringBuilder) {
        if ("*".equals(part)) {
            stringBuilder.append("*");
        } else {
            if (!part.startsWith("\"")) {
                stringBuilder.append("\"");
            }
            stringBuilder.append(part);
            if (!part.endsWith("\"")) {
                stringBuilder.append("\"");
            }
        }
    }
}