package com.exasol.sql.dql;

import com.exasol.sql.*;
import com.exasol.sql.expression.BooleanExpression;

/**
 * This class implements an SQL {@link Select} statement
 */
public class Select extends AbstractFragment implements SqlStatement {
    private FromClause from;
    private WhereClause where;

    /**
     * Create a new instance of a {@link Select}
     */
    public Select() {
        super();
    }

    /**
     * Add a wildcard field for all involved fields.
     *
     * @return <code>this</code> instance for fluent programming
     */
    public Select all() {
        addChild(Field.all());
        return this;
    }

    /**
     * Add one or more named fields.
     *
     * @param names field name
     * @return <code>this</code> instance for fluent programming
     */
    public Select field(final String... names) {
        for (final String name : names) {
            addChild(new Field(name));
        }
        return this;
    }

    /**
     * Add a boolean value expression
     *
     * @param expression boolean value expression
     * @return <code>this</code> instance for fluent programming
     */
    public Select value(final BooleanExpression expression) {
        addChild(new BooleanValueExpression(expression));
        return this;
    }

    /**
     * Get the {@link FromClause} of this select statement
     *
     * @return from clause
     */
    public synchronized FromClause from() {
        if (this.from == null) {
            this.from = new FromClause();
            addChild(this.from);
        }
        return this.from;
    }

    /**
     * Get the {@link WhereClause} of this select statement
     *
     * @return from clause
     */
    public synchronized WhereClause where() {
        if (this.where == null) {
            throw new IllegalStateException("Tried to access a WHERE clause before it was constructed.");
        }
        return this.where;
    }

    @Override
    public void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}