package com.exasol.sql.dql;

import com.exasol.sql.FragmentVisitor;

public interface SelectVisitor extends FragmentVisitor {
    public void visit(final Select select);

    public void visit(FromClause fromClause);

    public void visit(Join join);

    public void visit(LimitClause limitClause);

    public void visit(WhereClause whereClause);

    public void visit(ValueTable valueTable);

    public void visit(ValueTableRow valueTableRow);
}