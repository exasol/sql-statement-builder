package com.exasol.sql;

import com.exasol.sql.dql.ValueTable;
import com.exasol.sql.dql.ValueTableRow;

public interface TableValuesVisitor {
    public void visit(Table table);

    public void visit(Field field);

    public void visit(ValueTable valueTable);

    public void leave(ValueTable valueTable);

    public void visit(ValueTableRow valueTableRow);

    public void leave(ValueTableRow valueTableRow);
}