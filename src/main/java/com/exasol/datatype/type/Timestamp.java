package com.exasol.datatype.type;

import com.exasol.sql.ColumnDefinitionVisitor;

/**
 * This class implements the SQl data type timestamp
 */
public class Timestamp extends AbstractTimestampDataType {
    private static final String NAME = "TIMESTAMP";

    /**
     * Create a new instance of a {@link Timestamp} data type
     *
     * @param precision fractional seconds precision
     */
    public Timestamp(final int precision) {
        super(precision);
    }

    /**
     * Create a new instance of a {@link Timestamp} data type with the default fractional
     * seconds precision (3, i.e. millisecond precision)
     *
     */
    public Timestamp() {
        super(DEFAULT_FRACTIONAL_SECOND_PRECISION);
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
