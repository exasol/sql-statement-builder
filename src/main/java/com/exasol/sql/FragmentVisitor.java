package com.exasol.sql;

import com.exasol.sql.dql.*;
import com.exasol.sql.expression.*;

/**
 * This interface represents a visitor for SQL statement fragments.
 */
public interface FragmentVisitor {
    public void visit(final Select select);

    public void visit(final Field field);

    public void visit(FromClause fromClause);

    public void visit(TableReference tableReference);

    public void visit(Table table);

    public void visit(Join join);

    public void visit(AbstractBooleanExpression booleanExpression);

    public void visit(Not not);

    public void visit(Literal literal);
}