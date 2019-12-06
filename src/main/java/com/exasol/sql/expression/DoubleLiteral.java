package com.exasol.sql.expression;

/**
 * This class represents double literals.
 */
public class DoubleLiteral extends AbstractValueExpression {
    private final double value;

    private DoubleLiteral(final double value) {
        this.value = value;
    }

    /**
     * Create a new {@link DoubleLiteral} from a double.
     *
     * @param value content
     * @return new {@link DoubleLiteral}
     */
    public static DoubleLiteral of(final double value) {
        return new DoubleLiteral(value);
    }

    /**
     * Get the value of the {@link DoubleLiteral}
     *
     * @return double value
     */
    public double getValue() {
        return this.value;
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }
}