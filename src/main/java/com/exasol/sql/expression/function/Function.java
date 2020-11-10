package com.exasol.sql.expression.function;

import com.exasol.sql.expression.ValueExpression;

/**
 * This class represents a function in an SQL statement.
 */
public interface Function extends ValueExpression {
    /**
     * Get a function's name.
     * 
     * @return function's name
     */
    public String getFunctionName();

    /**
     * Check if function needs parenthesis.
     * 
     * @return true if function has parenthesis with parameters
     */
    public boolean hasParenthesis();

    /**
     * Accept {@link FunctionVisitor}.
     * 
     * @param visitor visitor to accept
     */
    public void accept(final FunctionVisitor visitor);
}