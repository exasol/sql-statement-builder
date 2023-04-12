package com.exasol.datatype.value;

import java.util.regex.Matcher;

/**
 * Abstract base class for interval types.
 */
public abstract class AbstractInterval {
    /** Interval value */
    protected final long value;
    protected final boolean positive;

    /**
     * Create an interval from a long integer value.
     *
     * @param value long integer value
     */
    protected AbstractInterval(final long value) {
        if (value >= 0) {
            this.value = value;
            this.positive = true;
        } else {
            this.value = -value;
            this.positive = false;
        }
    }

    /**
     * Create an interval from an absolute value and a sign.

     * @param absoluteValue absolute interval value
     * @param positive sign indicator
     */
    protected AbstractInterval(final long absoluteValue, final boolean positive) {
        this.value = absoluteValue;
        this.positive = positive;
    }

    /**
     * Parse a group that came out of a string match to an interval.
     *
     * @param matcher matcher applied on the interval string
     * @param groupNumber group that matched
     * @return long value representing the interval
     */
    protected static long parseMatchingGroupToLong(final Matcher matcher, final int groupNumber) {
        return (matcher.group(groupNumber) == null) ? 0 :
              Long.parseLong(matcher.group(groupNumber));
    }

    /**
     * Produce the sign of the interval.
     *
     * @return "+" if positive, "-" if negative
     */
    protected String getSign() {
        return this.positive ? "+" : "-";
    }

    /**
     * Get the signed long integer value.
     *
     * @return signed value
     */
    protected long getSignedValue() {
        return this.value * (this.positive ? 1 : -1);
    }
}
