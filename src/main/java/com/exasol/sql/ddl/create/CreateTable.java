package com.exasol.sql.ddl.create;

import com.exasol.datatype.type.Boolean;
import com.exasol.datatype.type.*;
import com.exasol.sql.*;

/**
 * This class implements an SQL {@link CreateTable} statement
 */
public class CreateTable extends AbstractFragment implements SqlStatement, CreateTableFragment {
    private ColumnsDefinition columnsDefinition;
    private final Table table;

    /**
     * Create a new instance of an {@link CreateTable} statement
     *
     * @param tableName name of the table to create
     */
    public CreateTable(final String tableName) {
        super(null);
        this.table = new Table(this, tableName);
    }

    /**
     * Add boolean column
     *
     * @param columnName name of the column to be added
     * @return <code>this</code> for fluent programming
     */
    public synchronized CreateTable booleanColumn(final String columnName) {
        checkIfCreateTableColumnsExists();
        this.columnsDefinition.add(columnName, new Boolean());
        return this;
    }

    /**
     * Add char column
     *
     * @param columnName name of the column to be added
     * @param length     pre-defined length for stored strings
     * @return <code>this</code> for fluent programming
     */
    public synchronized CreateTable charColumn(final String columnName, final int length) {
        checkIfCreateTableColumnsExists();
        this.columnsDefinition.add(columnName, new Char(length));
        return this;
    }

    /**
     * Add varchar column
     *
     * @param columnName name of the column to be added
     * @param length     pre-defined length for stored strings
     * @return <code>this</code> for fluent programming
     */
    public synchronized CreateTable varcharColumn(final String columnName, final int length) {
        checkIfCreateTableColumnsExists();
        this.columnsDefinition.add(columnName, new Varchar(length));
        return this;
    }

    /**
     * Add date column
     *
     * @param columnName name of the column to be added
     * @return <code>this</code> for fluent programming
     */
    public synchronized CreateTable dateColumn(final String columnName) {
        checkIfCreateTableColumnsExists();
        this.columnsDefinition.add(columnName, new Date());
        return this;
    }

    /**
     * Add decimal column
     *
     * @param columnName name of the column to be added
     * @param precision  precision for numeric value
     * @param scale      scale for numeric value
     * @return <code>this</code> for fluent programming
     */
    public synchronized CreateTable decimalColumn(final String columnName, final int precision, final int scale) {
        checkIfCreateTableColumnsExists();
        this.columnsDefinition.add(columnName, new Decimal(precision, scale));
        return this;
    }

    /**
     * Add double precision column
     *
     * @param columnName name of the column to be added
     * @return <code>this</code> for fluent programming
     */
    public synchronized CreateTable doublePrecisionColumn(final String columnName) {
        checkIfCreateTableColumnsExists();
        this.columnsDefinition.add(columnName, new DoublePrecision());
        return this;
    }

    /**
     * Add timestamp column
     *
     * @param columnName name of the column to be added
     * @return <code>this</code> for fluent programming
     */
    public synchronized CreateTable timestampColumn(final String columnName) {
        checkIfCreateTableColumnsExists();
        this.columnsDefinition.add(columnName, new Timestamp());
        return this;
    }

    /**
     * Add timestamp with local time zone column
     *
     * @param columnName name of the column to be added
     * @return <code>this</code> for fluent programming
     */
    public synchronized CreateTable timestampWithLocalTimeZoneColumn(final String columnName) {
        checkIfCreateTableColumnsExists();
        this.columnsDefinition.add(columnName, new TimestampWithLocalTimezone());
        return this;
    }

    /**
     * Add interval day to second column
     *
     * @param columnName           name of the column to be added
     * @param yearPrecision        year precision value
     * @param millisecondPrecision millisecond precision value
     * @return <code>this</code> for fluent programming
     */
    public synchronized CreateTable intervalDayToSecondColumn(final String columnName, final int yearPrecision,
          final int millisecondPrecision) {
        checkIfCreateTableColumnsExists();
        this.columnsDefinition.add(columnName, new IntervalDayToSecond(yearPrecision, millisecondPrecision));
        return this;
    }

    /**
     * Add interval year to month column
     *
     * @param columnName    name of the column to be added
     * @param yearPrecision year precision value
     * @return <code>this</code> for fluent programming
     */
    public synchronized CreateTable intervalYearToMonthColumn(final String columnName, final int yearPrecision) {
        checkIfCreateTableColumnsExists();
        this.columnsDefinition.add(columnName, new IntervalYearToMonth(yearPrecision));
        return this;
    }

    /**
     * Get a table name
     *
     * @return table name
     */
    public String getTableName() {
        return this.table.getName();
    }

    /**
     * Get columns of a table
     *
     * @return columns
     */
    public ColumnsDefinition getColumns() {
        return this.columnsDefinition;
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
        this.table.accept(visitor);
        if (this.columnsDefinition != null) {
            this.columnsDefinition.accept(visitor);
        }
    }

    private void checkIfCreateTableColumnsExists() {
        if (this.columnsDefinition == null) {
            this.columnsDefinition = new ColumnsDefinition(this);
        }
    }
}
