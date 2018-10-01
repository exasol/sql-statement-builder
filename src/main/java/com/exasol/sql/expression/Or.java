package com.exasol.sql.expression;

/**
 * This class represents
 */
public class Or extends AbstractBooleanExpression {
    /**
     * Create a new {@link Or} instance
     *
     * @param expressions boolean expressions to be connected by a logical Or
     */
    public Or(final BooleanExpression... expressions) {
        super(expressions);
    }

    /**
     * Create a new {@link Or} instance
     *
     * @param strings string literals to be connected by a logical Or
     */
    public Or(final String... strings) {
        this(Literal.toBooleanExpressions(strings));
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