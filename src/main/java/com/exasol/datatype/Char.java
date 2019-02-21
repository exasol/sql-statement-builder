package com.exasol.datatype;

public class Char implements DataType {
    private final int size;

    public Char(final int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
