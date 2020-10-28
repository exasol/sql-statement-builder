package com.exasol.sql.expression.literal;

import java.math.BigDecimal;

/**
 * This class represents BigDecimal literals.
 */
// [impl->dsn~literal-values~2]
public class BigDecimalLiteral extends AbstractLiteral {
    private final BigDecimal literal;

    private BigDecimalLiteral(final BigDecimal literal) {
        this.literal = literal;
    }

    /**
     * Create a new {@link BigDecimalLiteral} from a BigDecimal.
     *
     * @param literal content
     * @return new {@link StringLiteral}
     */
    public static BigDecimalLiteral of(final BigDecimal literal) {
        return new BigDecimalLiteral(literal);
    }

    /**
     * Get the value of the {@link BigDecimalLiteral}.
     *
     * @return BigDecimal value
     */
    public BigDecimal getValue() {
        return this.literal;
    }

    @Override
    public void accept(final LiteralVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.literal.toString();
    }
}
