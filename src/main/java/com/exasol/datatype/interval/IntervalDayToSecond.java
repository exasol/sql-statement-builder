package com.exasol.datatype.interval;

import com.exasol.sql.ddl.CreateTableVisitor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.exasol.datatype.interval.IntervalConstants.*;

/**
 * This class implements the Exasol-proprietary data type <code>INTERVAL DAY(x) TO SECONDS(y)</code>. It supports
 * conversions to and from strings and from milliseconds.
 *
 * <p>
 * In Exasol this data type represents a time difference consisting of the following components:
 * </p>
 * <ul>
 * <li>days</li>
 * <li>hours</li>
 * <li>minutes</li>
 * <li>seconds</li>
 * <li>milliseconds (or fraction of seconds)</li>
 * </ul>
 *
 * Since milliseconds are the highest resolution, each interval can also be expressed as a total number of milliseconds.
 * This is also the recommended way to represent the interval values in other systems which do not natively support this
 * data type.
 */
public class IntervalDayToSecond extends AbstractInterval {
    private static final int SIGN_MATCHING_GROUP = 1;
    private static final int DAYS_MATCHING_GROUP = 2;
    private static final int HOURS_MATCHING_GROUP = 3;
    private static final int MINUTES_MATCHING_GROUP = 4;
    private static final int SECONDS_MATCHING_GROUP = 5;
    private static final int MILLIS_MATCHING_GROUP = 6;
    private static final Pattern INTERVAL_PATTERN = Pattern.compile("([-+])?(?:(\\d{1,9})\\s+)?" // days
            + "(\\d{1,2})" // hours
            + ":(\\d{1,2})" // minutes
            + "(?::(\\d{1,2})" // seconds
            + "(?:\\.(\\d{1,3}))?)?" // milliseconds
    );

    private IntervalDayToSecond(final long value) {
        super(value);
    }

    private IntervalDayToSecond(final long absoluteValue, final boolean positive) {
        super(absoluteValue, positive);
    }

    @Override
    public String toString() {
        return String.format("%s%d %d:%02d:%02d.%03d", getSign(), getDays(), getHours(), getMinutes(), getSeconds(),
                getMillis());
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
     * Get the interval as the total number of milliseconds between two points in time.
     *
     * @return total number of milliseconds
     */
    // [impl->dsn~exasol.converting-interval-day-to-second-to-int~1]
    public long toMillis() {
        return getSignedValue();
    }

    /**
     * Create an {@link IntervalDayToSecond} from a number of milliseconds
     *
     * @param value total length of the interval in milliseconds
     * @return interval with milliseconds resolution
     */
    // [impl->dsn~exasol.converting-int-to-interval-day-to-second~2]
    public static IntervalDayToSecond ofMillis(final long value) {
        return new IntervalDayToSecond(value);
    }

    /**
     * Parse an {@link IntervalDayToSecond} from a string
     *
     * <p>
     * The accepted format is:
     * </p>
     * <p>
     * <code>[dddddddd ]<strong>hh:mm</strong>[:ss[.SSS]]</code>
     * <p>
     * Where
     * </p>
     * <dl>
     * <dt>d</dt>
     * <dd>day, 1-9 digits, optional</dd>
     * <dt>h</dt>
     * <dd>hours, 1-2 digits, mandatory</dd>
     * <dt>m</dt>
     * <dd>minutes, 1-2 digits, mandatory</dd>
     * <dt>s</dt>
     * <dd>seconds, 1-2 digits, optional</dd>
     * <dt>S</dt>
     * <dd>milliseconds, 1-3 digits, optional</dd>
     * </dl>
     *
     * @param text string representing an interval
     * @return interval with milliseconds resolution
     */
    // [impl->dsn~exasol.parsing-interval-day-to-second-from-strings~2]
    public static IntervalDayToSecond parse(final String text) {
        final Matcher matcher = INTERVAL_PATTERN.matcher(text);
        if (matcher.matches()) {
            return createIntervalFromParsingResults(matcher);
        } else {
            throw new IllegalArgumentException(
                    "Text \"" + text + "\" cannot be parsed to an INTERVAL. Must match \"" + INTERVAL_PATTERN + "\"");
        }
    }

    private static IntervalDayToSecond createIntervalFromParsingResults(final Matcher matcher) {
        final long parsedValue = MILLIS_PER_DAY * parseMatchingGroupToLong(matcher, DAYS_MATCHING_GROUP) //
                + MILLIS_PER_HOUR * parseMatchingGroupToLong(matcher, HOURS_MATCHING_GROUP) //
                + MILLIS_PER_MINUTE * parseMatchingGroupToLong(matcher, MINUTES_MATCHING_GROUP) //
                + MILLIS_PER_SECOND * parseMatchingGroupToLong(matcher, SECONDS_MATCHING_GROUP) //
                + parseMatchingGroupToLong(matcher, MILLIS_MATCHING_GROUP);
        final boolean parsedPositive = !"-".equals(matcher.group(SIGN_MATCHING_GROUP));
        return new IntervalDayToSecond(parsedValue, parsedPositive);
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        //todo
    }

    @Override
    public String getName() {
        return null;
        //todo
    }
}