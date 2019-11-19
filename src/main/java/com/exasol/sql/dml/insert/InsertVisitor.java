package com.exasol.sql.dml.insert;

import com.exasol.sql.*;

public interface InsertVisitor extends TableValuesVisitor {
    public void visit(SqlStatement insert);

    public void visit(Table table);

    public void visit(InsertFields insertFields);

    public void leave(InsertFields insertFields);

    public void visit(Field field);
}