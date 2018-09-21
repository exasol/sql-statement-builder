package com.exasol.sql.expression;

/**
 * This class implements the logical unary NOT
 */
public class Not extends AbstractBooleanExpression {
    /**
     * Create a new instance of a unary {@link Not} from a string literal
     *
     * @param string string literal to be negated
     */
    protected Not(final String string) {
        super(Literal.of(string));
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