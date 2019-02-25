package com.exasol.sql;

import com.exasol.sql.dql.ValueTable;
import com.exasol.sql.dql.ValueTableRow;

public interface ValueTableVisitor extends FragmentVisitor {
    @Override
    public void visit(ValueTable valueTable);

    @Override
    public void visit(ValueTableRow valueTableRow);
}