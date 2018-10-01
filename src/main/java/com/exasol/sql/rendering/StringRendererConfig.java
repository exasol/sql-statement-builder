package com.exasol.sql.rendering;

import com.exasol.sql.StatementFactory;

/**
 * This class implements a parameter object containing the configuration options
 * for the {@link StatementFactory}.
 */
public class StringRendererConfig {
    private final boolean lowerCase;

    private StringRendererConfig(final boolean lowerCase) {
        this.lowerCase = lowerCase;
    }

    /**
     * Get whether the statements should be produced in lower case.
     *
     * @return <code>true</code> if statements are produced in lower case
     */
    public boolean produceLowerCase() {
        return this.lowerCase;
    }

    /**
     * Builder for {@link StringRendererConfig}
     */
    public static class Builder {
        private boolean lowerCase = false;

        /**
         * Create a new instance of a {@link StringRendererConfig}
         *
         * @return new instance
         */
        public StringRendererConfig build() {
            return new StringRendererConfig(this.lowerCase);
        }

        /**
         * Define whether the statement should be produced in lower case
         *
         * @param lowerCase set to <code>true</code> if the statement should be produced
         *                  in lower case
         * @return this instance for fluent programming
         */
        public Builder lowerCase(final boolean lowerCase) {
            this.lowerCase = lowerCase;
            return this;
        }
    }
}