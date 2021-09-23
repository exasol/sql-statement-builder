package com.exasol.datatype.value;

import java.util.regex.Matcher;

public abstract class AbstractInterval {
    protected final long value;
    protected final boolean positive;

    protected AbstractInterval(final long value) {
        if (value >= 0) {
            this.value = value;
            this.positive = true;
        } else {
            this.value = -value;
            this.positive = false;
        }
    }

    protected AbstractInterval(final long absoluteValue, final boolean positive) {
        this.value = absoluteValue;
        this.positive = positive;
    }

    protected static long parseMatchingGroupToLong(final Matcher matcher, final int groupNumber) {
        return (matcher.group(groupNumber) == null) ? 0 :
              Long.parseLong(matcher.group(groupNumber));
    }

    protected String getSign() {
        return this.positive ? "+" : "-";
    }

    protected long getSignedValue() {
        return this.value * (this.positive ? 1 : -1);
    }
}