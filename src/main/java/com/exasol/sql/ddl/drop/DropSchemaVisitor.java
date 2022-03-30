package com.exasol.sql.ddl.drop;

import com.exasol.sql.ddl.Schema;

/**
 * Visitor for {@code DROP SCHEMA} statements.
 */
public interface DropSchemaVisitor {
    /**
     * Visit a {@code DROP SCHEMA} statement.
     *
     * @param dropSchema {@code DROP SCHEMA} statement to visit
     */
    public void visit(final DropSchema dropSchema);

    /**
     * Visit the name of the schema to drop.
     *
     * @param schema name of the schema to visit
     */
    public void visit(final Schema schema);

    /**
     * Visit the {@code CASCADE} option.
     *
     * @param cascade {@code CASCADE} option to visit
     */
    public void visit(final Cascade cascade);

    /**
     * Visit the {@code RESTRICT} option.
     *
     * @param restrict {@code RESTRICT} option to visit
     */
    public void visit(final Restrict restrict);
}
