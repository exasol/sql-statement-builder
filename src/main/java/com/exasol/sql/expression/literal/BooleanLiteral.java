package com.exasol.sql.expression.literal;

import com.exasol.sql.expression.BooleanExpression;
import com.exasol.sql.expression.BooleanExpressionVisitor;

/**
 * This class represents boolean literals (read "true" and "false")
 */
// [impl->dsn~boolean-literals~1]
public final class BooleanLiteral extends AbstractLiteral implements BooleanExpression {
    private static final String FALSE = "FALSE";
    private static final String TRUE = "TRUE";
    private final boolean value;

    private BooleanLiteral(final boolean value) {
        this.value = value;
    }

    public static BooleanLiteral of(final boolean value) {
        return new BooleanLiteral(value);
    }

    /**
     * Create a new {@link BooleanLiteral} instance from a String.
     *
     * @param value the string to be turned into a literal
     * @return new {@link BooleanLiteral} instance
     * @throws IllegalArgumentException in case the literal is not recognized.
     */
    public static BooleanLiteral of(final String value) {
        return new BooleanLiteral(parse(value));
    }

    private static boolean parse(final String value) {
        if (value == null) {
            throw new IllegalArgumentException("Unable to covert null value into a boolean value");
        } else {
            switch (value.toUpperCase()) {
            case TRUE:
            case "T":
            case "Y":
            case "YES":
            case "ON":
            case "ENABLED":
            case "1":
                return true;
            case FALSE:
            case "F":
            case "N":
            case "NO":
            case "OFF":
            case "DISABLED":
            case "0":
                return false;
            default:
                throw new IllegalArgumentException("Unable to covert literal '" + value + "' into a boolean value");
            }
        }
    }

    @Override
    public String toString() {
        return this.value ? TRUE : FALSE;
    }

    /**
     * Get boolean value this literal represents.
     *
     * @return boolean value.
     */
    public boolean toBoolean() {
        return this.value;
    }

    /**
     * Map an array of booleans to and array of BooleanExpressions
     *
     * @param values string literals to be turned into boolean expressions
     * @return boolean expressions
     */
    public static BooleanExpression[] toBooleanExpressions(final boolean[] values) {
        final BooleanExpression[] literals = new BooleanExpression[values.length];
        for (int i = 0; i < values.length; ++i) {
            literals[i] = BooleanLiteral.of(values[i]);
        }
        return literals;
    }

    @Override
    public void accept(final LiteralVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}