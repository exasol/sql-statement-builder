package com.exasol.sql.expression.comparison;

import com.exasol.sql.expression.ValueExpression;

/**
 * This class represents a LIKE comparison.
 */
// [impl->dsn~like-predicate~1]
public class LikeComparison extends AbstractComparison {
    private final Character escape;

    private LikeComparison(final Builder builder) {
        super(builder.operator, builder.left, builder.right);
        this.escape = builder.escape;
    }

    /**
     * Create a new builder for {@link LikeComparison}.
     *
     * @return new {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public void accept(final ComparisonVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Check if LIKE predicate has NOT.
     * 
     * @return true if contains not
     */
    public boolean hasNot() {
        return getOperator().equals(LikeComparisonOperator.NOT_LIKE);
    }

    /**
     * Check if LIKE predicate has ESCAPE.
     *
     * @return true if contains not
     */
    public boolean hasEscape() {
        return this.escape != null;
    }

    /**
     * Get an escape character.
     * 
     * @return escape char
     */
    public Character getEscape() {
        return this.escape;
    }

    /**
     * Fuzzy string comparators.
     */
    public enum LikeComparisonOperator implements ComparisonOperator {
        /** {@code LIKE} comparator */
        LIKE,
        /** {@code NOT_LIKE} comparator */
        NOT_LIKE;

        @Override
        public String toString() {
            return super.toString().replace("_", " ");
        }
    }

    /**
     * A builder for {@link LikeComparison}.
     */
    public static class Builder {
        private ValueExpression left;
        private ValueExpression right;
        private LikeComparisonOperator operator = LikeComparisonOperator.LIKE;
        private Character escape = null;

        /**
         * Add the left operand.
         *
         * @param left left operand
         * @return {@code this} for fluent programming
         */
        public Builder left(final ValueExpression left) {
            this.left = left;
            return this;
        }

        /**
         * Add the right operand.
         *
         * @param right right operand
         * @return {@code this} for fluent programming
         */
        public Builder right(final ValueExpression right) {
            this.right = right;
            return this;
        }

        /**
         * Set for NOT LIKE expression.
         *
         * @return {@code this} for fluent programming
         */
        public Builder not() {
            this.operator = LikeComparisonOperator.NOT_LIKE;
            return this;
        }

        /**
         * Add an escape character.
         *
         * @param escape escape character
         * @return {@code this} for fluent programming
         */
        public Builder escape(final char escape) {
            this.escape = escape;
            return this;
        }

        /**
         * Create a new instance of {@link LikeComparison}.
         * 
         * @return new instance of {@link LikeComparison}
         */
        public LikeComparison build() {
            return new LikeComparison(this);
        }
    }
}