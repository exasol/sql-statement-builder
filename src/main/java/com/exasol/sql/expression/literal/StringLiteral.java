package com.exasol.sql.expression.literal;

/**
 * This class represents string literals (or character literals in SQL terms).
 */
// [impl->dsn~literal-values~2]
public final class StringLiteral extends AbstractLiteral {
    private final String literal;

    private StringLiteral(final String literal) {
        this.literal = literal;
    }

    /**
     * Create a new {@link StringLiteral} from a string.
     *
     * @param literal content
     * @return new {@link StringLiteral}
     */
    public static StringLiteral of(final String literal) {
        return new StringLiteral(literal);
    }

    /**
     * Create a new {@link StringLiteral} from a character.
     *
     * @param literal content
     * @return new {@link StringLiteral}
     */
    public static StringLiteral of(final char literal) {
        return new StringLiteral(String.valueOf(literal));
    }

    @Override
    public void accept(final LiteralVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.literal;
    }
}
