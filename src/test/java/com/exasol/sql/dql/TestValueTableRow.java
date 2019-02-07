package com.exasol.sql.dql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.jupiter.api.Test;

import com.exasol.sql.expression.StringLiteral;

class TestValueTableRow {
    // [utest->dsn~value-table~1]
    @Test
    void testGetExpressions() {
        final ValueTableRow row = new ValueTableRow(null, "a", "b");
        assertThat(row.getExpressions(), contains(StringLiteral.of("a"), StringLiteral.of("b")));
    }
}