package com.exasol.sql.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

// [utest->dsn~literal-values~1]
class TestBigDecimalLiteral {
    @Test
    void testGetValue() {
        assertThat(BigDecimalLiteral.of(BigDecimal.TEN).getValue(), equalTo(BigDecimal.TEN));
    }

    @Test
    void testGetToString() {
        assertThat(BigDecimalLiteral.of(BigDecimal.valueOf(123)).toString(), equalTo("123"));
    }
}