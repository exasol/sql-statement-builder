package com.exasol.sql.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

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