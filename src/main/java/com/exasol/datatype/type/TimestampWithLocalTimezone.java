package com.exasol.datatype.type;

import com.exasol.sql.ColumnDefinitionVisitor;

/**
 * This class implements the Exasol-proprietary data type timestamp with local timezone data
 */
public class TimestampWithLocalTimezone extends AbstractTimestampDataType {
    private static final String NAME = "TIMESTAMP WITH LOCAL TIME ZONE";

    /**
     * Create a new instance of a {@link TimestampWithLocalTimezone} data type
     *
     * @param precision fractional seconds precision
     */
    public TimestampWithLocalTimezone(final int precision) {
        super(precision);
    }

    /**
     * Create a new instance of a {@link TimestampWithLocalTimezone} data type with the default fractional
     * seconds precision (3, i.e. millisecond precision)
     *
     */
    public TimestampWithLocalTimezone() {
        this(DEFAULT_FRACTIONAL_SECOND_PRECISION);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void accept(final ColumnDefinitionVisitor visitor) {
        visitor.visit(this);
    }

}