package com.exasol.sql.expression.literal;

/**
 * This class represents a null literal.
 */
// [impl->dsn~literal-values~2]
public final class NullLiteral extends AbstractLiteral {
    private static final NullLiteral instance = new NullLiteral();

    public static NullLiteral nullLiteral() {
        return instance;
    }

    private NullLiteral() {
        // intentionally left blank
    }

    @Override
    public void accept(final LiteralVisitor visitor) {
        visitor.visit(this);
    }
}