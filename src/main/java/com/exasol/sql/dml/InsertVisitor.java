package com.exasol.sql.dml;

import com.exasol.sql.FragmentVisitor;
import com.exasol.sql.ValueTableVisitor;

public interface InsertVisitor extends FragmentVisitor, ValueTableVisitor {
    public void visit(Insert insert);

    public void visit(InsertFields insertFields);

    public void leave(InsertFields insertFields);
}