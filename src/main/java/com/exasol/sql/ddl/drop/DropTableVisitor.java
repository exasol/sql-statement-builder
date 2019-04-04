package com.exasol.sql.ddl.drop;

import com.exasol.sql.SqlStatementVisitor;

public interface DropTableVisitor extends SqlStatementVisitor {
    public void visit(DropTable dropTable);

    public void visit(CascadeConstraints cascadeConstraints);
}
