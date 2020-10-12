package com.exasol.sql.expression;

/**
 * This class represents a logical LIKE predicate.
 */
// [impl->dsn~like-predicate~1]
public class Like extends AbstractBooleanExpression {
    private final ValueExpression leftOperand;
    private final ValueExpression rightOperand;
    private final boolean not;
    private final Character escape;

    private Like(final Builder builder) {
        this.leftOperand = builder.left;
        this.rightOperand = builder.right;
        this.not = builder.not;
        this.escape = builder.escape;
    }

    /**
     * Create a new builder for {@link Like}.
     *
     * @return new {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Get the left-hand side operator.
     *
     * @return left operator
     */
    public ValueExpression getLeftOperand() {
        return this.leftOperand;
    }

    /**
     * Get the right-hand side operator.
     *
     * @return right operator
     */
    public ValueExpression getRightOperand() {
        return this.rightOperand;
    }

    /**
     * Check if LIKE predicate has NOT.
     * 
     * @return true if contains not
     */
    public boolean hasNot() {
        return this.not;
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

    @Override
    public void acceptConcrete(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void dismissConcrete(final BooleanExpressionVisitor visitor) {
        visitor.leave(this);
    }

    /**
     * A builder for {@link Like}.
     */
    public static class Builder {
        private ValueExpression left;
        private ValueExpression right;
        private boolean not = false;
        private Character escape = null;

        /**
         * Add the left operand.
         *
         * @param left left operand
         * @return <code>this</code> for fluent programming
         */
        public Builder left(final ValueExpression left) {
            this.left = left;
            return this;
        }

        /**
         * Add the right operand.
         *
         * @param right right operand
         * @return <code>this</code> for fluent programming
         */
        public Builder right(final ValueExpression right) {
            this.right = right;
            return this;
        }

        /**
         * Set for NOT LIKE expression.
         *
         * @return <code>this</code> for fluent programming
         */
        public Builder not() {
            this.not = true;
            return this;
        }

        /**
         * Add an escape character.
         *
         * @param escape escape character
         * @return <code>this</code> for fluent programming
         */
        public Builder escape(final char escape) {
            this.escape = escape;
            return this;
        }

        /**
         * Create a new instance of {@link Like}.
         * 
         * @return new instance of {@link Like}
         */
        public Like build() {
            return new Like(this);
        }
    }
}