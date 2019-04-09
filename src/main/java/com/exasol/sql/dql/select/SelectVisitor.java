package com.exasol.sql.dql.select;

import com.exasol.sql.TableValuesVisitor;

public interface SelectVisitor extends TableValuesVisitor {
    public void visit(final Select select);

    public void visit(FromClause fromClause);

    public void visit(Join join);

    public void visit(LimitClause limitClause);

    public void visit(WhereClause whereClause);
}