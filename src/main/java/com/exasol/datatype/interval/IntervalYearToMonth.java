package com.exasol.datatype.interval;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements the Exasol-proprietary data type <code>INTERVAL YEAR(x) TO MONTH(y)</code>. It supports
 * conversions to and from strings and from a number of months.
 *
 * <p>
 * In Exasol this data type represents a time difference consisting of the following components:
 * </p>
 * <ul>
 * <li>years</li>
 * <li>months</li>
 * </ul>
 *
 * Since months are the highest resolution, each interval can also be expressed as a total number of months. This is
 * also the recommended way to represent the interval values in other systems which do not natively support this data
 * type.
 */
public class IntervalYearToMonth {
    private static final long MONTHS_PER_YEAR = 12L;
    private static final int YEARS_MATCHING_GROUP = 1;
    private static final int MONTHS_MATCHING_GROUP = 2;
    private static final Pattern INTERVAL_PATTERN = Pattern.compile("(\\d{1,9})-(\\d{1,2})");
    private final long value;

    private IntervalYearToMonth(final long value) {
        this.value = value;
    }

    private IntervalYearToMonth(final String text) {
        final Matcher matcher = INTERVAL_PATTERN.matcher(text);
        if (matcher.matches()) {
            this.value = MONTHS_PER_YEAR * parseMatchingGroupToLong(matcher, YEARS_MATCHING_GROUP) //
                    + parseMatchingGroupToLong(matcher, MONTHS_MATCHING_GROUP);
        } else {
            throw new IllegalArgumentException(
                    "Text \"" + text + "\" cannot be parsed to an INTERVAL. Must match \"" + INTERVAL_PATTERN + "\"");
        }
    }

    private long parseMatchingGroupToLong(final Matcher matcher, final int groupNumber) {
        return Long.parseLong(matcher.group(groupNumber));
    }

    @Override
    public String toString() {
        return String.format("%d-%02d", getYears(), getMonths());
    }

    private long getYears() {
        return this.value / MONTHS_PER_YEAR;
    }

    private long getMonths() {
        return this.value % MONTHS_PER_YEAR;
    }

    /**
     * Create an {@link IntervalDayToSecond} from a number of months
     *
     * @param value total length of the interval in months
     * @return interval with months resolution
     */
    public static IntervalYearToMonth ofMonths(final long value) {
        return new IntervalYearToMonth(value);
    }

    /**
     * Parse an {@link IntervalDayToSecond} from a string
     *
     * <p>
     * The accepted format is:
     * </p>
     * <p>
     * <code>YYYYYYYYY:MM</code>
     * <p>
     * Where
     * </p>
     * <dl>
     * <dt>Y</dt>
     * <dd>years, 1-9 digits, mandatory</dd>
     * <dt>M</dt>
     * <dd>months, 1-2 digits, mandatory</dd>
     * </dl>
     *
     * @param text string representing an interval
     * @return interval with months resolution
     */
    public static IntervalYearToMonth parse(final String text) {
        return new IntervalYearToMonth(text);
    }
}