package com.exasol.sql.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.exasol.sql.expression.comparison.SimpleComparisonOperator;

class TestComparisonOperator {
    @Test
    void testToString() {
        assertThat(SimpleComparisonOperator.EQUAL.toString(), equalTo("="));
    }

    // [utest->dsn~boolean-operation.comparison.constructing-from-strings~1]
    @Test
    void testOfSymbol() {
        assertThat(SimpleComparisonOperator.ofSymbol("<>"), equalTo(SimpleComparisonOperator.NOT_EQUAL));
    }

    // [utest->dsn~boolean-operation.comparison.constructing-from-strings~1]
    @Test
    void testOfUnknownSymbolThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> SimpleComparisonOperator.ofSymbol("§"));
    }
}