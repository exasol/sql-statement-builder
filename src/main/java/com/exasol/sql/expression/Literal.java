package com.exasol.sql.expression;

public class Literal extends AbstractBooleanExpression {
    private final String literal;

    private Literal(final String literal) {
        this.literal = literal;
    }

    /**
     * Create a new {@link Literal} instance from a String
     *
     * @param string the string to be turned into a literal
     * @return new Literal instance
     */
    public static Literal of(final String string) {
        return new Literal(string);
    }

    @Override
    public String toString() {
        return this.literal;
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
     * Map an array of {@link String} to and array of BooleanExpressions
     *
     * @param strings string literals to be turned into boolean expressions
     * @return boolean expressions
     */
    public static BooleanExpression[] toBooleanExpressions(final String[] strings) {
        final BooleanExpression[] literals = new BooleanExpression[strings.length];
        for (int i = 0; i < strings.length; ++i) {
            literals[i] = Literal.of(strings[i]);
        }
        return literals;
    }
}