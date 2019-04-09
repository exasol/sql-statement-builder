package com.exasol.sql.dql.select;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.exasol.sql.ValueTableRow;
import com.exasol.sql.expression.ValueExpression;

class TestValueTableRow {
    // [utest->dsn~value-table~1]
    @Test
    void testGetExpressions() {
        final ValueTableRow row = new ValueTableRow(null, "a", "b");
        final List<ValueExpression> expressions = row.getExpressions();
        assertAll(() -> assertThat(expressions.size(), equalTo(2)),
                () -> assertThat(expressions.get(0).toString(), equalTo("a")),
                () -> assertThat(expressions.get(1).toString(), equalTo("b")));
    }
}