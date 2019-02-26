package com.exasol.datatype;

import com.exasol.sql.Fragment;
import com.exasol.sql.ddl.CreateTableVisitor;

public class Varchar extends AbstractStringDataType {
    private static final String NAME = "VARCHAR";
    private static final int MAX_LENGTH = 2000000;

    public Varchar(final Fragment root, final int length) {
        super(root, length, MAX_LENGTH);
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
