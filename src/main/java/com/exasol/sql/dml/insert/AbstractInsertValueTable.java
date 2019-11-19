package com.exasol.sql.dml.insert;

import com.exasol.sql.*;

public abstract class AbstractInsertValueTable<T extends AbstractInsertValueTable<T>> extends AbstractFragment {
    protected ValueTable insertValueTable;
    protected InsertFields insertFields;

    public AbstractInsertValueTable(final Fragment root) {
        super(root);
    }

    protected abstract T self();

    protected synchronized void createInsertValueInstanceIfItDoesNotExist() {
        if (this.insertValueTable == null) {
            this.insertValueTable = new ValueTable(this);
        }
    }

    /**
     * Define fields into which should be inserted
     *
     * @param names field names
     * @return <code>this</code> for fluent programming
     */
    public synchronized T field(final String... names) {
        if (this.insertFields == null) {
            this.insertFields = new InsertFields(this.getRoot());
        }
        this.insertFields.add(names);
        return self();
    }

    /**
     * Insert a value table
     *
     * @param table value table to be inserted
     * @return <code>this</code> for fluent programming
     */
    public synchronized T valueTable(final ValueTable table) {
        if (this.insertValueTable != null) {
            throw new IllegalStateException("Cannot add a value table to an INSERT command that already has one.");
        }
        this.insertValueTable = table;
        return self();
    }

    /**
     * Insert a list of string values
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
     * Insert a list of integer values
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
     * Add an unnamed value placeholder to the value list (this is useful for prepared statements)
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
     * Add a given number unnamed value placeholder to the value list (this is useful for prepared statements)
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
}