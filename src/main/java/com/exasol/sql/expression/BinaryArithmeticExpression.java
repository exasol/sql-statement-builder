package com.exasol.sql.expression;

import com.exasol.util.AbstractTreeNode;

/**
 * This class represents a binary arithmetic expression.
 */
public class BinaryArithmeticExpression extends AbstractTreeNode implements ValueExpression {
    private final BinaryArithmeticOperator arithmeticOperator;
    private final ValueExpression right;
    private final ValueExpression left;

    private BinaryArithmeticExpression(final BinaryArithmeticOperator arithmeticOperator, final ValueExpression right,
            final ValueExpression left) {
        this.arithmeticOperator = arithmeticOperator;
        this.right = right;
        this.left = left;
        addChild(right);
        addChild(left);
    }

    /**
     * @param operator
     * @param left
     * @param right
     * @return
     */
    public static BinaryArithmeticExpression of(final BinaryArithmeticOperator operator, final ValueExpression left,
            final ValueExpression right) {
        return new BinaryArithmeticExpression(operator, left, right);
    }

    public BinaryArithmeticOperator getArithmeticOperator() {
        return this.arithmeticOperator;
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
        this.right.accept(visitor);
        visitor.leave(this);
        this.left.accept(visitor);
    }

    public enum BinaryArithmeticOperator {
        ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");

        private final String stringOperatorRepresentation;

        BinaryArithmeticOperator(final String stringOperatorRepresentation) {
            this.stringOperatorRepresentation = stringOperatorRepresentation;
        }

        public String getStringOperatorRepresentation() {
            return this.stringOperatorRepresentation;
        }
    }
}
