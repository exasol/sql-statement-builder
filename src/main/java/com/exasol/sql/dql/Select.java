package com.exasol.sql.dql;

import com.exasol.sql.*;

/**
 * This class implements an SQL {@link Select} statement
 */
public class Select extends AbstractFragment implements SqlStatement {
    public Select(final Fragment parent) {
        super(parent);
    }

    public Select() {
        super();
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

    /**
     * Add a {@link FromClause} to the statement with a table identified by its name
     *
     * @param name table reference name
     * @return the FROM clause
     */
    public FromClause from(final String name) {
        final FromClause from = FromClause.table(this, name);
        addChild(from);
        return from;
    }

    /**
     * Add a {@link FromClause} to the statement with an aliased table identified by
     * its name
     *
     * @param name table reference name
     * @param as   table correlation name
     * @return the FROM clause
     */
    public FromClause fromTableAs(final String name, final String as) {
        final FromClause from = FromClause.tableAs(this, name, as);
        addChild(from);
        return from;
    }
}