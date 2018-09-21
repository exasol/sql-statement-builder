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
}