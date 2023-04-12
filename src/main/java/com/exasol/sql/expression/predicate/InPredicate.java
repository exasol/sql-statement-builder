package com.exasol.sql.expression.predicate;

import java.util.Arrays;
import java.util.List;

import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.ValueExpression;

/**
 * A class that represents a {@code [NOT] IN} predicate.
 */
// [impl->dsn~predicate-operators~1]
public class InPredicate extends AbstractPredicate {
    private final ValueExpression expression;
    private final List<ValueExpression> operands;
    private final Select selectQuery;

    private InPredicate(final Builder builder) {
        super(builder.operator);
        this.expression = builder.expression;
        this.operands = builder.operands;
        this.selectQuery = builder.selectQuery;
    }

    /**
     * Checks if {@link InPredicate} has a sub query.
     *
     * @return {@code true} if predicate has a sub query, otherwise return {@code false}
     */
    public boolean hasSelectQuery() {
        return this.selectQuery != null;
    }

    /**
     * Returns the left expression in the {@code [NOT] IN} predicate.
     *
     * @return expression in predicate
     */
    public ValueExpression getExpression() {
        return this.expression;
    }

    /**
     * Returns the value expressions in the {@code [NOT] IN} predicate.
     *
     * @return value expression operands
     */
    public List<ValueExpression> getOperands() {
        return this.operands;
    }

    /**
     * Returns the sub select query in the {@code [NOT] IN} predicate.
     *
     * @return sub select query
     */
    public Select getSelectQuery() {
        return this.selectQuery;
    }

    /**
     * Creates a new builder for {@link InPredicate}.
     *
     * @return new {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * A class that represents {@link InPredicate} operator.
     */
    public enum InPredicateOperator implements PredicateOperator {
        /** In-list operator */
        IN,
        /** Not-in-list operator */
        NOT_IN;

        @Override
        public String toString() {
            return super.toString().replace("_", " ");
        }
    }

    /**
     * A builder for {@link InPredicate}.
     */
    public static class Builder {
        private ValueExpression expression;
        private List<ValueExpression> operands = null;
        private Select selectQuery = null;
        private InPredicateOperator operator = InPredicateOperator.IN;

        /**
         * A private constructor to hide the public default.
         */
        private Builder() {
            // intentionally empty
        }

        /**
         * Adds the predicate expression.
         *
         * @param expression in predicate expression
         * @return {@code this} for fluent programming
         */
        public Builder expression(final ValueExpression expression) {
            this.expression = expression;
            return this;
        }

        /**
         * Adds the operands.
         *
         * @param operands operands for {@code [NOT] IN} predicate
         * @return {@code this} for fluent programming
         */
        public Builder operands(final ValueExpression... operands) {
            if (this.selectQuery != null) {
                throw new IllegalArgumentException(getExceptionMessage());
            }
            this.operands = Arrays.asList(operands);
            return this;
        }

        /**
         * Adds the sub select query.
         *
         * @param select sub select for {@code [NOT] IN} predicate
         * @return {@code this} for fluent programming
         */
        public Builder selectQuery(final Select select) {
            if (this.operands != null) {
                throw new IllegalArgumentException(getExceptionMessage());
            }
            this.selectQuery = select;
            return this;
        }

        private String getExceptionMessage() {
            return "The '[NOT] IN' predicate cannot have both select query and expressions. "
                    + "Please use only either expressions or sub select query.";
        }

        /**
         * Sets {@code NOT IN} predicate.
         *
         * @return {@code this} for fluent programming
         */
        public Builder not() {
            this.operator = InPredicateOperator.NOT_IN;
            return this;
        }

        /**
         * Creates a new instance of {@code [NOT] IN} predicate class.
         *
         * @return new instance of {@link InPredicate}
         */
        public InPredicate build() {
            return new InPredicate(this);
        }
    }

    @Override
    public void accept(final PredicateVisitor visitor) {
        visitor.visit(this);
    }

}
