package com.exasol.sql.dml.insert;

import com.exasol.sql.SqlStatement;
import com.exasol.sql.Table;

/**
 * This class implements an SQL {@link Insert} statement
 */
// [impl->dsn~insert-statements~1]
public class Insert extends AbstractInsertValueTable implements SqlStatement, InsertFragment {
    private final Table table;
    private InsertFields insertFields;

    /**
     * Create a new instance of an {@link Insert} statement
     *
     * @param tableName name of the table into which the data should be inserted
     */
    public Insert(final String tableName) {
        super(null);
        this.table = new Table(this, tableName);
    }

    @Override
    protected AbstractInsertValueTable self() {
        return this;
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

    @Override
    public void accept(final InsertVisitor visitor) {
        visitor.visit(this);
        if (this.table != null) {
            this.table.accept(visitor);
        }
        if (this.insertFields != null) {
            this.insertFields.accept(visitor);
        }
        if (this.insertValueTable != null) {
            this.insertValueTable.accept(visitor);
        }
    }
}