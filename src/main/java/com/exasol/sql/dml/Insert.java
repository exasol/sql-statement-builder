package com.exasol.sql.dml;

import com.exasol.sql.*;
import com.exasol.sql.dql.Select;

/**
 * This class implements an SQL {@link Select} statement
 */
public class Insert extends AbstractFragment implements SqlStatement, InsertFragment {
    private final Table table;
    private InsertFields fields;

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
        if (this.fields == null) {
            this.fields = new InsertFields(this);
        }
        this.fields.add(names);
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
        if (this.fields != null) {
            this.fields.accept(visitor);
        }
    }
}