package com.exasol.sql.dql;

import com.exasol.sql.*;

/**
 * This class represents a {@link Table} in an SQL Statement
 */
public class Table extends AbstractFragement implements TableReference {
    private final String name;

    /**
     * Create a new {@link Table}
     *
     * @param parent parent SQL fragment
     * @param name   table name
     */
    public Table(final Fragment parent, final String name) {
        super(parent);
        this.name = name;
    }

    /**
     * Get the name of the table
     *
     * @return table name
     */
    public String getName() {
        return this.name;
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}