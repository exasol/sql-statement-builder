package com.exasol.datatype.interval;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class TestIntervalDayToSecond {
    // [utest->dsn~exasol.converting-int-to-interval-day-to-second~1]
    @ParameterizedTest
    @CsvSource({ //
            0L + ", '0:00:00.000'", //
            999L + ", '0:00:00.999'", //
            59L * 1000 + ", '0:00:59.000'", //
            59L * 60 * 1000 + ", '0:59:00.000'", //
            23L * 60 * 60 * 1000 + ", '23:00:00.000'", //
            999999999L * 24 * 60 * 60 * 1000 + ", '999999999 0:00:00.000'", //
            1L * 24 * 60 * 60 * 1000 + 1 * 60 * 60 * 1000 + 1 * 60 * 1000 + 1 * 1000 + 1 + ", '1 1:01:01.001'" //
    })
    void testofMillis(final long value, final String expected) {
        assertThat(IntervalDayToSecond.ofMillis(value).toString(), equalTo(expected));
    }

    // [utest->dsn~exasol.parsing-interval-day-to-second-from-strings~1]
    @ParameterizedTest
    @CsvSource({ "'0:0', '0:00:00.000'", //
            "'1:2:3', '1:02:03.000'", //
            "'11:22:33.444', '11:22:33.444'", //
            "'1 22:33:44.555', '1 22:33:44.555'", //
            "'999999999 22:33:44', '999999999 22:33:44.000'" //
    })
    void testParse(final String text, final String expected) {
        assertThat(IntervalDayToSecond.parse(text).toString(), equalTo(expected));
    }

    // [utest->dsn~exasol.parsing-interval-day-to-second-from-strings~1]
    @ParameterizedTest
    @ValueSource(strings = { "0", ":0", "1.0", "123:45", "12:234", "12:34:567", "12:34:56:7890", //
            "1000000000 0:0" //
    })
    void testParseIllegalInputThrowsException(final String text) {
        assertThrows(IllegalArgumentException.class, () -> IntervalDayToSecond.parse(text));
    }
}