package com.exasol.sql.expression.literal;

/**
 * Visitor for {@link Literal}.
 */
public interface LiteralVisitor {
    /**
     * Visit a literal null.
     *
     * @param nullLiteral literal null to visit
     */
    public void visit(final NullLiteral nullLiteral);

    /**
     * Visit a string literal.
     *
     * @param literal string literal to visit
     */
    public void visit(final StringLiteral literal);

    /**
     * Visit an integer literal.
     *
     * @param literal integer literal to visit
     */
    public void visit(final IntegerLiteral literal);

    /**
     * Visit a long integer literal.
     *
     * @param literal long integer literal to visit
     */
    public void visit(final LongLiteral literal);

    /**
     * Visit a double-precision floating point literal.
     *
     * @param literal double-precision floating point literal ot visit
     */
    public void visit(final DoubleLiteral literal);

    /**
     * Visit a single-precision floating point literal.
     *
     * @param literal single-precision floating point literal
     */
    public void visit(final FloatLiteral literal);

    /**
     * Visit a big decimal literal.
     *
     * @param literal big decimal literal to visit
     */
    public void visit(final BigDecimalLiteral literal);

    /**
     * Visit a boolean literal.
     *
     * @param literal boolean literal to visit
     */
    public void visit(final BooleanLiteral literal);
}