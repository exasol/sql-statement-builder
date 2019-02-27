package com.exasol.sql;

import com.exasol.sql.ddl.CreateTable;
import com.exasol.sql.dml.Insert;
import com.exasol.sql.dql.Select;

/**
 * The {@link StatementFactory} implements an factory for SQL statements.
 */
public final class StatementFactory {
    private static StatementFactory instance;

    /**
     * Get an instance of a {@link StatementFactory}
     *
     * @return the existing instance otherwise creates one.
     */
    public static synchronized StatementFactory getInstance() {
        if (instance == null) {
            instance = new StatementFactory();
        }
        return instance;
    }

    private StatementFactory() {
        // prevent instantiation outside singleton
    }

    /**
     * Create a {@link Select} statement
     *
     * @return a new instance of a {@link Select} statement
     */
    public Select select() {
        return new Select();
    }

    /**
     * Create an {@link Insert} statement
     *
     * @param tableName name of the table into which to insert the data
     * @return a new instance of a {@link Insert} statement
     */
    public Insert insertInto(final String tableName) {
        return new Insert(tableName);
    }

    /**
     * Create a {@link CreateTable} statement
     *
     * @param tableName name of the table to create
     * @return a new instance of a {@link CreateTable} statement
     */
    public CreateTable createTable(final String tableName) {
        return new CreateTable(tableName);
    }
}
