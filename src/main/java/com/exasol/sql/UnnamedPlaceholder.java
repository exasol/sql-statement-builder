package com.exasol.sql;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;

/**
 * This class implements a nameless placeholder ("?") in an SQL statement
 */
public class UnnamedPlaceholder implements ValueExpression {
    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "?";
    }
}