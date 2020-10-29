package com.exasol.sql.expression;

/**
 * Common interface for all types of value expressions
 */
public interface ValueExpression {
    /**
     * Accept a visitor
     *
     * @param visitor visitor to accept
     */
    public void accept(ValueExpressionVisitor visitor);
}