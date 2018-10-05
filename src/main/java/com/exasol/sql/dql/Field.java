package com.exasol.sql.dql;

import com.exasol.sql.*;

/**
 * This class represents a table field in an SQL statement.
 */
public class Field extends AbstractFragment {
    private final String name;

    /**
     * Create a new instance of a {@link Field}
     *
     * @param root root SQL statement
     * @param name field name
     */
    protected Field(final SqlStatement root, final String name) {
        super(root);
        this.name = name;
    }

    /**
     * Get the field name
     *
     * @return field name
     */
    public String getName() {
        return this.name;
    }

    @Override
    public void accept(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}