package com.exasol.datatype.type;

import com.exasol.sql.ColumnDefinitionVisitor;

/**
 * This class implements the SQl data type timestamp
 */
public class Timestamp implements DataType {
    private static final String NAME = "TIMESTAMP";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void accept(final ColumnDefinitionVisitor visitor) {
        visitor.visit(this);
    }
}
