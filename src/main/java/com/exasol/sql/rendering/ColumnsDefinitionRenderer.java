package com.exasol.sql.rendering;

import java.util.List;

import com.exasol.datatype.type.*;
import com.exasol.datatype.type.Boolean;
import com.exasol.sql.*;

/**
 * Renderer for columns definition.
 */
public class ColumnsDefinitionRenderer extends AbstractFragmentRenderer implements ColumnDefinitionVisitor {
    /**
     * Create a new instance of an {@link AbstractFragmentRenderer}-based class.
     *
     * @param config renderer configuration
     */
    public ColumnsDefinitionRenderer(final StringRendererConfig config) {
        super(config);
    }

    @Override
    public void visit(final ColumnsDefinition columnsDefinition) {
        final List<Column> columns = columnsDefinition.getColumns();
        if (!columns.isEmpty()) {
            append("(");
            columns.forEach(col -> col.accept(this));
            append(")");
        }
        setLastVisited(columnsDefinition);
    }

    @Override
    public void visit(final Column column) {
        appendCommaWhenNeeded(column);
        appendAutoQuoted(column.getColumnName());
        setLastVisited(column);
    }

    @Override
    public void visit(final Char charColumn) {
        appendStringDataType(charColumn);
    }

    @Override
    public void visit(final Varchar varcharColumn) {
        appendStringDataType(varcharColumn);
    }

    @Override
    public void visit(final Boolean booleanColumn) {
        appendDataTypeWithoutParameters(booleanColumn);
    }

    @Override
    public void visit(final Date dateColumn) {
        appendDataTypeWithoutParameters(dateColumn);
    }

    @Override
    public void visit(final Decimal decimalColumn) {
        appendSpace();
        append(decimalColumn.getName());
        append("(");
        append(decimalColumn.getPrecision());
        append(",");
        append(decimalColumn.getScale());
        append(")");
    }

    @Override
    public void visit(final DoublePrecision doublePrecisionColumn) {
        appendDataTypeWithoutParameters(doublePrecisionColumn);
    }

    @Override
    public void visit(final Timestamp timestampColumn) {
        appendDataTypeWithoutParameters(timestampColumn);
    }

    @Override
    public void visit(final TimestampWithLocalTimezone timestampWithLocalTimezoneColumn) {
        appendDataTypeWithoutParameters(timestampWithLocalTimezoneColumn);
    }

    @Override
    public void visit(final IntervalDayToSecond intervalDayToSecondColumn) {
        appendSpace();
        append(getIntervalDayToSecondNameWithPrecision(intervalDayToSecondColumn));
    }

    @Override
    public void visit(final IntervalYearToMonth intervalYearToMonthColumn) {
        appendSpace();
        append(getIntervalYearToMonthNameWithPrecision(intervalYearToMonthColumn));
    }

    private String getIntervalDayToSecondNameWithPrecision(final IntervalDayToSecond intervalDayToSecondColumn) {
        return String.format(intervalDayToSecondColumn.getName(), intervalDayToSecondColumn.getYearPrecision(),
                intervalDayToSecondColumn.getMillisecondPrecision());
    }

    private String getIntervalYearToMonthNameWithPrecision(final IntervalYearToMonth intervalYearToMonthColumn) {
        return String.format(intervalYearToMonthColumn.getName(), intervalYearToMonthColumn.getYearPrecision());
    }

    private void appendDataTypeWithoutParameters(final DataType dataType) {
        appendSpace();
        append(dataType.getName());
    }

    private void appendStringDataType(
            final AbstractStringDataType<? extends AbstractStringDataType<?>> stringDataType) {
        appendSpace();
        append(stringDataType.getName());
        append("(");
        append(stringDataType.getLength());
        append(")");
    }
}