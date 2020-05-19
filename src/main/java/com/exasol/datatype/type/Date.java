package com.exasol.datatype.type;

import com.exasol.sql.ColumnDefinitionVisitor;

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
    public void accept(final ColumnDefinitionVisitor visitor) {
        visitor.visit(this);
    }
}
