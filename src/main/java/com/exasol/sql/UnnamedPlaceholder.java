package com.exasol.sql;

import com.exasol.sql.expression.*;

public class UnnamedPlaceholder extends AbstractValueExpression implements ValueExpression {
    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}