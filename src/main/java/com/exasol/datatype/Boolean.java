package com.exasol.datatype;

public class Boolean implements DataType {
    private static Boolean bool;

    private Boolean() {
    }

    public static Boolean bool() {
        if (bool == null) {
            bool = new Boolean();
        }
        return bool;
    }
}
