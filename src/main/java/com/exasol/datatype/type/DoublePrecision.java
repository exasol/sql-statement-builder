package com.exasol.datatype.type;

import com.exasol.sql.ddl.create.CreateTableVisitor;

/**
 * This class implements a double precision floating point number data type
 */
public class DoublePrecision implements DataType {
    private static final String NAME = "DOUBLE PRECISION";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }
}
