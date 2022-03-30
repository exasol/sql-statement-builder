package com.exasol.sql.expression;

import com.exasol.sql.expression.comparison.Comparison;
import com.exasol.sql.expression.literal.BooleanLiteral;
import com.exasol.sql.expression.predicate.Predicate;

/**
 * Visitor interface for a {@link BooleanTerm}
 */
public interface BooleanExpressionVisitor {
    /**
     * Visit a unary {@code NOT} expression.
     *
     * @param not unary {@code NOT} to visit
     */
    public void visit(final Not not);

    /**
     * Visit a boolean literal.
     *
     * @param literal boolean literal to visit
     */
    public void visit(final BooleanLiteral literal);

    /**
     * Visit a logical {@code AND} expression.
     *
     * @param and logical {@code AND} to visit
     */
    public void visit(final And and);

    /**
     * Visit a logical {@code OR} expression.
     *
     * @param or logical {@code OR} to visit
     */
    public void visit(final Or or);

    /**
     * Visit a comparison.
     *
     * @param comparison comparison to visit
     */
    public void visit(final Comparison comparison);

    /**
     * Visit a predicate.
     *
     * @param predicate predicate to visit
     */
    public void visit(final Predicate predicate);
}