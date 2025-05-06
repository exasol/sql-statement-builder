package com.exasol.sql;

import java.util.ArrayList;
import java.util.List;

import com.exasol.datatype.type.*;
import com.exasol.datatype.type.Boolean;

/**
 * This class represents a list of column definitions in an SQL statement.
 */
public class ColumnsDefinition extends AbstractFragment {
    private List<Column> columns = new ArrayList<>();

    /**
     * Create an new instance of {@link ColumnsDefinition}.
     *
     * @param root root statement
     */
    public ColumnsDefinition(final SqlStatement root) {
        super(root);
    }

    /**
     * Create an new instance of {@link ColumnsDefinition}.
     *
     * @param columns list of columns
     */
    public ColumnsDefinition(final List<Column> columns) {
        super(null);
        this.columns = columns;
    }

    /**
     * Create a new builder for {@link ColumnsDefinition}.
     *
     * @return new {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Add a new column to the {@link ColumnsDefinition}
     *
     * @param name     name of the column to be added
     * @param dataType data type of the column to be added
     */
    public void add(final String name, final DataType dataType) {
        this.columns.add(new Column(this, name, dataType));
    }

    /**
     * Get a list of columns.
     *
     * @return list of columns
     */
    public List<Column> getColumns() {
        return this.columns;
    }

    /**
     * Accept a {@link ColumnDefinitionVisitor}.
     *
     * @param visitor visitor to accept
     */
    public void accept(final ColumnDefinitionVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * A builder for {@link ColumnsDefinition}.
     */
    public static class Builder {
        private final List<Column> columns = new ArrayList<>();

        /**
         * Add boolean column
         *
         * @param columnName name of the column to be added
         * @return {@code this} for fluent programming
         */
        public Builder booleanColumn(final String columnName) {
            this.columns.add(new Column(null, columnName, new Boolean()));
            return this;
        }

        /**
         * Add char column.
         *
         * @param columnName name of the column to be added
         * @param length     pre-defined length for stored strings
         * @return {@code this} for fluent programming
         */
        public Builder charColumn(final String columnName, final int length) {
            this.columns.add(new Column(null, columnName, new Char(length)));
            return this;
        }

        /**
         * Add varchar column.
         *
         * @param columnName name of the column to be added
         * @param length     pre-defined length for stored strings
         * @return {@code this} for fluent programming
         */
        public Builder varcharColumn(final String columnName, final int length) {
            this.columns.add(new Column(null, columnName, new Varchar(length)));
            return this;
        }

        /**
         * Add date column.
         *
         * @param columnName name of the column to be added
         * @return {@code this} for fluent programming
         */
        public Builder dateColumn(final String columnName) {
            this.columns.add(new Column(null, columnName, new Date()));
            return this;
        }

        /**
         * Add decimal column.
         *
         * @param columnName name of the column to be added
         * @param precision  precision for numeric value
         * @param scale      scale for numeric value
         * @return {@code this} for fluent programming
         */
        public Builder decimalColumn(final String columnName, final int precision, final int scale) {
            this.columns.add(new Column(null, columnName, new Decimal(precision, scale)));
            return this;
        }

        /**
         * Add double precision column.
         *
         * @param columnName name of the column to be added
         * @return {@code this} for fluent programming
         */
        public Builder doublePrecisionColumn(final String columnName) {
            this.columns.add(new Column(null, columnName, new DoublePrecision()));
            return this;
        }

        /**
         * Add timestamp column.
         *
         * @param columnName name of the column to be added
         * @return {@code this} for fluent programming
         */
        public Builder timestampColumn(final String columnName) {
            this.columns.add(new Column(null, columnName, new Timestamp()));
            return this;
        }

        /**
         * Add timestamp column with the specified fractional seconds precision.
         *
         * @param columnName name of the column to be added
         * @param precision  precision for numeric value
         * @return {@code this} for fluent programming
         */
        public Builder timestampColumn(final String columnName, final int precision) {
            this.columns.add(new Column(null, columnName, new Timestamp(precision)));
            return this;
        }

        /**
         * Add timestamp with local time zone column.
         *
         * @param columnName name of the column to be added
         * @return {@code this} for fluent programming
         */
        public Builder timestampWithLocalTimeZoneColumn(final String columnName) {
            this.columns.add(new Column(null, columnName, new TimestampWithLocalTimezone()));
            return this;
        }

        /**
         * Add timestamp with local time zone column with the specified fractional seconds precision.
         *
         * @param columnName name of the column to be added
         * @param precision  precision for numeric value
         * @return {@code this} for fluent programming
         */
        public Builder timestampWithLocalTimeZoneColumn(final String columnName, final int precision) {
            this.columns.add(new Column(null, columnName, new TimestampWithLocalTimezone(precision)));
            return this;
        }

        /**
         * Add interval day to second column.
         *
         * @param columnName           name of the column to be added
         * @param yearPrecision        year precision value
         * @param millisecondPrecision millisecond precision value
         * @return {@code this} for fluent programming
         */
        public Builder intervalDayToSecondColumn(final String columnName, final int yearPrecision,
                final int millisecondPrecision) {
            this.columns
                    .add(new Column(null, columnName, new IntervalDayToSecond(yearPrecision, millisecondPrecision)));
            return this;
        }

        /**
         * Add interval year to month column.
         *
         * @param columnName    name of the column to be added
         * @param yearPrecision year precision value
         * @return {@code this} for fluent programming
         */
        public Builder intervalYearToMonthColumn(final String columnName, final int yearPrecision) {
            this.columns.add(new Column(null, columnName, new IntervalYearToMonth(yearPrecision)));
            return this;
        }

        /**
         * Build a new {@link ColumnsDefinition}.
         *
         * @return new {@link ColumnsDefinition}
         */
        public ColumnsDefinition build() {
            return new ColumnsDefinition(this.columns);
        }
    }
}