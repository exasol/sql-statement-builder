package com.exasol.sql.dml.insert;

import com.exasol.sql.*;
import com.exasol.sql.expression.ValueExpression;

/**
 * Abstract base class for SQL fragments that contain a insert value table (for example {@code INSERT}, {@code MERGE}).
 *
 * @param <T> self pointer
 */
public abstract class AbstractInsertValueTable<T extends AbstractInsertValueTable<T>> extends AbstractFragment {
    protected ValueTable insertValueTable;
    protected InsertFields insertFields;

    /**
     * Create the abstract base for a fragment containing a value table.
     *
     * @param root root fragment
     */
    public AbstractInsertValueTable(final Fragment root) {
        super(root);
    }

    protected abstract T self();

    protected synchronized void createInsertValueInstanceIfItDoesNotExist() {
        if (!hasValues()) {
            this.insertValueTable = new ValueTable(this);
        }
    }

    /**
     * Define fields into which should be inserted.
     *
     * @param names field names
     * @return <code>this</code> for fluent programming
     */
    public synchronized T field(final String... names) {
        if (!hasFields()) {
            this.insertFields = new InsertFields(this.getRoot());
        }
        this.insertFields.add(names);
        return self();
    }

    /**
     * Insert a value table.
     *
     * @param table value table to be inserted
     * @return <code>this</code> for fluent programming
     */
    public synchronized T valueTable(final ValueTable table) {
        if (hasValues()) {
            throw new IllegalStateException("Cannot add a value table to an INSERT command that already has one.");
        }
        this.insertValueTable = table;
        return self();
    }

    /**
     * Insert a list of string values.
     *
     * @param values string values to be inserted
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~values-as-insert-source~1]
    public synchronized T values(final String... values) {
        createInsertValueInstanceIfItDoesNotExist();
        this.insertValueTable.add(values);
        return self();
    }

    /**
     * Insert a list of integer values.
     *
     * @param values integer values to be inserted
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~values-as-insert-source~1]
    public T values(final int... values) {
        createInsertValueInstanceIfItDoesNotExist();
        this.insertValueTable.add(values);
        return self();
    }

    /**
     * Insert a list of value expressions.
     *
     * @param expressions value expressions to be inserted
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~values-as-insert-source~1]
    public T values(final ValueExpression... expressions) {
        createInsertValueInstanceIfItDoesNotExist();
        this.insertValueTable.add(expressions);
        return self();
    }

    /**
     * Add an unnamed value placeholder to the value list (this is useful for prepared statements).
     *
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~values-as-insert-source~1]
    public synchronized T valuePlaceholder() {
        createInsertValueInstanceIfItDoesNotExist();
        this.insertValueTable.addPlaceholder();
        return self();
    }

    /**
     * Add a given number unnamed value placeholder to the value list (this is useful for prepared statements).
     *
     * @param amount number of placeholders to be added
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~values-as-insert-source~1]
    public synchronized T valuePlaceholders(final int amount) {
        createInsertValueInstanceIfItDoesNotExist();
        for (int i = 0; i < amount; ++i) {
            valuePlaceholder();
        }
        return self();
    }

    /**
     * Check if a value table is present.
     *
     * @return {@code true} if a value table exists
     */
    public boolean hasValues() {
        return this.insertValueTable != null;
    }

    /**
     * Check if a insert fields are defined.
     *
     * @return {@code true} if insert fields are defined
     */
    public boolean hasFields() {
        return this.insertFields != null;
    }
}