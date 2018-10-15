package com.exasol.sql.expression;

/**
 * This class represents a concrete value link a number or a text.
 */
public class Value extends AbstractValueExpression {
    private final Object value;

    /**
     * Create a new instance of a {@link Value}
     *
     * @param value contained value
     */
    public Value(final Object value) {
        this.value = value;
    }

    /**
     * Get the value
     *
     * @return value
     */
    public Object get() {
        return this.value;
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}