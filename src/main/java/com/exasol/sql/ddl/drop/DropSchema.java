package com.exasol.sql.ddl.drop;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.SqlStatement;
import com.exasol.sql.ddl.Schema;

/**
 * This class implements an SQL {@link DropSchema} statement.
 */
public class DropSchema extends AbstractFragment implements SqlStatement, DropSchemaFragment {
    private final Schema schema;
    private Cascade cascade;
    private Restrict restrict;
    private boolean ifExists = false;

    /**
     * Create a new instance of an {@link DropSchema} statement.
     *
     * @param schemaName name of the table to drop
     */
    public DropSchema(final String schemaName) {
        super(null);
        this.schema = new Schema(this, schemaName);
    }

    /**
     * Add {@code IF EXISTS} clause into a {@code DROP SCHEMA} statement.
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
     * Add {@code CASCADE} clause to a {@code DROP SCHEMA} statement.
     */
    public synchronized void cascade() {
        this.cascade = new Cascade(this);
    }

    /**
     * Add {@code RESTRICT} clause to a {@code DROP SCHEMA} statement.
     */
    public synchronized void restrict() {
        this.restrict = new Restrict(this);
    }

    /**
     * Get the schema name.
     *
     * @return schema name
     */
    public String getSchemaName() {
        return this.schema.getName();
    }

    /**
     * Get the cascade.
     *
     * @return {@link Cascade} object
     */
    public Cascade getCascade() {
        return this.cascade;
    }

    /**
     * Get the restriction.
     *
     * @return {@link Restrict} object
     */
    public Restrict getRestrict() {
        return this.restrict;
    }

    /**
     * Check whether the {@code IF EXISTS} clause is present.
     *
     * @return {@code true} if {@code IF EXISTS} clause is present
     */
    public boolean hasIfExistsModifier() {
        return this.ifExists;
    }

    @Override
    public void accept(final DropSchemaVisitor visitor) {
        validateCascadeAndRestrict();
        visitor.visit(this);
        this.schema.accept(visitor);
        if (this.cascade != null) {
            this.cascade.accept(visitor);
        }
        if (this.restrict != null) {
            this.restrict.accept(visitor);
        }
    }

    private void validateCascadeAndRestrict() {
        if ((this.cascade != null) && (this.restrict != null)) {
            throw new IllegalArgumentException(
                    "DROP SCHEMA expression must not contain CASCADE and RESTRICT clauses at the came time. "
                            + "Use only one of them.");
        }
    }
}