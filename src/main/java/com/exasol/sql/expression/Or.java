package com.exasol.sql.expression;

import com.exasol.sql.expression.literal.BooleanLiteral;

/**
 * This class represents a logical OR predicate.
 */
public class Or extends AbstractBooleanExpression {
    private final BooleanExpression[] operands;

    /**
     * Create a new {@link Or} instance
     *
     * @param operands boolean expressions to be connected by a logical Or
     */
    public Or(final BooleanExpression... operands) {
        this.operands = operands;
    }

    /**
     * Create a new {@link Or} instance
     *
     * @param values boolean literals to be connected by a logical Or
     */
    public Or(final boolean... values) {
        this(BooleanLiteral.toBooleanExpressions(values));
    }

    /**
     * Get the operands of this OR.
     * 
     * @return operands of this OR
     */
    public BooleanExpression[] getOperands() {
        return this.operands;
    }

    @Override
    public void accept(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}