package com.exasol.sql.expression;

import com.exasol.sql.UnnamedPlaceholder;
import com.exasol.sql.expression.function.Function;
import com.exasol.sql.expression.literal.Literal;

/**
 * Visitor interface for a {@link BooleanTerm}.
 */
public interface ValueExpressionVisitor {
    /**
     * Visit a literal.
     *
     * @param literal literal to visit
     */
    public void visit(final Literal literal);

    /**
     * Visit a function.
     *
     * @param function function to visit
     */
    public void visit(final Function function);

    /**
     * Visit an unnamed placeholder.
     *
     * @param unnamedPlaceholder placeholder to visit
     */
    public void visit(final UnnamedPlaceholder unnamedPlaceholder);

    /**
     * Visit a column reference
     *
     * @param columnReference column reference to visit
     */
    public void visit(final ColumnReference columnReference);

    /**
     * Visit a default value.
     *
     * @param defaultValue default value to visit
     */
    public void visit(final DefaultValue defaultValue);

    /**
     * Visit a binary arithmetic expression.
     *
     * @param expression binary arithmetic expression to visit
     */
    public void visit(final BinaryArithmeticExpression expression);

    /**
     * Visit a boolean expression.
     *
     * @param booleanExpression boolean expression to visit
     */
    public void visit(final BooleanExpression booleanExpression);
}