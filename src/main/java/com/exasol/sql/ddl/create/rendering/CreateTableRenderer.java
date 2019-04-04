package com.exasol.sql.ddl.create.rendering;

import com.exasol.datatype.type.Boolean;
import com.exasol.datatype.type.*;
import com.exasol.sql.Column;
import com.exasol.sql.Field;
import com.exasol.sql.Table;
import com.exasol.sql.ddl.ColumnsDefinition;
import com.exasol.sql.ddl.create.CreateTable;
import com.exasol.sql.ddl.create.CreateTableVisitor;
import com.exasol.sql.rendering.AbstractFragmentRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * The {@link CreateTableRenderer} turns SQL statement structures in to SQL strings.
 */
public class CreateTableRenderer extends AbstractFragmentRenderer implements CreateTableVisitor {
    /**
     * Create a new {@link CreateTableRenderer} with custom render settings.
     *
     * @param config render configuration settings
     */
    public CreateTableRenderer(final StringRendererConfig config) {
        super(config);
    }

    /**
     * Create an {@link CreateTableRenderer} using the default renderer configuration
     *
     * @return insert renderer
     */
    public static CreateTableRenderer create() {
        return new CreateTableRenderer(StringRendererConfig.createDefault());
    }

    /**
     * Create an {@link CreateTableRenderer}
     *
     * @param config renderer configuration
     * @return create table renderer
     */
    public static CreateTableRenderer create(final StringRendererConfig config) {
        return new CreateTableRenderer(config);
    }

    @Override
    public void visit(final CreateTable createTable) {
        appendKeyWord("CREATE TABLE ");
        setLastVisited(createTable);
    }

    @Override
    public void visit(final Column column) {
        appendCommaWhenNeeded(column);
        appendAutoQuoted(column.getColumnName());
        setLastVisited(column);
    }

    @Override
    public void visit(final ColumnsDefinition columnsDefinition) {
        append(" (");
        setLastVisited(columnsDefinition);
    }

    @Override
    public void leave(final ColumnsDefinition columnsDefinition) {
        append(")");
        setLastVisited(columnsDefinition);
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

    @Override
    public void visit(final Field field) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(final Table table) {
        appendAutoQuoted(table.getName());
        setLastVisited(table);
    }

    private String getIntervalDayToSecondNameWithPrecision(
          final IntervalDayToSecond intervalDayToSecondColumn) {
        return String.format(intervalDayToSecondColumn.getName(),
              intervalDayToSecondColumn.getYearPrecision(),
              intervalDayToSecondColumn.getMillisecondPrecision());
    }

    private String getIntervalYearToMonthNameWithPrecision(
          final IntervalYearToMonth intervalYearToMonthColumn) {
        return String.format(intervalYearToMonthColumn.getName(),
              intervalYearToMonthColumn.getYearPrecision());
    }

    private void appendDataTypeWithoutParameters(final DataType dataType) {
        appendSpace();
        append(dataType.getName());
    }

    private void appendStringDataType(final AbstractStringDataType stringDataType) {
        appendSpace();
        append(stringDataType.getName());
        append("(");
        append(stringDataType.getLength());
        append(")");
    }
}
