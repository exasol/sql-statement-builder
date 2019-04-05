package com.exasol.sql.ddl.drop;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.SqlStatement;
import com.exasol.sql.ddl.Schema;

/**
 * This class implements an SQL {@link DropSchema} statement
 */
public class DropSchema extends AbstractFragment implements SqlStatement, DropSchemaFragment {
    private Schema schema;
    private Cascade cascade;
    private Restrict restrict;
    private boolean ifExists = false;

    /**
     * Create a new instance of an {@link DropSchema} statement
     *
     * @param schemaName name of the table to drop
     */
    public DropSchema(final String schemaName) {
        super(null);
        this.schema = new Schema(this, schemaName);
    }

    /**
     * Add IF EXISTS clause into a DROP SCHEMA statement
     *
     * @return <code>this</code> for fluent programming
     */
    public synchronized DropSchema ifExists() {
        if (!this.ifExists) {
            this.ifExists = true;
        }
        return this;
    }

    /**
     * Add CASCADE clause into a DROP SCHEMA statement
     *
     * @return <code>this</code> for fluent programming
     */
    public synchronized DropSchema cascade() {
        cascade = new Cascade(this);
        return this;
    }

    /**
     * Add RESTRICT clause into a DROP SCHEMA statement
     *
     * @return <code>this</code> for fluent programming
     */
    public synchronized DropSchema restrict() {
        restrict = new Restrict(this);
        return this;
    }

    /**
     * Get a schema name
     *
     * @return schema name
     */
    public String getSchemaName() {
        return schema.getName();
    }

    public Cascade getCascade() {
        return cascade;
    }

    public Restrict getRestrict() {
        return restrict;
    }

    @Override
    public void accept(DropSchemaVisitor visitor) {
        validateCascadeAndRestrict();
        visitor.visit(this);
        this.schema.accept(visitor);
        if (cascade != null) {
            cascade.accept(visitor);
        }
        if (restrict != null) {
            restrict.accept(visitor);
        }
    }

    /**
     * Get true when IF EXISTS clause presents
     *
     * @return if exists
     */
    public boolean getIfExists() {
        return ifExists;
    }

    private void validateCascadeAndRestrict() {
        if (cascade != null && restrict != null) {
            throw new IllegalArgumentException(
                  "DROP SCHEMA expression must not contain CASCADE and RESTRICT clauses at the came time. "
                        + "Use only one of them.");
        }
    }
}
