package com.exasol.sql;

import com.exasol.sql.dql.ValueTable;
import com.exasol.sql.dql.ValueTableRow;

/**
 * This interface represents a visitor for SQL statement fragments.
 */
public interface FragmentVisitor {
    public void visit(final Field field);

    public void visit(Table table);

    public void visit(ValueTable valueTable);

    public void leave(ValueTable valueTable);

    public void visit(ValueTableRow valueTableRow);

    public void leave(ValueTableRow valueTableRow);
}