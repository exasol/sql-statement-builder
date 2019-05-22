package com.exasol.sql.dql.select;

import com.exasol.sql.*;
import com.exasol.sql.expression.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements an SQL {@link Select} statement
 */
public class Select extends AbstractFragment implements SqlStatement, SelectFragment {
    private final List<Field> fields = new ArrayList<>();
    private FromClause fromClause = null;
    private WhereClause whereClause = null;
    private LimitClause limitClause = null;
    private GroupByClause groupByClause = null;

    /**
     * Create a new instance of a {@link Select}
     */
    public Select() {
        super(null);
    }

    /**
     * Add a wildcard field for all involved fields.
     *
     * @return <code>this</code> instance for fluent programming
     */
    public Select all() {
        this.fields.add(new Field(this, "*"));
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
            this.fields.add(new Field(this, name));
        }
        return this;
    }

    /**
     * Get the {@link FromClause} of this select statement
     *
     * @return from clause
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized FromClause from() {
        if (this.fromClause == null) {
            this.fromClause = new FromClause(this);
        }
        return this.fromClause;
    }

    /**
     * Create a new full outer {@link LimitClause}
     *
     * @param count maximum number of rows to be included in query result
     * @return <code>this</code> for fluent programming
     * @throws IllegalStateException if a limit clause already exists
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized Select limit(final int count) {
        if (this.limitClause != null) {
            throw new IllegalStateException(
                    "Tried to create a LIMIT clause in a SELECT statement that already had one.");
        }
        this.limitClause = new LimitClause(this, count);
        return this;
    }

    /**
     * Create a new full outer {@link LimitClause}
     *
     * @param offset index of the first row in the query result
     * @param count  maximum number of rows to be included in query result
     * @return <code>this</code> for fluent programming
     * @throws IllegalStateException if a limit clause already exists
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized Select limit(final int offset, final int count) {
        if (this.limitClause != null) {
            throw new IllegalStateException(
                    "Tried to create a LIMIT clause in a SELECT statement that already had one.");
        }
        this.limitClause = new LimitClause(this, offset, count);
        return this;
    }

    /**
     * Create a new {@link WhereClause}
     *
     * @param expression boolean expression that defines the filter criteria
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized Select where(final BooleanExpression expression) {
        if (this.whereClause == null) {
            this.whereClause = new WhereClause(this, expression);
        }
        return this;
    }

    /**
     * Create a new {@link GroupByClause}
     *
     * @param columnNames column names
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized GroupByClause groupBy(final String... columnNames) {
        if (this.groupByClause == null) {
            this.groupByClause = new GroupByClause(this, columnNames);
        }
        return this.groupByClause;
    }

    @Override
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
        for (final Field field : this.fields) {
            field.accept(visitor);
        }
        if (this.fromClause != null) {
            this.fromClause.accept(visitor);
        }
        if (this.whereClause != null) {
            this.whereClause.accept(visitor);
        }
        if (this.limitClause != null) {
            this.limitClause.accept(visitor);
        }
        if (groupByClause != null) {
            this.groupByClause.accept(visitor);
        }
    }
}