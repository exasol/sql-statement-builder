package com.exasol.datatype;

import com.exasol.sql.ddl.CreateTableVisitor;

public class Date implements DataType {
    private static final String NAME = "DATE";
    private static Date date;

    private Date() {
    }

    public static Date date() {
        if (date == null) {
            date = new Date();
        }
        return date;
    }

    public static String getName() {
        return NAME;
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }
}
