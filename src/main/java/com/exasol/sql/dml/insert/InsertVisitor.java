package com.exasol.sql.dml.insert;

import com.exasol.sql.SqlStatement;
import com.exasol.sql.TableValuesVisitor;

public interface InsertVisitor extends TableValuesVisitor {
    public void visit(SqlStatement insert);

    public void visit(InsertFields insertFields);

    public void leave(InsertFields insertFields);
}