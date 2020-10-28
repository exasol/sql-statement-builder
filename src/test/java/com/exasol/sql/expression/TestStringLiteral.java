package com.exasol.sql.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

import com.exasol.sql.expression.literal.StringLiteral;

// [utest->dsn~literal-values~2]
class TestStringLiteral {
    @Test
    void testGetToString() {
        assertThat(StringLiteral.of("foo").toString(), equalTo("foo"));
    }
}