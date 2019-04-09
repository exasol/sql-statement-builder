package com.exasol.sql.ddl.drop;

import com.exasol.sql.Table;

public interface DropTableVisitor {
    public void visit(Table table);

    public void visit(DropTable dropTable);

    public void visit(CascadeConstraints cascadeConstraints);
}
