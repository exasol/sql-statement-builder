package com.exasol.sql.dql;

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
        return new Select(null);
    }
}
