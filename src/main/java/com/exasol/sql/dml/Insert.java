package com.exasol.sql.dml;

import com.exasol.sql.*;
import com.exasol.sql.dql.Select;

/**
 * This class implements an SQL {@link Select} statement
 */
// [impl->dsn~insert-statements~1]
public class Insert extends AbstractFragment implements SqlStatement, InsertFragment {
    private final Table table;
    private InsertFields insertFields;
    private InsertValues insertValues;

    /**
     * Create a new instance of an {@link Insert} statement
     *
     * @param tableName name of the table into which the data should be inserted
     */
    public Insert(final String tableName) {
        super(null);
        this.table = new Table(this, tableName);
    }

    /**
     * Define fields into which should be inserted
     *
     * @param names field names
     * @return <code>this</code> for fluent programming
     */
    public synchronized Insert field(final String... names) {
        if (this.insertFields == null) {
            this.insertFields = new InsertFields(this);
        }
        this.insertFields.add(names);
        return this;
    }

    /**
     * Get the name of the table into which data should be inserted
     *
     * @return table name
     */
    public String getTableName() {
        return this.table.getName();
    }

    /**
     * Insert a list of string values
     *
     * @param values string values to be inserted
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~values-as-insert-source~1]
    public synchronized Insert values(final String... values) {
        createInsertValueInstanceIfItDoesNotExist();
        this.insertValues.add(values);
        return this;
    }

    protected void createInsertValueInstanceIfItDoesNotExist() {
        if (this.insertValues == null) {
            this.insertValues = new InsertValues(this);
        }
    }

    /**
     * Insert a list of integer values
     *
     * @param values integer values to be inserted
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~values-as-insert-source~1]
    public Insert values(final int... values) {
        createInsertValueInstanceIfItDoesNotExist();
        this.insertValues.add(values);
        return this;
    }

    /**
     * Add an unnamed value placeholder to the value list (this is useful for prepared statements)
     *
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~values-as-insert-source~1]
    public synchronized Insert valuePlaceholder() {
        createInsertValueInstanceIfItDoesNotExist();
        this.insertValues.addPlaceholder();
        return this;
    }

    /**
     * Add a given number unnamed value placeholder to the value list (this is useful for prepared statements)
     *
     * @param amount number of placeholders to be added
     * @return <code>this</code> for fluent programming
     */
    // [impl->dsn~values-as-insert-source~1]
    public synchronized Insert valuePlaceholders(final int amount) {
        createInsertValueInstanceIfItDoesNotExist();
        for (int i = 0; i < amount; ++i) {
            this.insertValues.addPlaceholder();
        }
        return this;
    }

    @Override
    public void accept(final InsertVisitor visitor) {
        visitor.visit(this);
        if (this.table != null) {
            this.table.accept(visitor);
        }
        if (this.insertFields != null) {
            this.insertFields.accept(visitor);
        }
        if (this.insertValues != null) {
            this.insertValues.accept(visitor);
        }
    }
}