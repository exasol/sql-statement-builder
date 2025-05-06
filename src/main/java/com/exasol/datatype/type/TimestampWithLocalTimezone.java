package com.exasol.datatype.type;

import com.exasol.sql.ColumnDefinitionVisitor;

/**
 * This class implements the Exasol-proprietary data type timestamp with local timezone data
 */
public class TimestampWithLocalTimezone implements DataType {
    public static final int DEFAULT_PRECISION = 3;
    private static final String NAME = "TIMESTAMP WITH LOCAL TIME ZONE";

    private final int precision;

    /**
     * Create a new instance of a {@link TimestampWithLocalTimezone} data type
     *
     * @param precision fractional seconds precision
     */
    public TimestampWithLocalTimezone(final int precision) {
        validatePrecision(precision);
        this.precision = precision;
    }

    /**
     * Create a new instance of a {@link TimestampWithLocalTimezone} data type with the default fractional
     * seconds precision (3, i.e. millisecond precision)
     *
     */
    public TimestampWithLocalTimezone() {
        this(DEFAULT_PRECISION);
    }

    /**
     * @return precision value
     */
    public int getPrecision() {
        return this.precision;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void accept(final ColumnDefinitionVisitor visitor) {
        visitor.visit(this);
    }

    private void validatePrecision(final int precision) {
        if (precision < 0 || precision > 9) {
            throw new IllegalArgumentException("Precision must be a number between 0 and 9.");
        }
    }
}