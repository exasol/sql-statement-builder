package com.exasol.datatype;

import com.exasol.sql.ddl.CreateTableVisitor;

/**
 * This class implements the Exasol-proprietary data type varchar
 */
public class Varchar extends AbstractStringDataType {
    private static final String NAME = "VARCHAR";
    private static final int MAX_LENGTH = 2000000;

    public Varchar(final int length) {
        super(length, MAX_LENGTH);
    }

    @Override
    public String getName() {
        return NAME;
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
