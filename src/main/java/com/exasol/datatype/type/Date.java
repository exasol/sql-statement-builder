package com.exasol.datatype.type;

import com.exasol.sql.ddl.create.CreateTableVisitor;

/**
 * This class implements the SQL data type date
 */
public class Date implements DataType {
    private static final String NAME = "DATE";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }
}
