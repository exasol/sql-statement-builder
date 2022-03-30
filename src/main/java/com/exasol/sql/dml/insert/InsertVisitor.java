package com.exasol.sql.dml.insert;

import com.exasol.sql.*;

/**
 * Visitor for {@code INSERT} statements.
 */
public interface InsertVisitor extends ValueTableVisitor {
    /**
     * Visit an {@code INSERT} statement.
     *
     * @param insert {@code INSERT} statement to visit
     */
    public void visit(final SqlStatement insert);

    /**
     * Visit the table to insert into.
     *
     * @param table table to visit
     */
    public void visit(final Table table);

    /**
     * Visit the list of fields to insert into.
     *
     * @param insertFields fields to visit.
     */
    public void visit(final InsertFields insertFields);

    /**
     * Leave the list of fields to insert into.
     *
     * @param insertFields fields to leave.
     */
    public void leave(final InsertFields insertFields);

    /**
     * Visit a derived column.
     *
     * @param derivedColumn derived column to visit
     */
    public void visit(final DerivedColumn derivedColumn);
}