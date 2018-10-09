package com.exasol.sql.expression;

/**
 * Visitor interface for a {@link BooleanTerm}
 */
public interface ValueExpressionVisitor {
    void visit(Value value);
}