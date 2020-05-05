package com.exasol.sql;

import com.exasol.sql.expression.AbstractValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;

/**
 * This class represents a table field in an SQL statement.
 */
public class Field extends AbstractValueExpression {
    private final String name;

    /**
     * Create a new instance of a {@link Field}.
     *
     * @param name field name
     */
    public Field(final String name) {
        this.name = name;
    }

    /**
     * Get the field name.
     *
     * @return field name
     */
    public String getName() {
        return this.name;
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return getName();
    }
}