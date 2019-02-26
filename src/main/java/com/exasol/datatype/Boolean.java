package com.exasol.datatype;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;
import com.exasol.sql.ddl.CreateTableVisitor;

public class Boolean extends AbstractFragment implements DataType {
    private static final String NAME = "BOOLEAN";

    public Boolean(final Fragment root) {
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
