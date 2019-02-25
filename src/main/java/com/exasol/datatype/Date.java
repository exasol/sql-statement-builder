package com.exasol.datatype;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;
import com.exasol.sql.ddl.CreateTableVisitor;

public final class Date extends AbstractFragment implements DataType {
    private static final String NAME = "DATE";

    public Date(final Fragment root) {
        super(root);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }
}
