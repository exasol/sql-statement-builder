package com.exasol.sql.dql.select;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterableOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.ValueTable;
import com.exasol.sql.ValueTableRow;
import com.exasol.sql.expression.ValueExpression;

class TestValueTable {
    private ValueTable valueTable;

    @BeforeEach
    void beforeEach() {
        this.valueTable = new ValueTable(null);
    }

    // [utest->dsn~value-table~1]
    @Test
    void testCreateEmtpyValueTable() {
        assertThat(this.valueTable.getRows(), emptyIterableOf(ValueTableRow.class));
    }

    // [utest->dsn~value-table~1]
    @Test
    void testCreateValueTableFromRowsOfStringLiterals() {
        this.valueTable.appendRow("r1c1", "r1c2");
        this.valueTable.appendRow("r2c1", "r2c2");
        assertAll(() -> assertRowCount(2), //
                () -> assertRowContent(0, 0, "r1c1"), //
                () -> assertRowContent(0, 1, "r1c2"));
    }

    protected void assertRowCount(final int rows) {
        assertThat("Row count", this.valueTable.getRows().size(), equalTo(rows));
    }

    protected void assertRowContent(final int row, final int column, final String expected) {
        assertThat(namePosition(row, column), getExpressionAtPosition(row, column).toString(), equalTo(expected));
    }

    protected void assertRowContent(final int row, final int column, final char expected) {
        assertThat(namePosition(row, column), (getExpressionAtPosition(row, column).toString().charAt(0)),
                equalTo(expected));
    }

    protected ValueExpression getExpressionAtPosition(final int row, final int column) {
        return this.valueTable.getRows().get(row).getExpressions().get(column);
    }

    protected String namePosition(final int row, final int column) {
        return "Row " + row + ", column " + column + " content";
    }

    protected void assertRowContent(final int row, final int column, final int expected) {
        assertThat(namePosition(row, column), Integer.parseInt(getExpressionAtPosition(row, column).toString()),
                equalTo(expected));
    }

    protected void assertRowContent(final int row, final int column, final long expected) {
        assertThat(namePosition(row, column), Long.parseLong(getExpressionAtPosition(row, column).toString()),
                equalTo(expected));
    }

    protected void assertRowContent(final int row, final int column, final double expected) {
        assertThat(namePosition(row, column), Double.parseDouble(getExpressionAtPosition(row, column).toString()),
                equalTo(expected));
    }

    protected void assertRowContent(final int row, final int column, final float expected) {
        assertThat(namePosition(row, column), Float.parseFloat(getExpressionAtPosition(row, column).toString()),
                equalTo(expected));
    }

    protected void assertRowContent(final int row, final int column, final boolean expected) {
        assertThat(namePosition(row, column), Boolean.parseBoolean(getExpressionAtPosition(row, column).toString()),
                equalTo(expected));
    }

    // [utest->dsn~value-table~1]
    @Test
    void testAddStringsToLastRow() {
        this.valueTable.add("a", "b");
        assertAll(() -> assertRowCount(1), //
                () -> assertRowContent(0, 0, "a"), //
                () -> assertRowContent(0, 1, "b"));
    }

    // [utest->dsn~value-table~1]
    @Test
    void testAddCharsToLastRow() {
        this.valueTable.add('a', 'b');
        assertAll(() -> assertRowCount(1), //
                () -> assertRowContent(0, 0, 'a'), //
                () -> assertRowContent(0, 1, 'b'));
    }

    // [utest->dsn~value-table~1]
    @Test
    void testAddIntegersToLastRow() {
        this.valueTable.add(42, -42);
        assertAll(() -> assertRowCount(1), //
                () -> assertRowContent(0, 0, 42), //
                () -> assertRowContent(0, 1, -42));
    }

    // [utest->dsn~value-table~1]
    @Test
    void testAddLongsToLastRow() {
        this.valueTable.add(42L, -42L);
        assertAll(() -> assertRowCount(1), //
                () -> assertRowContent(0, 0, 42L), //
                () -> assertRowContent(0, 1, -42L));
    }

    // [utest->dsn~value-table~1]
    @Test
    void testAddDoublesToLastRow() {
        this.valueTable.add(42.45, -42.45);
        assertAll(() -> assertRowCount(1), //
                () -> assertRowContent(0, 0, 42.45), //
                () -> assertRowContent(0, 1, -42.45));
    }

    // [utest->dsn~value-table~1]
    @Test
    void testAddFloatsToLastRow() {
        this.valueTable.add(42.45f, -42.45f);
        assertAll(() -> assertRowCount(1), //
                () -> assertRowContent(0, 0, 42.45f), //
                () -> assertRowContent(0, 1, -42.45f));
    }

    // [utest->dsn~value-table~1]
    @Test
    void testAddBooleansToLastRow() {
        this.valueTable.add(true, false);
        assertAll(() -> assertRowCount(1), //
                () -> assertRowContent(0, 0, true), //
                () -> assertRowContent(0, 1, false));
    }

    // [utest->dsn~value-table~1]
    @Test
    void testAddPlaceholdersToLastRow() {
        this.valueTable.addPlaceholder();
        assertAll(() -> assertRowCount(1), //
                () -> assertRowContent(0, 0, "?"));
    }
}