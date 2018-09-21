package com.exasol.sql.expression;

/**
 * Visitor interface for a {@link BooleanTerm}
 */
public interface BooleanExpressionVisitor {
    void visit(Not not);

    void visit(Literal literal);

    void visit(BooleanTerm booleanTerm);

    void visit(And and);
}
