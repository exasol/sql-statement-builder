package com.exasol.sql.ddl;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.SqlStatement;

public class Create extends AbstractFragment implements SqlStatement, CreateFragment {
    /**
     * Create a new instance of a {@link Create}
     */
    public Create() {
        super(null);
    }

    public CreateTable table(final String tableName) {
        return new CreateTable(this, tableName);
    }

    @Override
    public void accept(final CreateVisitor visitor) {
        visitor.visit(this);
    }
}
