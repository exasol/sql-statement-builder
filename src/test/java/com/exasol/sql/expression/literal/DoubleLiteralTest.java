package com.exasol.sql.expression.literal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

// [utest->dsn~literal-values~2]
class DoubleLiteralTest {
    @Test
    void testGetValue() {
        assertThat(DoubleLiteral.of(10.56).getValue(), equalTo(10.56));
    }

    @Test
    void testGetToString() {
        assertThat(DoubleLiteral.of(10.56).toString(), equalTo("10.56"));
    }
}