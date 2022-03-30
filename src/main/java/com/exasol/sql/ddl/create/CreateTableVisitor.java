package com.exasol.sql.ddl.create;

import com.exasol.sql.Table;

/**
 * Visitor for {@code CREATE TABLE} statements.
 */
public interface CreateTableVisitor {
    /**
     * Visit a {@code CREATE TABLE} statement.
     *
     * @param createTable {@code CREATE TABLE} statement to visit
     */
    public void visit(final CreateTable createTable);

    /**
     * Leave a {@code CREATE TABLE} statement.
     *
     * @param createTable {@code CREATE TABLE} statement to leave
     */
    public void leave(final CreateTable createTable);

    /**
     * Visit the table to be created.
     *
     * @param table table to visit
     */
    public void visit(final Table table);
}
