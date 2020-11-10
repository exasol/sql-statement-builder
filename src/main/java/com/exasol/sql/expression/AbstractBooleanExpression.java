package com.exasol.sql.expression;

/**
 * Abstract base class for all types of BooleanExpressions.
 */
public abstract class AbstractBooleanExpression implements BooleanExpression {

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}