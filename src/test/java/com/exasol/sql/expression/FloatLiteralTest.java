package com.exasol.sql.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

// [utest->dsn~literal-values~2]
class FloatLiteralTest {
    @Test
    void testGetValue() {
        assertThat(FloatLiteral.of(10.56f).getValue(), equalTo(10.56f));
    }

    @Test
    void testGetToString() {
        assertThat(FloatLiteral.of(10.56f).toString(), equalTo("10.56"));
    }
}