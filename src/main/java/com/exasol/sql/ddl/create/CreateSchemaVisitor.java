package com.exasol.sql.ddl.create;

import com.exasol.sql.ddl.Schema;

public interface CreateSchemaVisitor {
    public void visit(CreateSchema createSchema);

    public void visit(Schema schema);
}
