package com.exasol.sql.dql;

import java.util.Optional;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.FragmentVisitor;

/**
 * This class represents a {@link Table} in an SQL Statement
 */
public class Table extends AbstractFragment implements TableReference {
    private final String name;
    private final Optional<String> as;

    /**
     * Create a new {@link Table}
     *
     * @param name table name
     */
    public Table(final String name) {
        super();
        this.name = name;
        this.as = Optional.empty();
    }

    /**
     * Create a new {@link Table} with a name and an alias
     *
     * @param name table name
     * @param as table alias
     */
    public Table(final String name, final String as) {
        super();
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