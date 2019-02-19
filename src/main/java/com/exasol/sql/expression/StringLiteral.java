package com.exasol.sql.expression;

/**
 * This class represents string literals (or character literals in SQL terms).
 */
public final class StringLiteral extends AbstractValueExpression {
    private final String literal;

    private StringLiteral(final String literal) {
        this.literal = literal;
    }

    /**
     * Create a new {@link StringLiteral} from a string
     *
     * @param literal content
     * @return new {@link StringLiteral}
     */
    public static StringLiteral of(final String literal) {
        return new StringLiteral(literal);
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.literal;
    }
}