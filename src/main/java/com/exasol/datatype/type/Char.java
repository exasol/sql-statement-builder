package com.exasol.datatype.type;

import com.exasol.sql.ddl.CreateTableVisitor;

/**
 * This class implements the SQL data type char
 */
@java.lang.SuppressWarnings("common-java:DuplicatedBlocks")
public class Char extends AbstractStringDataType {
    private static final String NAME = "CHAR";
    private static final int MAX_LENGTH = 2000;

    /**
     * Create a new instance of an {@link Char} data type
     *
     * @param length pre-defined length for stored strings
     */
    public Char(final int length) {
        super(length, MAX_LENGTH, NAME);
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    protected AbstractStringDataType self() {
        return this;
    }
}
