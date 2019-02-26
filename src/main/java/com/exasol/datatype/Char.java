package com.exasol.datatype;

import com.exasol.sql.Fragment;
import com.exasol.sql.ddl.CreateTableVisitor;

public class Char extends AbstractStringDataType {
    private static final String NAME = "CHAR";
    private static final int MAX_LENGTH = 2000;

    public Char(final Fragment root, final int length) {
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
