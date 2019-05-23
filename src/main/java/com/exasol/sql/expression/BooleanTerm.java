package com.exasol.sql.expression;

// [impl->dsn~boolean-operators~1]
public abstract class BooleanTerm extends AbstractBooleanExpression {
    private BooleanTerm() {
        super();
    }

    public static BooleanExpression not(final boolean value) {
        return new Not(value);
    }

    public static BooleanExpression not(final BooleanExpression expression) {
        return new Not(expression);
    }

    public static BooleanExpression and(final boolean... values) {
        return new And(values);
    }

    public static BooleanExpression and(final BooleanExpression expression, final boolean value) {
        return new And(expression, BooleanLiteral.of(value));
    }

    public static BooleanExpression and(final boolean value, final BooleanExpression expression) {
        return new And(BooleanLiteral.of(value), expression);
    }

    public static BooleanExpression and(final BooleanExpression... expressions) {
        return new And(expressions);
    }

    public static BooleanExpression or(final boolean... values) {
        return new Or(values);
    }

    public static BooleanExpression or(final BooleanExpression expression, final boolean value) {
        return new Or(expression, BooleanLiteral.of(value));
    }

    public static BooleanExpression or(final boolean value, final BooleanExpression expression) {
        return new Or(BooleanLiteral.of(value), expression);
    }

    public static BooleanExpression or(final BooleanExpression... expressions) {
        return new Or(expressions);
    }

    // [impl->dsn~boolean-operation.comparison.constructing-from-strings~1]
    public static BooleanExpression compare(final ValueExpression left, final String operatorSymbol,
            final ValueExpression right) {
        return new Comparison(ComparisonOperator.ofSymbol(operatorSymbol), left, right);
    }

    // [impl->dsn~boolean-operation.comparison.constructing-from-enum~1]
    public static BooleanExpression compare(final ValueExpression left, final ComparisonOperator operator,
            final ValueExpression right) {
        return new Comparison(operator, left, right);
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression eq(final ValueExpression left, final ValueExpression right) {
        return new Comparison(ComparisonOperator.EQUAL, left, right);
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression ne(final ValueExpression left, final ValueExpression right) {
        return new Comparison(ComparisonOperator.NOT_EQUAL, left, right);
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression lt(final ValueExpression left, final ValueExpression right) {
        return new Comparison(ComparisonOperator.LESS_THAN, left, right);
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression gt(final ValueExpression left, final ValueExpression right) {
        return new Comparison(ComparisonOperator.GREATER_THAN, left, right);
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression le(final ValueExpression left, final ValueExpression right) {
        return new Comparison(ComparisonOperator.LESS_THAN_OR_EQUAL, left, right);
    }

    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression ge(final ValueExpression left, final ValueExpression right) {
        return new Comparison(ComparisonOperator.GREATER_THAN_OR_EQUAL, left, right);
    }

    /**
     * Create a logical operation from an operator name and a list of operands
     *
     * @param operator    name of the operator
     * @param expressions operands
     * @return instance of either {@link And}, {@link Or} or {@link Not}
     * @throws IllegalArgumentException if the operator is unknown or null
     */
    public static BooleanExpression operation(final String operator, final BooleanExpression... expressions) {
        switch (operator.toLowerCase()) {
        case "and":
            return new And(expressions);
        case "or":
            return new Or(expressions);
        case "not":
            if (expressions.length == 1) {
                return new Not(expressions[0]);
            } else {
                throw new IllegalArgumentException(
                        "Logical \"not\" must have exactly one operand. Got " + expressions.length + ".");
            }
        default:
            throw new IllegalArgumentException(
                    "Unknown boolean connector \"" + operator + "\". Must be one of \"and\" or \"or\".");
        }
    }
}