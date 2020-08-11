package com.exasol.sql.expression;

import java.math.BigDecimal;

/**
 * This class represents BigDecimal literals.
 */
// [impl->dsn~literal-values~1]
public class BigDecimalLiteral extends AbstractValueExpression {
    private final BigDecimal literal;

    private BigDecimalLiteral(BigDecimal literal) {
        this.literal = literal;
    }

    /**
     * Create a new {@link BigDecimalLiteral} from a BigDecimal.
     *
     * @param literal content
     * @return new {@link StringLiteral}
     */
    public static BigDecimalLiteral of(BigDecimal literal) {
        return new BigDecimalLiteral(literal);
    }

    @Override
    public void accept(ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return literal.toString();
    }
}
