package com.exasol.sql.expression;

import com.exasol.sql.*;

public class BooleanExpression extends AbstractFragment {
    protected BooleanExpression(final Fragment parent) {
        super(parent);
    }

    public static BooleanExpression not(final String string) {
        return new BooleanExpression(null);
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}