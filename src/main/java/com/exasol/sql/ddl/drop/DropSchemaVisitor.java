package com.exasol.sql.ddl.drop;

import com.exasol.sql.ddl.Schema;

public interface DropSchemaVisitor {
    public void visit(DropSchema dropSchema);

    public void visit(Schema schema);

    public void visit(Cascade cascade);

    public void visit(Restrict restrict);
}
