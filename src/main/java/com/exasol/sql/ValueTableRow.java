package com.exasol.sql;

import java.util.*;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.literal.*;

/**
 * This class represents a row in a {@link ValueTable}.
 */
// [impl->dsn~value-table~1]
public class ValueTableRow extends AbstractFragment {
    private final List<ValueExpression> expressions;

    /**
     * Create a value table row from a list of expressions.
     *
     * @param root        root node of the SQL statement
     * @param expressions value expressions
     */
    public ValueTableRow(final Fragment root, final ValueExpression... expressions) {
        super(root);
        this.expressions = Arrays.asList(expressions);
    }

    /**
     * Create a value table row from a list of string literals.
     *
     * @param root   root node of the SQL statement
     * @param values sting literals
     */
    public ValueTableRow(final Fragment root, final String... values) {
        super(root);
        this.expressions = new ArrayList<>(values.length);
        for (final String value : values) {
            this.expressions.add(StringLiteral.of(value));
        }
    }

    private ValueTableRow(final Builder builder) {
        super(builder.root);
        this.expressions = builder.expressions;
    }

    /**
     * Get the list of expressions the row consists of.
     *
     * @return list of expressions
     */
    public List<ValueExpression> getExpressions() {
        return this.expressions;
    }

    /**
     * Accept a visitor.
     * 
     * @param visitor to accept.
     */
    public void accept(final ValueTableVisitor visitor) {
        visitor.visit(this);
        visitor.leave(this);
    }

    /**
     * Get a {@link Builder} for a {@link ValueTableRow}.
     *
     * @param root root fragment of the SQL statement
     *
     * @return new builder instance
     */
    public static Builder builder(final Fragment root) {
        return new Builder(root);
    }

    /**
     * Builder for {@link ValueTableRow}s
     */
    public static class Builder {
        private final Fragment root;
        private final List<ValueExpression> expressions = new ArrayList<>();

        /**
         * Create a new builder for a value table row.
         *
         * @param root root fragment of the SQL statement
         */
        public Builder(final Fragment root) {
            this.root = root;
        }

        /**
         * Add one or more string literals to the row.
         *
         * @param values strings to be added
         * @return {@code this} for fluent programming
         */
        public Builder add(final String... values) {
            for (final String value : values) {
                this.expressions.add(StringLiteral.of(value));
            }
            return this;
        }

        /**
         * Add one or more char literals to the row.
         *
         * @param values chars to be added
         * @return {@code this} for fluent programming
         */
        public Builder add(final char... values) {
            for (final char value : values) {
                this.expressions.add(StringLiteral.of(value));
            }
            return this;
        }

        /**
         * Add one or more integer literals to the row.
         *
         * @param values integers to be added
         * @return {@code this} for fluent programming
         */
        public Builder add(final int... values) {
            for (final int value : values) {
                this.expressions.add(IntegerLiteral.of(value));
            }
            return this;
        }

        /**
         * Add one or more long literals to the row.
         *
         * @param values longs to be added
         * @return {@code this} for fluent programming
         */
        public Builder add(final long... values) {
            for (final long value : values) {
                this.expressions.add(LongLiteral.of(value));
            }
            return this;
        }

        /**
         * Add one or more double literals to the row.
         *
         * @param values doubles to be added
         * @return {@code this} for fluent programming
         */
        public Builder add(final double... values) {
            for (final double value : values) {
                this.expressions.add(DoubleLiteral.of(value));
            }
            return this;
        }

        /**
         * Add one or more float literals to the row.
         *
         * @param values floats to be added
         * @return {@code this} for fluent programming
         */
        public Builder add(final float... values) {
            for (final float value : values) {
                this.expressions.add(FloatLiteral.of(value));
            }
            return this;
        }

        /**
         * Add one or more boolean literals to the row.
         *
         * @param values booleans to be added
         * @return {@code this} for fluent programming
         */
        public Builder add(final boolean... values) {
            for (final boolean value : values) {
                this.expressions.add(BooleanLiteral.of(value));
            }
            return this;
        }

        /**
         * Add an {@link UnnamedPlaceholder} to the row.
         *
         * @return {@code this} for fluent programming
         */
        public Builder addPlaceholder() {
            this.expressions.add(new UnnamedPlaceholder());
            return this;
        }

        /**
         * Add a list of expressions to the {@link ValueTableRow}.
         *
         * @param expressions expressions to be added
         * @return {@code this} for fluent programming
         */
        public Builder add(final List<ValueExpression> expressions) {
            this.expressions.addAll(expressions);
            return this;
        }

        /**
         * Build a new {@link ValueTableRow}.
         *
         * @return new {@link ValueTableRow}
         */
        public ValueTableRow build() {
            return new ValueTableRow(this);
        }
    }
}
