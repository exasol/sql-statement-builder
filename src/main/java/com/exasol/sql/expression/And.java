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
     * @param expressions boolean expressions to be connected by a logical AND
     */
    public And(final BooleanExpression... expressions) {
        super(expressions);
        this.operands = Arrays.asList(expressions);
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
    public void accept(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}