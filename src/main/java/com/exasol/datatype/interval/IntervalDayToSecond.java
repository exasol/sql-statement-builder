package com.exasol.datatype.interval;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements the Exasol-proprietary data type <code>INTERVAL DAY(x) TO SECONDS(y)</code>. It supports
 * conversions to and from strings and from milliseconds.
 */
public class IntervalDayToSecond {
    private static final long MILLIS_PER_SECOND = 1000L;
    private static final long SECONDS_PER_MINUTE = 60L;
    private static final long MINUTES_PER_HOUR = 60L;
    private static final long HOURS_PER_DAY = 24L;
    private static final long MILLIS_PER_MINUTE = SECONDS_PER_MINUTE * MILLIS_PER_SECOND;
    private static final long MILLIS_PER_HOUR = MINUTES_PER_HOUR * MILLIS_PER_MINUTE;
    private static final long MILLIS_PER_DAY = HOURS_PER_DAY * MILLIS_PER_HOUR;
    private static final int DAYS_MATCHING_GROUP = 1;
    private static final int HOURS_MATCHING_GROUP = 2;
    private static final int MINUTES_MATCHING_GROUP = 3;
    private static final int SECONDS_MATCHING_GROUP = 4;
    private static final int MILLIS_MATCHING_GROUP = 5;
    private static final Pattern INTERVAL_PATTERN = Pattern.compile("(?:(\\d{1,9})\\s+)?" // days
            + "(\\d{1,2})" // hours
            + ":(\\d{1,2})" // minutes
            + "(?::(\\d{1,2})" // seconds
            + "(?:\\.(\\d{1,3}))?)?" // milliseconds
    );
    private final long value;

    private IntervalDayToSecond(final long value) {
        this.value = value;
    }

    private IntervalDayToSecond(final String text) {
        final Matcher matcher = INTERVAL_PATTERN.matcher(text);
        if (matcher.matches()) {
            this.value = MILLIS_PER_DAY * parseMatchingGroupToLong(matcher, DAYS_MATCHING_GROUP) //
                    + MILLIS_PER_HOUR * parseMatchingGroupToLong(matcher, HOURS_MATCHING_GROUP) //
                    + MILLIS_PER_MINUTE * parseMatchingGroupToLong(matcher, MINUTES_MATCHING_GROUP) //
                    + MILLIS_PER_SECOND * parseMatchingGroupToLong(matcher, SECONDS_MATCHING_GROUP) //
                    + parseMatchingGroupToLong(matcher, MILLIS_MATCHING_GROUP);
        } else {
            throw new IllegalArgumentException(
                    "Text \"" + text + "\" cannot be parsed to an INTERVAL. Must match \"" + INTERVAL_PATTERN + "\"");
        }
    }

    private long parseMatchingGroupToLong(final Matcher matcher, final int groupNumber) {
        return (matcher.group(groupNumber) == null) ? 0 : Long.parseLong(matcher.group(groupNumber));
    }

    @Override
    public String toString() {
        return hasDays() //
                ? String.format("%d %d:%02d:%02d.%03d", getDays(), getHours(), getMinutes(), getSeconds(), getMillis()) //
                : String.format("%d:%02d:%02d.%03d", getHours(), getMinutes(), getSeconds(), getMillis());
    }

    private boolean hasDays() {
        return this.value >= MILLIS_PER_DAY;
    }

    private long getDays() {
        return this.value / MILLIS_PER_DAY;
    }

    private long getHours() {
        return this.value / MILLIS_PER_HOUR % HOURS_PER_DAY;
    }

    private long getMinutes() {
        return this.value / MILLIS_PER_MINUTE % MINUTES_PER_HOUR;
    }

    private long getSeconds() {
        return this.value / MILLIS_PER_SECOND % SECONDS_PER_MINUTE;
    }

    private long getMillis() {
        return this.value % MILLIS_PER_SECOND;
    }

    /**
     * Create an {@link IntervalDayToSecond} from a number of milliseconds
     *
     * @param value milliseconds
     * @return interval
     */
    public static IntervalDayToSecond ofMillis(final long value) {
        return new IntervalDayToSecond(value);
    }

    /**
     * Parse an {@link IntervalDayToSecond} from a string
     *
     * @param text
     * @return
     */
    public static IntervalDayToSecond parse(final String text) {
        return new IntervalDayToSecond(text);
    }
}