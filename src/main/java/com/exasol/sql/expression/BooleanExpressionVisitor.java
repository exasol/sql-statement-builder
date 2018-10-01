package com.exasol.sql.expression;

/**
 * Visitor interface for a {@link BooleanTerm}
 */
public interface BooleanExpressionVisitor {
    public void visit(Not not);

    public void visit(Literal literal);

    public void visit(And and);

    public void leave(Not not);

    public void leave(Literal literal);

    public void leave(And and);

    public void visit(Or or);

    public void leave(Or or);

    public void visit(Comparison comparison);

    public void leave(Comparison comparison);
}