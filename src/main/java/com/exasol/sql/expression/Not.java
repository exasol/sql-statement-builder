package com.exasol.sql.expression;

/**
 * This class implements the logical unary NOT predicate.
 */
public class Not extends AbstractBooleanExpression {
    /**
     * Create a new instance of a unary {@link Not} from a boolean literal
     *
     * @param value boolean literal to be negated
     */
    public Not(final boolean value) {
        super(BooleanLiteral.of(value));
    }

    /**
     * Create a new instance of a unary {@link Not} from a string literal
     *
     * @param value string literal to be negated
     */
    public Not(final String value) {
        super(BooleanLiteral.of(value));
    }

    /**
     * Create a new instance of a unary {@link Not} from a boolean expression
     *
     * @param expression boolean expression literal to be negated
     */
    public Not(final BooleanExpression expression) {
        super(expression);
    }

    @Override
    public void acceptConcrete(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void dismissConcrete(final BooleanExpressionVisitor visitor) {
        visitor.leave(this);
    }
}