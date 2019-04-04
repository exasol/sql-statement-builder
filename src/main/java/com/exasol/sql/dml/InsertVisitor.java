package com.exasol.sql.dml;

import com.exasol.sql.TableValuesVisitor;

public interface InsertVisitor extends TableValuesVisitor {
    public void visit(Insert insert);

    public void visit(InsertFields insertFields);

    public void leave(InsertFields insertFields);
}