package com.exasol.sql.dql;

import com.exasol.sql.*;

public class TableExpression extends AbstractFragement {

    public TableExpression(final Fragment parent) {
        super(parent);
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}
