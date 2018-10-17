package com.exasol.sql.expression;

/**
 * This class represents
 */
public class And extends AbstractBooleanExpression {
    /**
     * Create a new {@link And} instance
     *
     * @param expressions boolean expressions to be connected by a logical AND
     */
    public And(final BooleanExpression... expressions) {
        super(expressions);
    }

    /**
     * Create a new {@link And} instance
     *
     * @param strings string literals to be connected by a logical AND
     */
    public And(final String... strings) {
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