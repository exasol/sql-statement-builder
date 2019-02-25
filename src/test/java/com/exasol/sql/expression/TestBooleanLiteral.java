package com.exasol.sql.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TestBooleanLiteral {
    // [utest->dsn~boolean-literals~1]
    @ValueSource(strings = { "true", "t", "yes", "y", "on", "enabled", "1" })
    @ParameterizedTest
    void testLiteralsInterpretedAsTrue(final String literal) {
        assertThat(BooleanLiteral.of(literal).toBoolean(), equalTo(true));
    }

    // [utest->dsn~boolean-literals~1]
    @ValueSource(strings = { "false", "f", "no", "n", "off", "disabled", "0" })
    @ParameterizedTest
    void testLiteralsInterpretedAsFalse(final String literal) {
        assertThat(BooleanLiteral.of(literal).toBoolean(), equalTo(false));
    }

    // [utest->dsn~boolean-literals~1]
    @Test
    void testLiteralOfNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BooleanLiteral.of(null));
    }

    // [utest->dsn~boolean-literals~1]
    @Test
    void testLiteralOfUnknownStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BooleanLiteral.of("unknown"));
    }

    // [utest->dsn~boolean-literals~1]
    @Test
    void testToStringTrue() {
        assertThat(BooleanLiteral.of("true").toString(), equalTo("TRUE"));
    }

    // [utest->dsn~boolean-literals~1]
    @Test
    void testToStringFalse() {
        assertThat(BooleanLiteral.of("false").toString(), equalTo("FALSE"));
    }
}