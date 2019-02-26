package com.exasol.sql.ddl.rendering;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ddl.CreateTable;
import com.exasol.sql.rendering.StringRendererConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersWithConfigTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestCreateTableRendering {
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
              rendersWithConfigTo(StringRendererConfig.builder().lowerCase(true).build(),
                    "create table testName"));
    }

    @Test
    void testCreateTableWithBooleanColumn() {
        assertThat(this.createTable.booleanColumn("a"),
              rendersTo("CREATE TABLE testName (a BOOLEAN)"));
    }

    @Test
    void testCreateTableWithDateColumn() {
        assertThat(this.createTable.dateColumn("a"),
              rendersTo("CREATE TABLE testName (a DATE)"));
    }

    @Test
    void testCreateTableWithDoublePrecisionColumn() {
        assertThat(this.createTable.doublePrecisionColumn("a"),
              rendersTo("CREATE TABLE testName (a DOUBLE PRECISION)"));
    }

    @Test
    void testCreateTableWithCharColumn() {
        assertThat(this.createTable.charColumn("a", 10),
              rendersTo("CREATE TABLE testName (a CHAR(10))"));
    }

    @Test
    void testCreateTableWithInvalidCharSizeColumn() {
        assertThrows(IllegalArgumentException.class, () -> this.createTable.charColumn("a", 2001));
        assertThrows(IllegalArgumentException.class, () -> this.createTable.charColumn("a", -1));
    }

    @Test
    void testCreateTableWithVarcharColumn() {
        assertThat(this.createTable.varcharColumn("a", 3000),
              rendersTo("CREATE TABLE testName (a VARCHAR(3000))"));
    }

    @Test
    void testCreateTableWithInvalidVarcharSizeColumn() {
        assertThrows(IllegalArgumentException.class,
              () -> this.createTable.varcharColumn("a", 2000001));
        assertThrows(IllegalArgumentException.class, () -> this.createTable.varcharColumn("a", -1));
    }

    @Test
    void testCreateTableWithDecimalColumn() {
        assertThat(this.createTable.decimalColumn("a", 18, 0),
              rendersTo("CREATE TABLE testName (a DECIMAL(18,0))"));
    }

    @Test
    void testCreateTableWithInvalidDecimalPrecisionColumn() {
        assertThrows(IllegalArgumentException.class,
              () -> this.createTable.decimalColumn("b", 37, 0));
        assertThrows(IllegalArgumentException.class,
              () -> this.createTable.decimalColumn("b", 0, 0));
    }

    @Test
    void testCreateTableWithInvalidDecimalScaleColumn() {
        assertThrows(IllegalArgumentException.class,
              () -> this.createTable.decimalColumn("b", 18, -1));
        assertThrows(IllegalArgumentException.class,
              () -> this.createTable.decimalColumn("b", 18, 19));
    }

    @Test
    void testCreateTableWithCharAFewColumns() {
        assertThat(this.createTable.decimalColumn("dec_col", 9, 0).charColumn("char_col", 10)
              .booleanColumn("bool_col"), rendersTo("CREATE TABLE testName (dec_col DECIMAL(9,0), char_col CHAR(10), bool_col BOOLEAN)"));
    }
}
