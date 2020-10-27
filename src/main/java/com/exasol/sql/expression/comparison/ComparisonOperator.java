package com.exasol.sql.expression.comparison;

/**
 * Interface for comparison operators.
 */
public interface ComparisonOperator {
    /**
     * Returns the operator symbol that represents the comparison.
     *
     * @return operator symbol
     */
    @Override
    public String toString();
}
