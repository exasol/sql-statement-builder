package com.exasol.sql.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

import com.exasol.sql.expression.literal.LongLiteral;

// [utest->dsn~literal-values~2]
class LongLiteralTest {
    @Test
    void testGetValue() {
        assertThat(LongLiteral.of(9000000000L).getValue(), equalTo(9000000000L));
    }

    @Test
    void testGetToString() {
        assertThat(LongLiteral.of(9000000000L).toString(), equalTo("9000000000"));
    }
}