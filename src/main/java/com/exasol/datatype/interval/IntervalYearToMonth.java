package com.exasol.datatype.interval;

import static com.exasol.datatype.interval.IntervalConstants.MONTHS_PER_YEAR;

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
public class IntervalYearToMonth extends AbstractInterval {
    private static final int SIGN_MATCHING_GROUP = 1;
    private static final int YEARS_MATCHING_GROUP = 2;
    private static final int MONTHS_MATCHING_GROUP = 3;
    private static final Pattern INTERVAL_PATTERN = Pattern.compile("([-+])?(\\d{1,9})-(\\d{1,2})");

    private IntervalYearToMonth(final long value) {
        super(value);
    }

    private IntervalYearToMonth(final long absoluteValue, final boolean positive) {
        super(absoluteValue, positive);
    }

    @Override
    public String toString() {
        return String.format("%s%d-%02d", getSign(), getYears(), getMonths());
    }

    private long getYears() {
        return this.value / MONTHS_PER_YEAR;
    }

    private long getMonths() {
        return this.value % MONTHS_PER_YEAR;
    }

    /**
     * Get the interval as the total number of months between two points in time
     *
     * @return total number of months
     */
    // [impl->dsn~exasol.converting-interval-year-to-month-to-int~1]
    public long toMonths() {
        return getSignedValue();
    }

    /**
     * Create an {@link IntervalDayToSecond} from a number of months
     *
     * @param value total length of the interval in months
     * @return interval with months resolution
     */
    // [impl->dsn~exasol.converting-int-to-interval-year-to-month~2]
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
    // [impl->dsn~exasol.parsing-interval-year-to-month-from-strings~2]
    public static IntervalYearToMonth parse(final String text) {
        final Matcher matcher = INTERVAL_PATTERN.matcher(text);
        if (matcher.matches()) {
            final long parsedValue = MONTHS_PER_YEAR * parseMatchingGroupToLong(matcher, YEARS_MATCHING_GROUP) //
                    + parseMatchingGroupToLong(matcher, MONTHS_MATCHING_GROUP);
            final boolean parsedPositive = !"-".equals(matcher.group(SIGN_MATCHING_GROUP));
            return new IntervalYearToMonth(parsedValue, parsedPositive);
        } else {
            throw new IllegalArgumentException(
                    "Text \"" + text + "\" cannot be parsed to an INTERVAL. Must match \"" + INTERVAL_PATTERN + "\"");
        }
    }
}