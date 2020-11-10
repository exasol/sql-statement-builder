package com.exasol.sql.expression;

import com.exasol.sql.UnnamedPlaceholder;
import com.exasol.sql.expression.function.Function;
import com.exasol.sql.expression.literal.Literal;

/**
 * Visitor interface for a {@link BooleanTerm}.
 */
public interface ValueExpressionVisitor {
    public void visit(final Literal literal);

    public void visit(final Function function);

    public void visit(UnnamedPlaceholder unnamedPlaceholder);

    public void visit(ColumnReference columnReference);

    public void visit(DefaultValue defaultValue);

    public void visit(BinaryArithmeticExpression expression);

    public void visit(BooleanExpression booleanExpression);

}