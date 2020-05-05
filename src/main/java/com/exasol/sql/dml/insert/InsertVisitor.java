package com.exasol.sql.dml.insert;

import com.exasol.sql.*;

public interface InsertVisitor extends ValueTableVisitor {
    public void visit(SqlStatement insert);

    public void visit(Table table);

    public void visit(InsertFields insertFields);

    public void leave(InsertFields insertFields);

    public void visit(DerivedColumn derivedColumn);
}