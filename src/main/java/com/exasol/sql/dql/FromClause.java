package com.exasol.sql.dql;

import com.exasol.sql.*;

public class FromClause extends AbstractFragement {
    public FromClause(final Fragment parent) {
        super(parent);
    }

    public static FromClause table(final Fragment parent, final String name) {
        final FromClause fromClause = new FromClause(parent);
        fromClause.addChild(new Table(fromClause, name));
        return fromClause;
    }

    public static FromClause tableAs(final Fragment parent, final String name, final String as) {
        final FromClause fromClause = new FromClause(parent);
        fromClause.addChild(new Table(fromClause, name, as));
        return fromClause;
    }

    public FromClause from(final String name) {
        addChild(new Table(this, name));
        return this;
    }

    public Fragment fromTableAs(final String name, final String as) {
        addChild(new Table(this, name, as));
        return this;
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }

    public FromClause join(final String name, final String specification) {
        addChild(new Join(this, name, specification));
        return this;
    }
}