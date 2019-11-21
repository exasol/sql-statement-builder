package com.exasol.sql.dql.select;

import com.exasol.sql.*;

public interface SelectVisitor extends ValueTableVisitor {
    public void visit(final Select select);

    public void visit(FromClause fromClause);

    public void visit(Join join);

    public void visit(LimitClause limitClause);

    public void visit(WhereClause whereClause);

    public void visit(GroupByClause groupByClause);

    public void visit(OrderByClause orderByClause);

    public void visit(Field field);

    void visit(Table table);
}