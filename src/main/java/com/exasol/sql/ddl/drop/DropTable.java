package com.exasol.sql.ddl.drop;

import com.exasol.sql.*;

/**
 * This class implements an SQL {@link DropTable} statement
 */
public class DropTable extends AbstractFragment implements SqlStatement, DropTableFragment {
    private final Table table;
    private boolean ifExists = false;
    private CascadeConstraints cascadeConstraints = null;

    /**
     * Create a new instance of an {@link DropTable} statement
     *
     * @param tableName name of the table to drop
     */
    public DropTable(final String tableName) {
        super(null);
        this.table = new Table(this, tableName);
    }

    @Override
    public void accept(final DropTableVisitor visitor) {
        visitor.visit(this);
        this.table.accept(visitor);
        if (this.cascadeConstraints != null) {
            this.cascadeConstraints.accept(visitor);
        }
    }

    /**
     * Add "if exists" expression into a DROP TABLE statement
     *
     * @return <code>this</code> for fluent programming
     */
    public synchronized DropTable ifExists() {
        if (!this.ifExists) {
            this.ifExists = true;
        }
        return this;
    }

    /**
     * Add "cascade constraints" expression into a DROP TABLE statement
     *
     * @return <code>this</code> for fluent programming
     */
    public DropTable cascadeConstraints() {
        this.cascadeConstraints = new CascadeConstraints(this);
        return this;
    }

    /**
     * Get true when "if exists" expression presents
     *
     * @return if exists
     */
    public boolean getIfExists() {
        return this.ifExists;
    }

    protected String getTableName() {
        return this.table.getName();
    }

    protected CascadeConstraints getCascadeConstraints() {
        return this.cascadeConstraints;
    }
}
