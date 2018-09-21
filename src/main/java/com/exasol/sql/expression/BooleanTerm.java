package com.exasol.sql.expression;

public class BooleanTerm extends AbstractBooleanExpression {
    public BooleanTerm(final BooleanExpression nestedExpression) {
        super(nestedExpression);
    }

    public static BooleanExpression not(final String string) {
        return create(new Not(string));
    }

    private static BooleanExpression create(final BooleanExpression nestedExpression) {
        final BooleanExpression expression = new BooleanTerm(nestedExpression);
        return expression;
    }

    public static BooleanExpression not(final BooleanExpression expression) {
        return create(new Not(expression));
    }

    public static BooleanExpression and(final String... strings) {
        return create(new And(strings));
    }

    public static BooleanExpression and(final BooleanExpression expression, final String string) {
        return create(new And(expression, Literal.of(string)));
    }

    public static BooleanExpression and(final String literal, final BooleanExpression expression) {
        return create(new And(Literal.of(literal), expression));
    }

    public static BooleanExpression and(final BooleanExpression... expressions) {
        return create(new And(expressions));
    }

    @Override
    public void acceptConcrete(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}