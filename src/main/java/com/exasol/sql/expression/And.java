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
     * @param values boolean literals to be connected by a logical AND
     */
    public And(final boolean... values) {
        this(BooleanLiteral.toBooleanExpressions(values));
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