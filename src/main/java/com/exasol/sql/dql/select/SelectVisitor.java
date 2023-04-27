package com.exasol.sql.dql.select;

import com.exasol.sql.*;

/**
 * Visitor for {@code SELECT} SQL statements.
 */
public interface SelectVisitor extends ValueTableVisitor {
    /**
     * Visit the {@code SELECT} part of the statement.
     *
     * @param select select to visit
     */
    public void visit(final Select select);

    /**
     * Visit the {@code FROM} clause.
     *
     * @param fromClause {@code FROM} clause to visit
     */
    public void visit(final FromClause fromClause);

    /**
     * Leave the {@code FROM} clause.
     *
     * @param fromClause {@code FROM} clause to leave
     */
    public void leave(final FromClause fromClause);

    /**
     * Visit the {@code JOIN} clause.
     *
     * @param join {@code JOIN} clause to visit
     */
    public void visit(final Join join);

    /**
     * Visit the {@code LIMIT} clause.
     *
     * @param limitClause {@code LIMIT} clause to visit
     */
    public void visit(final LimitClause limitClause);

    /**
     * Visit the {@code WHERE} clause.
     *
     * @param whereClause {@code WHERE} clause to visit
     */
    public void visit(final WhereClause whereClause);

    /**
     * Visit the {@code GROUP BY} clause.
     *
     * @param groupByClause {@code GROUP BY} clause to visit
     */
    public void visit(final GroupByClause groupByClause);

    /**
     * Visit the {@code ORDER BY} clause.
     *
     * @param orderByClause {@code ORDER BY} clause to visit
     */
    public void visit(final OrderByClause orderByClause);

    /**
     * Visit a derived column.
     *
     * @param derivedColumn derived column to visit
     */
    public void visit(final DerivedColumn derivedColumn);

    /**
     * Visit a table.
     *
     * @param table table to visit
     */
    public void visit(final Table table);

    void leave(DerivedColumn derivedColumn);
}