package com.exasol.datatype.type;

import com.exasol.sql.ddl.create.CreateTableVisitor;

/**
 * This class implements the SQL data type boolean
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
