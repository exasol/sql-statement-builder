package com.exasol.datatype.value;

final class IntervalConstants {
    private IntervalConstants() {
        // prevent instanciation
    }

    static final long MILLIS_PER_SECOND = 1000L;
    static final long SECONDS_PER_MINUTE = 60L;
    static final long MINUTES_PER_HOUR = 60L;
    static final long HOURS_PER_DAY = 24L;
    static final long MONTHS_PER_YEAR = 12L;
    static final long MILLIS_PER_MINUTE = SECONDS_PER_MINUTE * MILLIS_PER_SECOND;
    static final long MILLIS_PER_HOUR = MINUTES_PER_HOUR * MILLIS_PER_MINUTE;
    static final long MILLIS_PER_DAY = HOURS_PER_DAY * MILLIS_PER_HOUR;
}