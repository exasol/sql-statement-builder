package com.exasol.sql.ddl;

import com.exasol.sql.Column;
import com.exasol.sql.FragmentVisitor;

public interface CreateVisitor extends FragmentVisitor {
    public void visit(final Create create);

    public void visit(final Column column);
}
