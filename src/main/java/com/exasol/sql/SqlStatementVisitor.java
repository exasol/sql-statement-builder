package com.exasol.sql;

/**
 * This interface represents a visitor for SQL statement fragments.
 */
public interface SqlStatementVisitor {
    public void visit(Table table);
}