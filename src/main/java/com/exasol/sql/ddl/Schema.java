package com.exasol.sql.ddl;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;
import com.exasol.sql.ddl.create.CreateSchemaVisitor;

/**
 * This class represents a {@link Schema} in an SQL Statement
 */
public class Schema extends AbstractFragment {
    private String name;

    /**
     * Create a new {@link Schema}
     *
     * @param root       SQL statement this schema belongs to
     * @param schemaName schema name
     */
    public Schema(Fragment root, String schemaName) {
        super(root);
        this.name = schemaName;
    }

    /**
     * Get a schema name
     *
     * @return schema name
     */
    public String getName() {
        return name;
    }

    public void accept(CreateSchemaVisitor visitor) {
        visitor.visit(this);
    }
}
