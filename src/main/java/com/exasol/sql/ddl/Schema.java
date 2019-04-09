package com.exasol.sql.ddl;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;
import com.exasol.sql.ddl.create.CreateSchemaVisitor;
import com.exasol.sql.ddl.drop.DropSchemaVisitor;

/**
 * This class represents a {@link Schema} in an SQL Statement
 */
public class Schema extends AbstractFragment {
    private final String name;

    /**
     * Create a new {@link Schema}
     *
     * @param root       SQL statement this schema belongs to
     * @param schemaName schema name
     */
    public Schema(final Fragment root, final String schemaName) {
        super(root);
        this.name = schemaName;
    }

    /**
     * Get the schema name
     *
     * @return schema name
     */
    public String getName() {
        return name;
    }

    public void accept(final CreateSchemaVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(final DropSchemaVisitor visitor) {
        visitor.visit(this);
    }
}
