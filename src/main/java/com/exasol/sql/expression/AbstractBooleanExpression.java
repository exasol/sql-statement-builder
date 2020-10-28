package com.exasol.sql.expression;

import java.util.List;

import com.exasol.util.AbstractBottomUpTreeNode;

/**
 * Abstract base class for all types of BooleanExpressions.
 */
public abstract class AbstractBooleanExpression extends AbstractBottomUpTreeNode implements BooleanExpression {
    protected AbstractBooleanExpression() {
        super();
    }

    protected AbstractBooleanExpression(final BooleanExpression expression) {
        super(expression);
    }

    protected AbstractBooleanExpression(final BooleanExpression... expressions) {
        super(expressions);
    }

    @Override
    public List<BooleanExpression> getChildren() {
        return (List<BooleanExpression>) super.getChildren();
    }

    @Override
    public BooleanExpression getChild(final int index) {
        return (BooleanExpression) super.getChild(index);
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}