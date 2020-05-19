package com.exasol.datatype.type;

import com.exasol.sql.ColumnDefinitionVisitor;

/**
 * This class implements the Exasol-proprietary data type timestamp with local timezone data
 */
public class TimestampWithLocalTimezone implements DataType {
    private static final String NAME = "TIMESTAMP WITH LOCAL TIME ZONE";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void accept(final ColumnDefinitionVisitor visitor) {
        visitor.visit(this);
    }
}