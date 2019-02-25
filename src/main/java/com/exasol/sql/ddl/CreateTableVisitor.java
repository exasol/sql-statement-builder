package com.exasol.sql.ddl;

import com.exasol.datatype.Boolean;
import com.exasol.datatype.*;
import com.exasol.sql.Column;
import com.exasol.sql.FragmentVisitor;

public interface CreateTableVisitor extends FragmentVisitor {
    public void visit(final CreateTable createTable);

    public void visit(final Column column);

    public void visit(final ColumnsDefinition columnsDefinition);

    public void leave(final ColumnsDefinition columnsDefinition);

    public void visit(final Char charColumn);

    public void visit(final Varchar varcharColumn);

    public void visit(final Boolean booleanColumn);

    public void visit(final Date dateColumn);

    public void visit(final Decimal decimalColumn);
}
