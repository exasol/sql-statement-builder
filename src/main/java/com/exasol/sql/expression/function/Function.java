package com.exasol.sql.expression.function;

import java.util.List;

import com.exasol.sql.expression.ValueExpression;

/**
 * This class represents a function in an SQL statement.
 */
public interface Function extends ValueExpression {
    public String getFunctionName();

    public List<ValueExpression> getValueExpressions();

    public boolean hasDerivedColumnName();

    public String getDerivedColumnName();
}
