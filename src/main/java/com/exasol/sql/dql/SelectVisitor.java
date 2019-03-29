package com.exasol.sql.dql;

import com.exasol.sql.FragmentVisitor;
import com.exasol.sql.ValueTableVisitor;

public interface SelectVisitor extends FragmentVisitor, ValueTableVisitor {
    public void visit(final Select select);

    public void visit(FromClause fromClause);

    public void visit(Join join);

    public void visit(LimitClause limitClause);

    public void visit(WhereClause whereClause);
}