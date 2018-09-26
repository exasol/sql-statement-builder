package com.exasol.sql.dql;

import com.exasol.sql.*;

public class FromClause extends AbstractFragment {
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
        addChild(new Join(this, JoinType.DEFAULT, name, specification));
        return this;
    }

    public FromClause innerJoin(final String name, final String specification) {
        addChild(new Join(this, JoinType.INNER, name, specification));
        return this;
    }

    public FromClause leftJoin(final String name, final String specification) {
        addChild(new Join(this, JoinType.LEFT, name, specification));
        return this;
    }

    public FromClause rightJoin(final String name, final String specification) {
        addChild(new Join(this, JoinType.RIGHT, name, specification));
        return this;
    }

    public FromClause fullJoin(final String name, final String specification) {
        addChild(new Join(this, JoinType.FULL, name, specification));
        return this;
    }

    public FromClause leftOuterJoin(final String name, final String specification) {
        addChild(new Join(this, JoinType.LEFT_OUTER, name, specification));
        return this;
    }

    public FromClause rightOuterJoin(final String name, final String specification) {
        addChild(new Join(this, JoinType.RIGHT_OUTER, name, specification));
        return this;
    }

    public FromClause fullOuterJoin(final String name, final String specification) {
        addChild(new Join(this, JoinType.FULL_OUTER, name, specification));
        return this;
    }

    public LimitClause limit(final int count) {
        final LimitClause limitClause = new LimitClause(this, count);
        addChild(limitClause);
        return limitClause;
    }

    public LimitClause limit(final int offset, final int count) {
        final LimitClause limitClause = new LimitClause(this, offset, count);
        addChild(limitClause);
        return limitClause;
    }
}