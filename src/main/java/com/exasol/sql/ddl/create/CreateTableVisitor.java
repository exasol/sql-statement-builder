package com.exasol.sql.ddl.create;

import com.exasol.datatype.type.*;
import com.exasol.datatype.type.Boolean;
import com.exasol.sql.Table;

public interface CreateTableVisitor {
    public void visit(final Table table);

    public void visit(final CreateTable createTable);

    public void visit(final Column column);

    public void visit(final ColumnsDefinition columnsDefinition);

    public void leave(final ColumnsDefinition columnsDefinition);

    public void visit(final Char charColumn);

    public void visit(final Varchar varcharColumn);

    public void visit(final Boolean booleanColumn);

    public void visit(final Date dateColumn);

    public void visit(final Decimal decimalColumn);

    public void visit(DoublePrecision doublePrecision);

    public void visit(Timestamp timestamp);

    public void visit(TimestampWithLocalTimezone timestampWithLocalTimezone);

    public void visit(IntervalDayToSecond intervalDayToSecond);

    public void visit(IntervalYearToMonth intervalYearToMonth);
}
