package com.exasol.sql.expression;

import com.exasol.sql.UnnamedPlaceholder;

/**
 * Visitor interface for a {@link BooleanTerm}
 */
public interface ValueExpressionVisitor {
    public void visit(UnnamedPlaceholder unnamedPlaceholder);

    public void visit(StringLiteral literal);

    public void visit(IntegerLiteral literal);
}