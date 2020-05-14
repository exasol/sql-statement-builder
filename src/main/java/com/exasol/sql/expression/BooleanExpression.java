package com.exasol.sql.expression;

/**
 * Common interface for all types of boolean expressions
 */
public interface BooleanExpression extends ValueExpression {
    /**
     * Accept a visitor
     *
     * @param visitor visitor to accept
     */
    public void accept(final BooleanExpressionVisitor visitor);
}