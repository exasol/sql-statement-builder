package com.exasol.sql;

import com.exasol.sql.expression.*;

/**
 * This class implements a nameless placeholder ("?") in an SQL statement
 */
public class UnnamedPlaceholder extends AbstractValueExpression implements ValueExpression {
    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "?";
    }
}