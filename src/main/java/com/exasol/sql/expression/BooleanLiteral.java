package com.exasol.sql.expression;

/**
 * This class represents boolean literals (read "true" and "false")
 */
public class BooleanLiteral extends AbstractBooleanExpression {
    private static final String FALSE = "FALSE";
    private static final String TRUE = "TRUE";
    private final boolean value;

    private BooleanLiteral(final boolean value) {
        this.value = value;
    }

    public static BooleanExpression of(final boolean value) {
        return new BooleanLiteral(value);
    }

    /**
     * Create a new {@link BooleanLiteral} instance from a String
     *
     * @param value the string to be turned into a literal
     * @return new Literal instance
     * @throws IllegalArgumentException in case the literal is not recognized.
     */
    public static BooleanLiteral of(final String value) throws IllegalArgumentException {
        return new BooleanLiteral(parse(value));
    }

    private static boolean parse(final String value) throws IllegalArgumentException {
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
            }
        }
        throw new IllegalArgumentException("Unable to covert literal '" + value + "' into a boolean value");
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

    @Override
    public void acceptConcrete(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void dismissConcrete(final BooleanExpressionVisitor visitor) {
        visitor.leave(this);
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

    /**
     * Map an array of {@link String} to and array of BooleanExpressions
     *
     * @param strings string literals to be turned into boolean expressions
     * @return boolean expressions
     */
    public static BooleanExpression[] toBooleanExpressions(final String[] strings) {
        final BooleanExpression[] literals = new BooleanExpression[strings.length];
        for (int i = 0; i < strings.length; ++i) {
            literals[i] = BooleanLiteral.of(strings[i]);
        }
        return literals;
    }
}