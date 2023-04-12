package com.exasol.sql.ddl.create;

import com.exasol.sql.ddl.Schema;

/**
 * Visitor for {@code CREATE SCHEMA} statements.
 */
public interface CreateSchemaVisitor {
    /**
     * Visit a {@code CREATE SCHEMA} statement.
     *
     * @param createSchema {@code CREATE SCHEMA} statement to visit
     */
    public void visit(final CreateSchema createSchema);

    /**
     * Visit the schema to be created.
     *
     * @param schema schema to visit
     */
    public void visit(final Schema schema);
}
