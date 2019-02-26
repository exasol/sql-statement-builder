package com.exasol.datatype;

import com.exasol.sql.ddl.CreateTableVisitor;

/**
 * This class implements the Exasol-proprietary data type boolean
 */
public class Boolean implements DataType {
    private static final String NAME = "BOOLEAN";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }
}
