package com.exasol.sql.expression;

import com.exasol.sql.expression.comparison.Comparison;
import com.exasol.sql.expression.literal.BooleanLiteral;

/**
 * Visitor interface for a {@link BooleanTerm}
 */
public interface BooleanExpressionVisitor {
    public void visit(Not not);

    public void visit(BooleanLiteral literal);

    public void visit(And and);

    public void leave(Not not);

    public void leave(And and);

    public void visit(Or or);

    public void leave(Or or);

    public void visit(Comparison comparison);
}