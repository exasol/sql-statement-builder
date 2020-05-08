package com.exasol.sql.expression;

/**
 * This class represents a key word without quotation inside a value expression.
 */
public class KeyWord extends AbstractValueExpression {
    private final String value;

    private KeyWord(final String value) {
        this.value = value;
    }

    /**
     * Create a new {@link KeyWord} from a String.
     *
     * @param value content
     * @return new {@link KeyWord}
     */
    public static KeyWord of(final String value) {
        return new KeyWord(value);
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.value;
    }
}