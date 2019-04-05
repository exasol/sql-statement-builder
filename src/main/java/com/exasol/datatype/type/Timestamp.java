package com.exasol.datatype.type;

import com.exasol.sql.ddl.create.CreateTableVisitor;

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
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }
}
