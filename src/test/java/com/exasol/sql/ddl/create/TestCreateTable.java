package com.exasol.sql.ddl.create;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.datatype.type.*;
import com.exasol.datatype.type.Boolean;
import com.exasol.sql.StatementFactory;

class TestCreateTable {
    private static final int LENGTH = 20;
    private static final String TEST_TABLE_NAME = "test table name";
    private static final String BOOLEAN_COLUMN_NAME = "booleanColumn";
    private static final String CHAR_COLUMN_NAME = "charColumn";
    private static final String DATE_COLUMN_NAME = "booleanColumn";
    private static final String VARCHAR_COLUMN_NAME = "varcharColumn";
    private static final String DECIMAL_COLUMN_NAME = "decimalColumn";
    private static final int PRECISION = 18;
    private static final int SCALE = 0;
    private static final String DOUBLE_PRECISION_COLUMN_NAME = "doublePrecisionColumn";
    private static final String TIMESTAMP_COLUMN_NAME = "timestampColumn";
    private static final String TIMESTAMP_WITH_LOCAL_TIMEZONE_COLUMN_NAME = "timestampWithLocalTimeZoneColumn";
    private static final String INTERVAL_DAY_TO_SECOND_COLUMN_NAME = "intervalDayToSecondColumn";
    private static final String INTERVAL_YEAR_TO_MONTH_COLUMN_NAME = "intervalYearToMonthColumn";
    private CreateTable createTable;

    @BeforeEach
    void beforeEach() {
        this.createTable = StatementFactory.getInstance().createTable(TEST_TABLE_NAME);
    }

    @Test
    void getTableName() {
        assertThat(this.createTable.getTableName(), equalTo(TEST_TABLE_NAME));
    }

    @Test
    void testCreateTableWithBooleanColumn() {
        final Column column = this.createTable.booleanColumn(BOOLEAN_COLUMN_NAME).getColumns().getColumns().get(0);
        assertInstance(column, BOOLEAN_COLUMN_NAME, Boolean.class);
    }

    private void assertInstance(final Column column, final String name, final Class<?> classToAssert) {
        assertAll(() -> assertThat(column.getColumnName(), equalTo(name)),
                () -> assertThat(column.getDataType(), instanceOf(classToAssert)));
    }

    @Test
    void testCreateTableWithCharColumn() {
        final Column column = this.createTable.charColumn(CHAR_COLUMN_NAME, LENGTH).getColumns().getColumns().get(0);
        assertInstance(column, CHAR_COLUMN_NAME, Char.class);
    }

    @Test
    void testCreateTableWithDateColumn() {
        final Column column = this.createTable.dateColumn(DATE_COLUMN_NAME).getColumns().getColumns().get(0);
        assertInstance(column, DATE_COLUMN_NAME, Date.class);
    }

    @Test
    void testCreateTableWithVarcharColumn() {
        final Column column = this.createTable.varcharColumn(VARCHAR_COLUMN_NAME, LENGTH).getColumns().getColumns()
                .get(0);
        assertInstance(column, VARCHAR_COLUMN_NAME, Varchar.class);
    }

    @Test
    void testCreateTableWithDecimalColumn() {
        final Column column = this.createTable.decimalColumn(DECIMAL_COLUMN_NAME, PRECISION, SCALE).getColumns()
                .getColumns().get(0);
        assertInstance(column, DECIMAL_COLUMN_NAME, Decimal.class);
    }

    @Test
    void testCreateTableWithDoublePrecisionColumn() {
        final Column column = this.createTable.doublePrecisionColumn(DOUBLE_PRECISION_COLUMN_NAME).getColumns()
                .getColumns().get(0);
        assertInstance(column, DOUBLE_PRECISION_COLUMN_NAME, DoublePrecision.class);
    }

    @Test
    void testCreateTableWithTimestampColumn() {
        final Column column = this.createTable.timestampColumn(TIMESTAMP_COLUMN_NAME).getColumns().getColumns().get(0);
        assertInstance(column, TIMESTAMP_COLUMN_NAME, Timestamp.class);
    }

    @Test
    void testCreateTableWithTimestampWithLocalTimeZoneColumn() {
        final Column column = this.createTable
                .timestampWithLocalTimeZoneColumn(TIMESTAMP_WITH_LOCAL_TIMEZONE_COLUMN_NAME).getColumns().getColumns()
                .get(0);
        assertInstance(column, TIMESTAMP_WITH_LOCAL_TIMEZONE_COLUMN_NAME, TimestampWithLocalTimezone.class);
    }

    @Test
    void testCreateTableWithIntervalDayToSecondColumn() {
        final Column column = this.createTable.intervalDayToSecondColumn(INTERVAL_DAY_TO_SECOND_COLUMN_NAME, 2, 2)
                .getColumns().getColumns().get(0);
        assertInstance(column, INTERVAL_DAY_TO_SECOND_COLUMN_NAME, IntervalDayToSecond.class);
    }

    @Test
    void testCreateTableWithIntervalYearToMonthColumn() {
        final Column column = this.createTable.intervalYearToMonthColumn(INTERVAL_YEAR_TO_MONTH_COLUMN_NAME, 2)
                .getColumns().getColumns().get(0);
        assertInstance(column, INTERVAL_YEAR_TO_MONTH_COLUMN_NAME, IntervalYearToMonth.class);
    }
}