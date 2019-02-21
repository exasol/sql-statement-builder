package com.exasol.sql.ddl;

public interface CreateVisitor {
    public void visit(final Create create);
}
