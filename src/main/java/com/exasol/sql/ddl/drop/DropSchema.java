package com.exasol.sql.ddl.drop;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.SqlStatement;
import com.exasol.sql.ddl.Schema;

/**
 * This class implements an SQL {@link DropSchema} statement
 */
public class DropSchema extends AbstractFragment implements SqlStatement, DropSchemaFragment {
    private final Schema schema;
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
     * Add CASCADE clause to a DROP SCHEMA statement
     */
    public synchronized void cascade() {
        cascade = new Cascade(this);
    }

    /**
     * Add RESTRICT clause to a DROP SCHEMA statement
     */
    public synchronized void restrict() {
        restrict = new Restrict(this);
    }

    /**
     * Get the schema name
     *
     * @return schema name
     */
    public String getSchemaName() {
        return schema.getName();
    }

    /**
     * Get the cascade
     *
     * @return {@link Cascade} object
     */
    public Cascade getCascade() {
        return cascade;
    }

    /**
     * Get the restrict
     *
     * @return {@link Restrict} object
     */
    public Restrict getRestrict() {
        return restrict;
    }

    @Override
    public void accept(final DropSchemaVisitor visitor) {
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

    private void validateCascadeAndRestrict() {
        if (cascade != null && restrict != null) {
            throw new IllegalArgumentException(
                    "DROP SCHEMA expression must not contain CASCADE and RESTRICT clauses at the came time. "
                            + "Use only one of them.");
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
}
