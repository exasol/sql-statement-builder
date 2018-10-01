package com.exasol.sql.expression;

public abstract class BooleanTerm extends AbstractBooleanExpression {
    private BooleanTerm() {
        super();
    }

    public static BooleanExpression not(final String string) {
        return new Not(string);
    }

    public static BooleanExpression not(final BooleanExpression expression) {
        return new Not(expression);
    }

    public static BooleanExpression and(final String... strings) {
        return new And(strings);
    }

    public static BooleanExpression and(final BooleanExpression expression, final String string) {
        return new And(expression, Literal.of(string));
    }

    public static BooleanExpression and(final String literal, final BooleanExpression expression) {
        return new And(Literal.of(literal), expression);
    }

    public static BooleanExpression and(final BooleanExpression... expressions) {
        return new And(expressions);
    }

    public static BooleanExpression or(final String... strings) {
        return new Or(strings);
    }

    public static BooleanExpression or(final BooleanExpression expression, final String string) {
        return new Or(expression, Literal.of(string));
    }

    public static BooleanExpression or(final String literal, final BooleanExpression expression) {
        return new Or(Literal.of(literal), expression);
    }

    public static BooleanExpression or(final BooleanExpression... expressions) {
        return new Or(expressions);
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression compare(final String left, final String operatorSymbol, final String right) {
        return new Comparison(ComparisonOperator.ofSymbol(operatorSymbol), Literal.of(left), Literal.of(right));
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression eq(final String left, final String right) {
        return new Comparison(ComparisonOperator.EQUAL, Literal.of(left), Literal.of(right));
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression ne(final String left, final String right) {
        return new Comparison(ComparisonOperator.NOT_EQUAL, Literal.of(left), Literal.of(right));
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression lt(final String left, final String right) {
        return new Comparison(ComparisonOperator.LESS_THAN, Literal.of(left), Literal.of(right));
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression gt(final String left, final String right) {
        return new Comparison(ComparisonOperator.GREATER_THAN, Literal.of(left), Literal.of(right));
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression le(final String left, final String right) {
        return new Comparison(ComparisonOperator.LESS_THAN_OR_EQUAL, Literal.of(left), Literal.of(right));
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression ge(final String left, final String right) {
        return new Comparison(ComparisonOperator.GREATER_THAN_OR_EQUAL, Literal.of(left), Literal.of(right));
    }
}