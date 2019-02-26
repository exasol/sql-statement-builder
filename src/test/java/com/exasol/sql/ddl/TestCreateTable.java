package com.exasol.sql.ddl;

import com.exasol.datatype.Boolean;
import com.exasol.datatype.*;
import com.exasol.sql.Column;
import com.exasol.sql.StatementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

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
    private static final String TIMESTAMP_WITH_LOCAL_TIMEZONE_COLUMN_NAME =
          "timestampWithLocalTimeZoneColumn";
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
        final Column column =
              this.createTable.booleanColumn(BOOLEAN_COLUMN_NAME).getColumns().getColumns().get(0);
        assertThat(column.getColumnName(), equalTo(BOOLEAN_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(Boolean.class));
    }

    @Test
    void testCreateTableWithCharColumn() {
        final Column column =
              this.createTable.charColumn(CHAR_COLUMN_NAME, LENGTH).getColumns().getColumns()
                    .get(0);
        assertThat(column.getColumnName(), equalTo(CHAR_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(Char.class));
    }

    @Test
    void testCreateTableWithDateColumn() {
        final Column column =
              this.createTable.dateColumn(DATE_COLUMN_NAME).getColumns().getColumns().get(0);
        assertThat(column.getColumnName(), equalTo(DATE_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(Date.class));
    }

    @Test
    void testCreateTableWithVarcharColumn() {
        final Column column =
              this.createTable.varcharColumn(VARCHAR_COLUMN_NAME, LENGTH).getColumns().getColumns()
                    .get(0);
        assertThat(column.getColumnName(), equalTo(VARCHAR_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(Varchar.class));
    }

    @Test
    void testCreateTableWithDecimalColumn() {
        final Column column =
              this.createTable.decimalColumn(DECIMAL_COLUMN_NAME, PRECISION, SCALE).getColumns()
                    .getColumns().get(0);
        assertThat(column.getColumnName(), equalTo(DECIMAL_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(Decimal.class));
    }

    @Test
    void testCreateTableWithDoublePrecisionColumn() {
        final Column column =
              this.createTable.doublePrecisionColumn(DOUBLE_PRECISION_COLUMN_NAME).getColumns()
                    .getColumns().get(0);
        assertThat(column.getColumnName(), equalTo(DOUBLE_PRECISION_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(DoublePrecision.class));
    }

    @Test
    void testCreateTableWithTimestampColumn() {
        final Column column =
              this.createTable.timestampColumn(TIMESTAMP_COLUMN_NAME).getColumns().getColumns()
                    .get(0);
        assertThat(column.getColumnName(), equalTo(TIMESTAMP_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(Timestamp.class));
    }

    @Test
    void testCreateTableWithTimestampWithLocalTimeZoneColumn() {
        final Column column =
              this.createTable.timestampWithLocalTimeZoneColumn(TIMESTAMP_WITH_LOCAL_TIMEZONE_COLUMN_NAME)
                    .getColumns().getColumns().get(0);
        assertThat(column.getColumnName(), equalTo(TIMESTAMP_WITH_LOCAL_TIMEZONE_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(TimestampWithLocalTimezone.class));
    }
}