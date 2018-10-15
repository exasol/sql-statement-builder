package com.exasol.sql.rendering;

import com.exasol.sql.StatementFactory;

/**
 * This class implements a parameter object containing the configuration options for the {@link StatementFactory}.
 */
public class StringRendererConfig {
    private final boolean lowerCase;
    private final boolean quote;

    private StringRendererConfig(final Builder builder) {
        this.lowerCase = builder.lowerCase;
        this.quote = builder.quote;
    }

    /**
     * Get whether the statements should be produced in lower case.
     *
     * @return <code>true</code> if statements are produced in lower case
     */
    public boolean useLowerCase() {
        return this.lowerCase;
    }

    /**
     * Get whether identifiers should be enclosed in double quotation marks.
     *
     * @return <code>true</code> if should be enclosed in quotes
     */
    public boolean useQuotes() {
        return this.quote;
    }

    /**
     * Create the default configuration.
     *
     * @return default configuration
     */
    public static StringRendererConfig createDefault() {
        return builder().build();
    }

    /**
     * Get a builder for {@link StringRendererConfig}
     *
     * @return builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link StringRendererConfig}
     */
    public static class Builder {
        private boolean lowerCase = false;
        private boolean quote = false;

        private Builder() {
        }

        /**
         * Create a new instance of a {@link StringRendererConfig}
         *
         * @return new instance
         */
        public StringRendererConfig build() {
            return new StringRendererConfig(this);
        }

        /**
         * Define whether the statement should be produced in lower case
         *
         * @param lowerCase set to <code>true</code> if the statement should be produced in lower case
         * @return this instance for fluent programming
         */
        public Builder lowerCase(final boolean lowerCase) {
            this.lowerCase = lowerCase;
            return this;
        }

        /**
         * Define whether schema, table and field identifiers should be enclosed in double quotation marks.
         *
         * @param quote set to <code>true</code> if identifiers should be enclosed in quotes
         * @return this instance for fluent programming
         */
        public Builder quoteIdentifiers(final boolean quote) {
            this.quote = quote;
            return this;
        }
    }
}