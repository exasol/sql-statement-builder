package com.exasol.sql;

import com.exasol.datatype.type.*;
import com.exasol.datatype.type.Boolean;

public interface ColumnDefinitionVisitor {
    public void visit(final ColumnsDefinition columnsDefinition);

    public void leave(final ColumnsDefinition columnsDefinition);

    public void visit(final Column column);

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