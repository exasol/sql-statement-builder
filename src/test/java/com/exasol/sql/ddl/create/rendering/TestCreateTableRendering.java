package com.exasol.sql.ddl.create.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersWithConfigTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ddl.create.CreateTable;
import com.exasol.sql.rendering.StringRendererConfig;

// [utest->dsn~rendering.sql.create~1]
class TestCreateTableRendering {
    private static final String TABLE_NAME = "testName";

    private CreateTable createTable;

    @BeforeEach
    void beforeEach() {
        this.createTable = StatementFactory.getInstance().createTable(TABLE_NAME);
    }

    @Test
    void testCreateTable() {
        assertThat(this.createTable, rendersTo("CREATE TABLE testName"));
    }

    @Test
    void testCreateTableRendersToWithConfig() {
        assertThat(this.createTable,
                rendersWithConfigTo(StringRendererConfig.builder().lowerCase(true).build(), "create table testName"));
    }

    @Test
    void testCreateTableWithBooleanColumn() {
        assertThat(this.createTable.booleanColumn("a"), rendersTo("CREATE TABLE testName (a BOOLEAN)"));
    }

    @Test
    void testCreateTableWithDateColumn() {
        assertThat(this.createTable.dateColumn("a"), rendersTo("CREATE TABLE testName (a DATE)"));
    }

    @Test
    void testCreateTableWithDoublePrecisionColumn() {
        assertThat(this.createTable.doublePrecisionColumn("a"),
                rendersTo("CREATE TABLE testName (a DOUBLE PRECISION)"));
    }

    @Test
    void testCreateTableWithTimestampColumn() {
        assertThat(this.createTable.timestampColumn("a"), rendersTo("CREATE TABLE testName (a TIMESTAMP)"));
    }

    @Test
    void testCreateTableWithTimestampColumnWithPrecision() {
        assertThat(this.createTable.timestampColumn("a", 9), rendersTo("CREATE TABLE testName (a TIMESTAMP(9))"));
    }

    @Test
    void testCreateTableWithInvalidTimestampColumn() {
        assertThrows(IllegalArgumentException.class, () -> this.createTable.timestampColumn("a", -1));
        assertThrows(IllegalArgumentException.class, () -> this.createTable.timestampColumn("a", 10));
    }

    @Test
    void testCreateTableWithTimestampWithLocalTimeZoneColumn() {
        assertThat(this.createTable.timestampWithLocalTimeZoneColumn("a"),
                rendersTo("CREATE TABLE testName (a TIMESTAMP WITH LOCAL TIME ZONE)"));
    }

    @Test
    void testCreateTableWithTimestampWithLocalTimeZoneColumnWithPrecision() {
        assertThat(this.createTable.timestampWithLocalTimeZoneColumn("a", 6),
                rendersTo("CREATE TABLE testName (a TIMESTAMP(6) WITH LOCAL TIME ZONE)"));
    }

    @Test
    void testCreateTableWithInvalidTimestampWithLocalTimeZoneColumn() {
        assertThrows(IllegalArgumentException.class, () -> this.createTable.timestampWithLocalTimeZoneColumn("a", -1));
        assertThrows(IllegalArgumentException.class, () -> this.createTable.timestampWithLocalTimeZoneColumn("a", 10));
    }


    @Test
    void testCreateTableWithCharColumn() {
        assertThat(this.createTable.charColumn("a", 10), rendersTo("CREATE TABLE testName (a CHAR(10))"));
    }

    @Test
    void testCreateTableWithInvalidCharSizeColumn() {
        assertThrows(IllegalArgumentException.class, () -> this.createTable.charColumn("a", 2001));
        assertThrows(IllegalArgumentException.class, () -> this.createTable.charColumn("a", -1));
    }

    @Test
    // Not a requirement, just to see what happens
    void testCreateTableWithoutColumn() {
        assertThat(this.createTable, rendersTo("CREATE TABLE testName"));
    }

    @Test
    void testCreateTableWithVarcharColumn() {
        assertThat(this.createTable.varcharColumn("a", 3000), rendersTo("CREATE TABLE testName (a VARCHAR(3000))"));
    }

    @Test
    void testCreateTableWithIntervalYearToMonthColumn() {
        assertThat(this.createTable.intervalYearToMonthColumn("a", 2),
                rendersTo("CREATE TABLE testName (a INTERVAL YEAR(2) TO MONTH)"));
    }

    @Test
    void testCreateTableWithInvalidIntervalYearToMonthColumn() {
        assertThrows(IllegalArgumentException.class, () -> this.createTable.intervalYearToMonthColumn("b", 37));
        assertThrows(IllegalArgumentException.class, () -> this.createTable.intervalYearToMonthColumn("b", 0));
    }

    @Test
    void testCreateTableWithIntervalDayToSecondColumn() {
        assertThat(this.createTable.intervalDayToSecondColumn("a", 2, 3),
                rendersTo("CREATE TABLE testName (a INTERVAL DAY(2) TO SECOND(3))"));
    }

    @Test
    void testCreateTableWithInvalidIntervalDayToSecodColumn() {
        assertThrows(IllegalArgumentException.class, () -> this.createTable.intervalDayToSecondColumn("b", 2, -1));
        assertThrows(IllegalArgumentException.class, () -> this.createTable.intervalDayToSecondColumn("b", 2, 10));
    }

    @Test
    void testCreateTableWithInvalidVarcharSizeColumn() {
        assertThrows(IllegalArgumentException.class, () -> this.createTable.varcharColumn("a", 2000001));
        assertThrows(IllegalArgumentException.class, () -> this.createTable.varcharColumn("a", -1));
    }

    @Test
    void testCreateTableWithDecimalColumn() {
        assertThat(this.createTable.decimalColumn("a", 18, 0), rendersTo("CREATE TABLE testName (a DECIMAL(18,0))"));
    }

    @Test
    void testCreateTableWithInvalidDecimalPrecisionColumn() {
        assertThrows(IllegalArgumentException.class, () -> this.createTable.decimalColumn("b", 37, 0));
        assertThrows(IllegalArgumentException.class, () -> this.createTable.decimalColumn("b", 0, 0));
    }

    @Test
    void testCreateTableWithInvalidDecimalScaleColumn() {
        assertThrows(IllegalArgumentException.class, () -> this.createTable.decimalColumn("b", 18, -1));
        assertThrows(IllegalArgumentException.class, () -> this.createTable.decimalColumn("b", 18, 19));
    }

    @Test
    void testCreateTableWithCharAFewColumns() {
        assertThat(this.createTable.decimalColumn("dec_col", 9, 0).charColumn("char_col", 10).booleanColumn("bool_col"),
                rendersTo("CREATE TABLE testName (dec_col DECIMAL(9,0), char_col CHAR(10), bool_col BOOLEAN)"));
    }
}
