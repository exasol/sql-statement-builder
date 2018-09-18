package com.exasol.sql.dql;

import com.exasol.sql.*;

/**
 * This class implements an SQL {@link Select} statement
 */
public class Select extends AbstractFragement implements SqlStatement {
    public Select(final Fragment parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "SELECT";
    }

    /**
     * Create a wildcard field for all involved fields.
     *
     * @return this instance for fluent programming
     */
    public Select all() {
        addChild(Field.all(this));
        return this;
    }

    public Select field(final String... names) {
        for (final String name : names) {
            addChild(new Field(this, name));
        }
        return this;
    }

    @Override
    public void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}
