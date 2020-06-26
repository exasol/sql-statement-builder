package com.exasol.sql.ddl.drop;

import com.exasol.sql.*;

/**
 * This class implements an SQL {@link DropTable} statement.
 */
// [impl->dsn~drop-statements~1]
public class DropTable extends AbstractFragment implements SqlStatement, DropTableFragment {
    private final Table table;
    private boolean ifExists = false;
    private CascadeConstraints cascadeConstraints = null;

    /**
     * Create a new instance of an {@link DropTable} statement.
     *
     * @param tableName name of the table to drop
     */
    public DropTable(final String tableName) {
        super(null);
        this.table = new Table(this, tableName);
    }

    /**
     * Add {@code IF EXISTS} clause into a {@code DROP TABLE} statement.
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
     * Add {@code CASCADE CONSTRAINTS} clause into a {@code DROP TABLE} statement.
     *
     * @return <code>this</code> for fluent programming
     */
    public DropTable cascadeConstraints() {
        this.cascadeConstraints = new CascadeConstraints(this);
        return this;
    }

    protected String getTableName() {
        return this.table.getName();
    }

    /**
     * Check if the {@code IF EXISTS} clause is present.
     *
     * @return {@code true} if {@code IF EXISTS} clause is present
     */
    public boolean hasIfExistsModifier() {
        return this.ifExists;
    }

    protected CascadeConstraints getCascadeConstraints() {
        return this.cascadeConstraints;
    }

    @Override
    public void accept(final DropTableVisitor visitor) {
        visitor.visit(this);
        this.table.accept(visitor);
        if (this.cascadeConstraints != null) {
            this.cascadeConstraints.accept(visitor);
        }
    }
}