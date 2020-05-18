package com.exasol.datatype.type;

import com.exasol.sql.ColumnDefinitionVisitor;

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
    public void accept(final ColumnDefinitionVisitor visitor) {
        visitor.visit(this);
    }
}
