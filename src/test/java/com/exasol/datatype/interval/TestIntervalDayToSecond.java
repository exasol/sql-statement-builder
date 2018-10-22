package com.exasol.datatype.interval;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class TestIntervalDayToSecond {
    // [utest->dsn~exasol.converting-int-to-interval-day-to-second~2]
    @ParameterizedTest
    @CsvSource({ //
            0L + ", '+0 0:00:00.000'", //
            999L + ", '+0 0:00:00.999'", //
            59L * 1000 + ", '+0 0:00:59.000'", //
            59L * 60 * 1000 + ", '+0 0:59:00.000'", //
            23L * 60 * 60 * 1000 + ", '+0 23:00:00.000'", //
            999999999L * 24 * 60 * 60 * 1000 + ", '+999999999 0:00:00.000'", //
            1L * 24 * 60 * 60 * 1000 + 1 * 60 * 60 * 1000 + 1 * 60 * 1000 + 1 * 1000 + 1 + ", '+1 1:01:01.001'", //
            -(1L * 24 * 60 * 60 * 1000 + 1 * 60 * 60 * 1000 + 1 * 60 * 1000 + 1 * 1000 + 1) + ", '-1 1:01:01.001'", //
            -(1000L + 1) + ", '-0 0:00:01.001'" //
    })
    void testOfMillis(final long value, final String expected) {
        final AbstractInterval interval = IntervalDayToSecond.ofMillis(value);
        assertThat(interval.toString(), equalTo(expected));
    }

    // [utest->dsn~exasol.parsing-interval-day-to-second-from-strings~2]
    @ParameterizedTest
    @CsvSource({ "'0:0', '+0 0:00:00.000'", //
            "'1:2:3', '+0 1:02:03.000'", //
            "'11:22:33.444', '+0 11:22:33.444'", //
            "'1 22:33:44.555', '+1 22:33:44.555'", //
            "'+1 22:33:44.555', '+1 22:33:44.555'", //
            "'-1 22:33:44.555', '-1 22:33:44.555'", //
            "'999999999 22:33:44', '+999999999 22:33:44.000'" //
    })
    void testParse(final String text, final String expected) {
        final AbstractInterval interval = IntervalDayToSecond.parse(text);
        assertThat(interval.toString(), equalTo(expected));
    }

    // [utest->dsn~exasol.parsing-interval-day-to-second-from-strings~2]
    @ParameterizedTest
    @ValueSource(strings = { "0", ":0", "1.0", "123:45", "12:234", "12:34:567", "12:34:56:7890", //
            "1000000000 0:0", "- 1-1", "+ 1-1", "*1.1" //
    })
    void testParseIllegalInputThrowsException(final String text) {
        assertThrows(IllegalArgumentException.class, () -> IntervalDayToSecond.parse(text));
    }

    // [utest->dsn~exasol.converting-interval-day-to-second-to-int~1]
    @ParameterizedTest
    @ValueSource(longs = { 1234, 0, -2345 })
    void testToMillis(final long millis) {
        assertThat(IntervalDayToSecond.ofMillis(millis).toMillis(), equalTo(millis));
    }
}