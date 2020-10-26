package com.exasol.sql.expression.comparison;

import java.beans.Expression;

/**
 * This enum represents the different types of {@link SimpleComparison}s that can be used in {@link Expression}s.
 */
public enum SimpleComparisonOperator implements ComparisonOperator {
    // [impl->dsn~comparison-operations~1]
    EQUAL("="), NOT_EQUAL("<>"), GREATER_THAN(">"), GREATER_THAN_OR_EQUAL(">="), LESS_THAN("<"),
    LESS_THAN_OR_EQUAL("<=");

    private final String operatorSymbol;

    private SimpleComparisonOperator(final String operatorSymbol) {
        this.operatorSymbol = operatorSymbol;
    }

    @Override
    public String toString() {
        return this.operatorSymbol;
    }

    /**
     * Get the {@link SimpleComparisonOperator} for the provided symbol
     *
     * @param operatorSymbol symbol that represents the operator
     * @return operator
     */
    public static SimpleComparisonOperator ofSymbol(final String operatorSymbol) {
        for (final SimpleComparisonOperator operator : SimpleComparisonOperator.values()) {
            if (operator.operatorSymbol.equals(operatorSymbol)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Unknown comparison operator \"" + operatorSymbol + "\"");
    }
}