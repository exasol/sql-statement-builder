package com.exasol.sql.dql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterableOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testCreateValueTableFromRowsOfLiterals() {
        this.valueTable.appendRow("r1c1", "r1c2");
        this.valueTable.appendRow("r2c1", "r2c2");
        final List<ValueTableRow> rows = this.valueTable.getRows();
        assertAll(() -> assertThat(rows.size(), equalTo(2)), //
                () -> assertThat(rows.get(0), equalTo(new ValueTableRow(null, "r1c1", "r1c2"))));
    }
}