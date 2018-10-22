package com.exasol.datatype.interval;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class TestIntervalYearToMonth {
    // [utest->dsn~exasol.converting-int-to-interval-year-to-month~2]
    @ParameterizedTest
    @CsvSource({ //
            0L + ", '+0-00'", //
            11L + ", '+0-11'", //
            999999999L * 12 + ", '+999999999-00'", //
            999999999L * 12 + 11 + ", '+999999999-11'", //
            1L * 12 + 1 + ", '+1-01'", //
            -(1L * 12 + 1) + ", '-1-01'" //
    })
    void testOfMonths(final long value, final String expected) {
        final AbstractInterval interval = IntervalYearToMonth.ofMonths(value);
        assertThat(interval.toString(), equalTo(expected));
    }

    // [utest->dsn~exasol.parsing-interval-year-to-month-from-strings~2]
    @ParameterizedTest
    @CsvSource({ "'0-0', '+0-00'", //
            "'1-2', '+1-02'", //
            "'22-11', '+22-11'", //
            "'+22-11', '+22-11'", //
            "'-22-11', '-22-11'", //
            "'999999999-11', '+999999999-11'" //
    })
    void testParse(final String text, final String expected) {
        final AbstractInterval interval = IntervalYearToMonth.parse(text);
        assertThat(interval.toString(), equalTo(expected));
    }

    // [utest->dsn~exasol.parsing-interval-year-to-month-from-strings~2]
    @ParameterizedTest
    @ValueSource(strings = { "0", "-0", "0-", "0-123", "- 1-1", "+ 1-1", "*1-1", "1000000000-0" })
    void testParseIllegalInputThrowsException(final String text) {
        assertThrows(IllegalArgumentException.class, () -> IntervalYearToMonth.parse(text));
    }

    // [utest->converting-interval-year-to-month-to-int~1]
    @ParameterizedTest
    @ValueSource(longs = { 1234, 0, -2345 })
    void testGetMonths(final long months) {
        assertThat(IntervalYearToMonth.ofMonths(months).toMonths(), equalTo(months));
    }
}