package com.exasol.sql;

public interface TableValuesVisitor {
    public void visit(ValueTable valueTable);

    public void leave(ValueTable valueTable);

    public void visit(ValueTableRow valueTableRow);

    public void leave(ValueTableRow valueTableRow);
}