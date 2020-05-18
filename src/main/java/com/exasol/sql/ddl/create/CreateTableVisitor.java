package com.exasol.sql.ddl.create;

import com.exasol.sql.Table;

public interface CreateTableVisitor {
    public void visit(final Table table);

    public void visit(final CreateTable createTable);

    public void leave(final CreateTable createTable);
}
