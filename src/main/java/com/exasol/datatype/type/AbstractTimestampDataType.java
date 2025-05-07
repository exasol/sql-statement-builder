package com.exasol.datatype.type;

import static java.lang.String.format;

/**
 * Implements common logic for Timestamp types.
 */
public abstract class AbstractTimestampDataType implements DataType {
    /**
     * Default fractional second precision when not specified: milliseconds
     */
    public static final int DEFAULT_FRACTIONAL_SECOND_PRECISION = 3;

    private final int precision;

    AbstractTimestampDataType(final int precision) {
        validatePrecision(precision);
        this.precision = precision;
    }

    /**
     * @return precision value
     */
    public int getPrecision() {
        return this.precision;
    }

    private void validatePrecision(final int precision) {
        if (precision < 0 || precision > 9) {
            throw new IllegalArgumentException(format("Invalid precision: %d. Must be a number between 0 and 9.", precision));
        }
    }
}
