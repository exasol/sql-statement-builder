package com.exasol.sql.expression;

import com.exasol.sql.expression.literal.BooleanLiteral;

/**
 * This class represents a logical AND predicate.
 */
public class And extends AbstractBooleanExpression {
    private final BooleanExpression[] operands;

    /**
     * Create a new {@link And} instance
     *
     * @param operands boolean expressions to be connected by a logical AND
     */
    public And(final BooleanExpression... operands) {
        this.operands = operands;
    }

    /**
     * Create a new {@link And} instance
     *
     * @param values boolean literals to be connected by a logical AND
     */
    public And(final boolean... values) {
        this(BooleanLiteral.toBooleanExpressions(values));
    }

    /**
     * Get the operands of this AND.
     * 
     * @return operands of this AND
     */
    public BooleanExpression[] getOperands() {
        return this.operands;
    }

    @Override
    public void accept(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
