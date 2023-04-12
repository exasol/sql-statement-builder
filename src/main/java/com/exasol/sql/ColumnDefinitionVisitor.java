package com.exasol.sql;

import com.exasol.datatype.type.*;
import com.exasol.datatype.type.Boolean;

/**
 * Visit the definition of a column.
 */
public interface ColumnDefinitionVisitor {
    /**
     * Visit a set of column definitions.
     *
     * @param columnsDefinition column definitions to visit.
     */
    public void visit(final ColumnsDefinition columnsDefinition);

    /**
     * Visit a single column.
     *
     * @param column to visit
     */
    public void visit(final Column column);

    /**
     * Visit a character column.
     *
     * @param charColumn character column to visit
     */
    public void visit(final Char charColumn);

    /**
     * Visit a variable-length character column.
     *
     * @param varcharColumn variable-length character column to visit
     */
    public void visit(final Varchar varcharColumn);

    /**
     * Visit a boolean column.
     *
     * @param booleanColumn boolean column to visit
     */
    public void visit(final Boolean booleanColumn);

    /**
     * Visit a date column.
     *
     * @param dateColumn date column to visit
     */
    public void visit(final Date dateColumn);

    /**
     * Visit a decimal column.
     *
     * @param decimalColumn decimal column to visit
     */
    public void visit(final Decimal decimalColumn);

    /**
     * Visit a double-precision floating point column.
     *
     * @param doublePrecision double-precision floating point column to visit
     */
    public void visit(final DoublePrecision doublePrecision);

    /**
     * Visit a timestamp column.
     *
     * @param timestamp timestamp column to visit
     */
    public void visit(final Timestamp timestamp);

    /**
     * Visit a timestamp-with-local-timezone column.
     *
     * @param timestampWithLocalTimezone timestamp-with-local-timezone column to visit
     */
    public void visit(final TimestampWithLocalTimezone timestampWithLocalTimezone);

    /**
     * Visit an interval column with second precision.
     *
     * @param intervalDayToSecond interval column to visit
     */
    public void visit(final IntervalDayToSecond intervalDayToSecond);

    /**
     * Visit an interval column with month precision.
     *
     * @param intervalYearToMonth interval column to visit
     */
    public void visit(final IntervalYearToMonth intervalYearToMonth);
}
