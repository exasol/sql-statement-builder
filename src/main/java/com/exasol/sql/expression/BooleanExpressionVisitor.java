package com.exasol.sql.expression;

/**
 * Visitor interface for a {@link BooleanTerm}
 */
public interface BooleanExpressionVisitor {
    void visit(Not not);

    void visit(Literal literal);

    void visit(BooleanTerm booleanTerm);

    void visit(And and);

    void leave(Not not);

    void leave(Literal literal);

    void leave(BooleanTerm booleanTerm);

    void leave(And and);

    void visit(Or or);

    void leave(Or or);
}
