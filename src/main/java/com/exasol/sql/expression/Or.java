package com.exasol.sql.expression;

import com.exasol.sql.expression.literal.BooleanLiteral;

/**
 * This class represents a logical OR predicate.
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
     * @param values boolean literals to be connected by a logical Or
     */
    public Or(final boolean... values) {
        this(BooleanLiteral.toBooleanExpressions(values));
    }

    @Override
    public void accept(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}