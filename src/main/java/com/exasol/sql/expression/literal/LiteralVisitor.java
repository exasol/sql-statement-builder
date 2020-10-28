package com.exasol.sql.expression.literal;

import com.exasol.sql.expression.DoubleLiteral;

/**
 * Visitor for {@link Literal}.
 */
public interface LiteralVisitor {

    public void visit(final NullLiteral nullLiteral);

    public void visit(final StringLiteral literal);

    public void visit(final IntegerLiteral literal);

    public void visit(final LongLiteral literal);

    public void visit(final DoubleLiteral literal);

    public void visit(final FloatLiteral literal);

    public void visit(final BigDecimalLiteral literal);

    public void visit(final BooleanLiteral literal);

}
