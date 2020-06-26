package com.exasol.sql.expression;

/**
 * This class represents a null literal.
 */
// [impl->dsn~literal-values~1]
public final class NullLiteral extends AbstractValueExpression {
    private static final NullLiteral instance = new NullLiteral();

    public static NullLiteral nullLiteral() {
        return instance;
    }

    private NullLiteral() {
        // intentionally left blank
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}