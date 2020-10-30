package com.exasol.sql.expression;

import java.util.Arrays;
import java.util.List;

import com.exasol.sql.expression.literal.BooleanLiteral;

/**
 * This class represents a logical OR predicate.
 */
public class Or extends AbstractBooleanExpression {
    private final List<BooleanExpression> operands;

    /**
     * Create a new {@link Or} instance
     *
     * @param operands boolean expressions to be connected by a logical Or
     */
    public Or(final BooleanExpression... operands) {
        this.operands = Arrays.asList(operands);
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
    public List<BooleanExpression> getOperands() {
        return this.operands;
    }

    @Override
    public void accept(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}