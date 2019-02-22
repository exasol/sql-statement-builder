package com.exasol.datatype;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;
import com.exasol.sql.ddl.CreateTableFragment;
import com.exasol.sql.ddl.CreateTableVisitor;

public class Char extends AbstractFragment implements DataType, CreateTableFragment {
    private static final String NAME = "CHAR";
    private final int size;

    public Char(final Fragment root, final int size) {
        super(root);
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return NAME;
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }
}
