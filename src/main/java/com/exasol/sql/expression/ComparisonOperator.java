package com.exasol.sql.expression;

import java.beans.Expression;

/**
 * This enum represents the different types of {@link Comparison}s that can be
 * used in {@link Expression}s.
 */
public enum ComparisonOperator {
    // [impl->dsn~comparison-operations~1]
    EQUAL("="), NOT_EQUAL("<>"), GREATER(">"), GREATER_OR_EQUAL(">="), LESS_THAN("<"), LESS_THAN_OR_EQUAL("<=");

    private final String operatorSymbol;

    private ComparisonOperator(final String operatorSymbol) {
        this.operatorSymbol = operatorSymbol;
    }

    /**
     * Returns the operator symbol that represents the comparison.
     *
     * @return operator symbol
     */
    @Override
    public String toString() {
        return this.operatorSymbol;
    }

    /**
     * Get the {@link ComparisonOperator} for the provided symbol
     *
     * @param operatorSymbol symbol that represents the operator
     * @return operator
     */
    public static ComparisonOperator ofSymbol(final String operatorSymbol) {
        for (final ComparisonOperator operator : ComparisonOperator.values()) {
            if (operator.operatorSymbol.equals(operatorSymbol)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Unknown comparison operator \"" + operatorSymbol + "\"");
    }
}