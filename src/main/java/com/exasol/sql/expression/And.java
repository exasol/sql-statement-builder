package com.exasol.sql.expression;

import java.util.Arrays;
import java.util.List;

import com.exasol.sql.expression.literal.BooleanLiteral;

/**
 * This class represents a logical AND predicate.
 */
public class And extends AbstractBooleanExpression {
    private final List<BooleanExpression> operands;

    /**
     * Create a new {@link And} instance
     *
     * @param operands boolean expressions to be connected by a logical AND
     */
    public And(final BooleanExpression... operands) {
        this.operands = Arrays.asList(operands);
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
    public List<BooleanExpression> getOperands() {
        return this.operands;
    }

    @Override
    public void accept(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
