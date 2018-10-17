package com.exasol.datatype.interval;

import java.util.regex.Matcher;

public abstract class AbstractInterval {
    protected final long value;
    protected boolean positive;

    public AbstractInterval(final long value) {
        if (value >= 0) {
            this.value = value;
            this.positive = true;
        } else {
            this.value = -value;
            this.positive = false;
        }
    }

    public AbstractInterval(final long absoluteValue, final boolean positive) {
        this.value = absoluteValue;
        this.positive = positive;
    }

    protected static long parseMatchingGroupToLong(final Matcher matcher, final int groupNumber) {
        return (matcher.group(groupNumber) == null) ? 0 : Long.parseLong(matcher.group(groupNumber));
    }

    protected String getSign() {
        return this.positive ? "+" : "-";
    }
}