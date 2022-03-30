package com.exasol.sql.expression.predicate;

import com.exasol.sql.expression.ValueExpression;

/**
 * A class that represents a {@code [NOT] BETWEEN} predicate.
 */
// [impl->dsn~predicate-operators~1]
public class BetweenPredicate extends AbstractPredicate {
    private final ValueExpression expression;
    private final ValueExpression start;
    private final ValueExpression end;

    private BetweenPredicate(final Builder builder) {
        super(builder.operator);
        this.expression = builder.expression;
        this.start = builder.start;
        this.end = builder.end;
    }

    /**
     * Returns the left expression in the {@code [NOT] BETWEEN} predicate.
     *
     * @return left expression in the predicate
     */
    public ValueExpression getExpression() {
        return this.expression;
    }

    /**
     * Returns the start expression in the {@code [NOT] BETWEEN} predicate.
     *
     * @return start expression in the predicate
     */
    public ValueExpression getStartExpression() {
        return this.start;
    }

    /**
     * Returns the end expression in the {@code [NOT] BETWEEN} predicate.
     *
     * @return end expression in the predicate
     */
    public ValueExpression getEndExpression() {
        return this.end;
    }

    /**
     * Creates a new builder for {@link BetweenPredicate}.
     *
     * @return new {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * A class that represents {@link BetweenPredicate} operator.
     */
    public enum BetweenPredicateOperator implements PredicateOperator {
        /** Between boundaries */
        BETWEEN,
        /** Outside boundaries */
        NOT_BETWEEN;

        @Override
        public String toString() {
            return super.toString().replace("_", " ");
        }
    }

    /**
     * A builder for {@link BetweenPredicate}.
     */
    public static class Builder {
        private ValueExpression expression;
        private ValueExpression start;
        private ValueExpression end;
        private BetweenPredicateOperator operator = BetweenPredicateOperator.BETWEEN;

        /**
         * A private constructor to hide the public default.
         */
        private Builder() {
            // intentionally empty
        }

        /**
         * Adds the left expression of predicate.
         *
         * @param expression in predicate expression
         * @return {@code this} for fluent programming
         */
        public Builder expression(final ValueExpression expression) {
            this.expression = expression;
            return this;
        }

        /**
         * Adds the start expression of predicate.
         *
         * @param start start expression in predicate
         * @return {@code this} for fluent programming
         */
        public Builder start(final ValueExpression start) {
            this.start = start;
            return this;
        }

        /**
         * Adds the end expression of predicate.
         *
         * @param end end expression in predicate
         * @return {@code this} for fluent programming
         */
        public Builder end(final ValueExpression end) {
            this.end = end;
            return this;
        }

        /**
         * Sets {@code NOT BETWEEN} predicate.
         *
         * @return {@code this} for fluent programming
         */
        public Builder not() {
            this.operator = BetweenPredicateOperator.NOT_BETWEEN;
            return this;
        }

        /**
         * Creates a new instance of {@code [NOT] BETWEEN} predicate class.
         *
         * @return new instance of {@link BetweenPredicate}
         */
        public BetweenPredicate build() {
            return new BetweenPredicate(this);
        }
    }

    @Override
    public void accept(final PredicateVisitor visitor) {
        visitor.visit(this);
    }

}
