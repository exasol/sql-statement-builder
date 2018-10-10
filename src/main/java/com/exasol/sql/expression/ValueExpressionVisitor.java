package com.exasol.sql.expression;

import com.exasol.sql.UnnamedPlaceholder;

/**
 * Visitor interface for a {@link BooleanTerm}
 */
public interface ValueExpressionVisitor {
    void visit(Value value);

    void visit(UnnamedPlaceholder unnamedPlaceholder);
}