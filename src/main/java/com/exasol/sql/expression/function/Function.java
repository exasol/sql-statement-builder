package com.exasol.sql.expression.function;

import com.exasol.sql.expression.ValueExpression;

/**
 * This class represents a function in an SQL statement.
 */
public interface Function extends ValueExpression {
    public String getFunctionName();

    public boolean hasDerivedColumnName();

    public String getDerivedColumnName();
}
