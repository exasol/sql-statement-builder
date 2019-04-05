package com.exasol.datatype.type;

import com.exasol.sql.ddl.create.CreateTableVisitor;

/**
 * This class implements the Exasol-proprietary data type varchar
 */
@java.lang.SuppressWarnings("common-java:DuplicatedBlocks")
public class Varchar extends AbstractStringDataType {
    private static final String NAME = "VARCHAR";
    private static final int MAX_LENGTH = 2000000;

    /**
     * Create a new instance of an {@link Varchar} data type
     *
     * @param length pre-defined length for stored strings
     */
    public Varchar(final int length) {
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
