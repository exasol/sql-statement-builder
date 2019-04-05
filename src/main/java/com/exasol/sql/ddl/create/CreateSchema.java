package com.exasol.sql.ddl.create;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.SqlStatement;
import com.exasol.sql.ddl.Schema;

/**
 * This class implements an SQL {@link CreateSchema} statement
 */
public class CreateSchema extends AbstractFragment implements SqlStatement, CreateSchemaFragment {
    private Schema schema;

    /**
     * Create a new instance of an {@link CreateSchema} statement
     *
     * @param schemaName name of the table to create
     */
    public CreateSchema(final String schemaName) {
        super(null);
        this.schema = new Schema(this, schemaName);
    }

    /**
     * Get a schema name
     *
     * @return schema name
     */
    public String getSchemaName() {
        return schema.getName();
    }

    @Override
    public void accept(CreateSchemaVisitor visitor) {
        visitor.visit(this);
        this.schema.accept(visitor);
    }
}