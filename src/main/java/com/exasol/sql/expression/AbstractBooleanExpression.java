package com.exasol.sql.expression;

import com.exasol.util.AbstractBottomUpTreeNode;
import com.exasol.util.TreeNode;

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
    public void accept(final BooleanExpressionVisitor visitor) {
        acceptConcrete(visitor);
        for (final TreeNode child : this.getChildren()) {
            ((BooleanExpression) child).accept(visitor);
        }
        dismissConcrete(visitor);
    }

    /**
     * Sub-classes must override this method so that the visitor knows the type of the visited class at compile time.
     *
     * @param visitor visitor to accept
     */
    public abstract void acceptConcrete(final BooleanExpressionVisitor visitor);

    /**
     * Sub-classes must override this method so that the visitor knows the type of the visited class at compile time.
     *
     * @param visitor visitor to accept
     */
    public abstract void dismissConcrete(final BooleanExpressionVisitor visitor);

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}