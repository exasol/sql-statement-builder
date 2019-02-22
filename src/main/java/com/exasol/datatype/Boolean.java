package com.exasol.datatype;

import com.exasol.sql.ddl.CreateTableVisitor;

public class Boolean implements DataType {
    private static final String NAME = "BOOLEAN";
    private static Boolean bool;

    private Boolean() {
    }

    public static synchronized Boolean bool() {
        if (bool == null) {
            bool = new Boolean();
        }
        return bool;
    }

    public static String getName() {
        return NAME;
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }
}
