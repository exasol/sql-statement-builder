package com.exasol.sql.expression;

/**
 * This class represents float literals.
 */
// [impl->dsn~literal-values~2]
public class FloatLiteral extends AbstractValueExpression {
    private final float value;

    private FloatLiteral(final float value) {
        this.value = value;
    }

    /**
     * Create a new {@link FloatLiteral} from a float.
     *
     * @param value content
     * @return new {@link FloatLiteral}
     */
    public static FloatLiteral of(final float value) {
        return new FloatLiteral(value);
    }

    /**
     * Get the value of the {@link FloatLiteral}
     *
     * @return float value
     */
    public float getValue() {
        return this.value;
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return Float.toString(this.value);
    }
}
