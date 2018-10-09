package com.exasol.sql;

/**
 * This interface represents a visitor for SQL statement fragments.
 */
public interface FragmentVisitor {
    public void visit(final Field field);

    public void visit(Table table);
}