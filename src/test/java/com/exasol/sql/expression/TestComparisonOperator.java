package com.exasol.sql.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TestComparisonOperator {
    @Test
    void testToString() {
        assertThat(ComparisonOperator.EQUAL.toString(), equalTo("="));
    }

    // [utest->dsn~boolean-operation.comparison.constructing-from-strings~1]
    @Test
    void testOfSymbol() {
        assertThat(ComparisonOperator.ofSymbol("<>"), equalTo(ComparisonOperator.NOT_EQUAL));
    }

    // [utest->dsn~boolean-operation.comparison.constructing-from-strings~1]
    @Test
    void testOfUnknownSymbolThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ComparisonOperator.ofSymbol("§"));
    }
}