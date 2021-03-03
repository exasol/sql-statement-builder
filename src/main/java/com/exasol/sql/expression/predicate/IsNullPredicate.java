package com.exasol.sql.expression.predicate;

import com.exasol.sql.expression.ValueExpression;

/**
 * A class that represents a {@code IS [NOT] NULL} predicate.
 */
// [impl->dsn~predicate-operators~1]
public class IsNullPredicate extends AbstractPredicate {
    private final ValueExpression operand;

    /**
     * Creates a new instance of {@link IsNullPredicate} for {@code IS NULL} predicate.
     *
     * @param operand value expression to check for null
     */
    public IsNullPredicate(final ValueExpression operand) {
        super(IsNullPredicateOperator.IS_NULL);
        this.operand = operand;
    }

    /**
     * Creates a new instance of {@link IsNullPredicate} for {@code IS [NOT] NULL} predicate.
     *
     * @param operator predicate operator
     * @param operand  value expression to check for null
     */
    public IsNullPredicate(final IsNullPredicateOperator operator, final ValueExpression operand) {
        super(operator);
        this.operand = operand;
    }

    /**
     * Returns the value expression to be checked for {@code null}.
     *
     * @return value expression operand
     */
    public ValueExpression getOperand() {
        return this.operand;
    }

    /**
     * An operator for {@link IsNullPredicate} class.
     */
    public enum IsNullPredicateOperator implements PredicateOperator {
        IS_NULL, IS_NOT_NULL;

        @Override
        public String toString() {
            return super.toString().replace("_", " ");
        }
    }

    @Override
    public void accept(final PredicateVisitor visitor) {
        visitor.visit(this);
    }

}
