package com.exasol.datatype;

public class Date implements DataType {
    private static Date date;

    private Date() {
    }

    public static Date date() {
        if (date == null) {
            date = new Date();
        }
        return date;
    }
}
