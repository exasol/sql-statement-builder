package com.exasol.sql.expression.literal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

// [utest->dsn~literal-values~2]
class TestStringLiteral {
    @Test
    void testGetToString() {
        assertThat(StringLiteral.of("foo").toString(), equalTo("foo"));
    }
}