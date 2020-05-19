package com.exasol.sql.expression;

import com.exasol.sql.UnnamedPlaceholder;
import com.exasol.sql.expression.function.exasol.ExasolFunction;
import com.exasol.sql.expression.function.exasol.ExasolUdf;

/**
 * Visitor interface for a {@link BooleanTerm}.
 */
public interface ValueExpressionVisitor {
    public void visit(UnnamedPlaceholder unnamedPlaceholder);

    public void visit(StringLiteral literal);

    public void visit(IntegerLiteral literal);

    public void visit(LongLiteral literal);

    public void visit(DoubleLiteral literal);

    public void visit(FloatLiteral literal);

    public void visit(BooleanLiteral literal);

    public void visit(ColumnReference columnReference);

    public void visit(DefaultValue defaultValue);

    public void visit(ExasolFunction function);

    public void leave(ExasolFunction function);

    public void visit(ExasolUdf function);

    public void leave(ExasolUdf function);

    public void visit(BinaryArithmeticExpression expression);

    public void visit(NullLiteral nullLiteral);

    public void visit(BooleanExpression booleanExpression);
}