package com.exasol.sql.expression.predicate;

import com.exasol.sql.expression.BooleanExpression;

/**
 * Interface for classes that implement predicate expressions.
 */
public interface Predicate extends BooleanExpression {

    /**
     * Returns the predicate operator.
     *
     * @return predicate operator
     */
    public PredicateOperator getOperator();

    /**
     * Accepts {@link PredicateVisitor}.
     *
     * @param visitor predicate visitor to accept
     */
    public void accept(PredicateVisitor visitor);
}
