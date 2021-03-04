package com.exasol.sql.expression.predicate;

/**
 * An interface for the predicate operators.
 */
public interface PredicateOperator {

    /**
     * Returns the predicate operator symbol.
     *
     * @return predicate operator symbol
     */
    @Override
    public String toString();

}
