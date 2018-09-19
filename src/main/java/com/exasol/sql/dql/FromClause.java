package com.exasol.sql.dql;

import com.exasol.sql.*;

public class FromClause extends AbstractFragement {
    public FromClause(final Fragment parent, final String... names) {
        super(parent);
        for (final String name : names) {
            addChild(new Table(this, name));
        }
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}
