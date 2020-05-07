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
     * Check if this function has a derived column name.
     * 
     * @return true if this function has a derived column name
     */
    public boolean hasDerivedColumnName();

    /**
     * Get a derived column name.
     * 
     * @return derived column name as a String
     */
    public String getDerivedColumnName();
}
