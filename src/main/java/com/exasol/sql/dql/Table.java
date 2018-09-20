package com.exasol.sql.dql;

import java.util.Optional;

import com.exasol.sql.*;

/**
 * This class represents a {@link Table} in an SQL Statement
 */
public class Table extends AbstractFragment implements TableReference {
    private final String name;
    private final Optional<String> as;

    /**
     * Create a new {@link Table}
     *
     * @param parent parent SQL fragment
     * @param name   table name
     */
    public Table(final Fragment parent, final String name) {
        super(parent);
        this.name = name;
        this.as = Optional.empty();
    }

    public Table(final Fragment parent, final String name, final String as) {
        super(parent);
        this.name = name;
        this.as = Optional.of(as);
    }

    /**
     * Get the name of the table
     *
     * @return table name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the correlation name (i.e. an alias) of the table.
     *
     * @return correlation name
     */
    public Optional<String> getAs() {
        return this.as;
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}