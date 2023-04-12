package com.exasol.sql.ddl.drop;

import com.exasol.sql.Table;

/**
 * Visitor for {@code DROP TABLE} statements.
 */
public interface DropTableVisitor {
    /**
     * Visit a {@code DROP TABLE} statement.
     *
     * @param dropTable {@code DROP TABLE} statement to visit
     */
    public void visit(final DropTable dropTable);

    /**
     * Visit the {@code TABLE} to be dropped.
     *
     * @param table {@code TABLE} to visit
     */
    public void visit(final Table table);

    /**
     * Visit the {@code CASCADE CONSTRAINTS} option.
     *
     * @param cascadeConstraints {@code CASCADE CONSTRAINTS} option to visit
     */
    public void visit(final CascadeConstraints cascadeConstraints);
}
