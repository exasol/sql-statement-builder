package com.exasol.sql;

import java.util.Optional;

/**
 * This class represents a {@link Table} in an SQL Statement
 */
public class Table extends AbstractFragment {
    private final String name;
    private final Optional<String> as;

    /**
     * Create a new {@link Table} with a name and an alias
     *
     * @param root SQL statement this table belongs to
     * @param name table name
     */
    public Table(final Fragment root, final String name) {
        super(root);
        this.name = name;
        this.as = Optional.empty();
    }

    /**
     * Create a new {@link Table} with a name and an alias
     *
     * @param root SQL statement this table belongs to
     * @param name table name
     * @param as table alias
     */
    public Table(final Fragment root, final String name, final String as) {
        super(root);
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

    public void accept(final SqlStatementVisitor visitor) {
        visitor.visit(this);
    }
}