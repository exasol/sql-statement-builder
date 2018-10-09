package com.exasol.sql.dml;

import com.exasol.sql.FragmentVisitor;

public interface InsertVisitor extends FragmentVisitor {
    public void visit(Insert insert);

    public void visit(InsertFields insertFields);

    public void leave(InsertFields insertFields);

    public void visit(InsertValues insertValues);

    public void leave(InsertValues insertValues);
}