package com.exasol.sql;

import java.util.*;

import com.exasol.sql.expression.*;

/**
 * This class represents a row in a {@link ValueTable}.
 */
// [impl->dsn~value-table~1]
public class ValueTableRow extends AbstractFragment {
    private final List<ValueExpression> expressions;

    /**
     * Create a value table row from a list of expressions
     *
     * @param root        root node of the SQL statement
     * @param expressions value expressions
     */
    public ValueTableRow(final Fragment root, final ValueExpression... expressions) {
        super(root);
        this.expressions = Arrays.asList(expressions);
    }

    /**
     * Create a value table row from a list of string literals
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
     * Get the list of expressions the row consists of
     *
     * @return list of expressions
     */
    public List<ValueExpression> getExpressions() {
        return this.expressions;
    }

    public void accept(final ValueTableVisitor visitor) {
        visitor.visit(this);
        visitor.leave(this);
    }

    /**
     * Get a {@link Builder} for a {@link ValueTableRow}
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

        public Builder(final Fragment root) {
            this.root = root;
        }

        /**
         * Add one or more string literals to the row
         *
         * @param values strings to be added
         * @return <code>this</code> for fluent programming
         */
        public Builder add(final String... values) {
            for (final String value : values) {
                this.expressions.add(StringLiteral.of(value));
            }
            return this;
        }

        /**
         * Add one or more integer literals to the row
         *
         * @param values integers to be added
         * @return <code>this</code> for fluent programming
         */
        public Builder add(final int... values) {
            for (final int value : values) {
                this.expressions.add(IntegerLiteral.of(value));
            }
            return this;
        }

        /**
         * Add an {@link UnnamedPlaceholder} to the row.
         *
         * @return <code>this</code> for fluent programming
         */
        public Builder addPlaceholder() {
            this.expressions.add(new UnnamedPlaceholder());
            return this;
        }

        /**
         * Add a list of expressions to the {@link ValueTableRow}
         *
         * @param expressions expressions to be added
         * @return <code>this</code> for fluent programming
         */
        public Builder add(final List<ValueExpression> expressions) {
            this.expressions.addAll(expressions);
            return this;
        }

        /**
         * Build a new {@link ValueTableRow}
         *
         * @return new {@link ValueTableRow}
         */
        public ValueTableRow build() {
            return new ValueTableRow(this);
        }
    }
}