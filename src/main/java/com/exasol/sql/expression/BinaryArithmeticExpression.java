package com.exasol.sql.expression;

import com.exasol.util.AbstractTreeNode;

/**
 * This class represents a binary arithmetic expression.
 * <p>
 * Examples: +, -, *, /
 * </p>
 */
public class BinaryArithmeticExpression extends AbstractTreeNode implements ValueExpression {
    private final BinaryArithmeticOperator arithmeticOperator;
    private final ValueExpression left;
    private final ValueExpression right;

    private BinaryArithmeticExpression(final BinaryArithmeticOperator arithmeticOperator, final ValueExpression left,
            final ValueExpression right) {
        this.arithmeticOperator = arithmeticOperator;
        this.left = left;
        this.right = right;
        addChild(left);
        addChild(right);
    }

    /**
     * Create a new {@link BinaryArithmeticExpression} instance.
     * 
     * @param operator arithmetic operator represented by {@link BinaryArithmeticOperator}
     * @param left left part of the expression
     * @param right right part of the expression
     * @return new {@link BinaryArithmeticExpression} instance
     */
    public static BinaryArithmeticExpression of(final BinaryArithmeticOperator operator, final ValueExpression left,
            final ValueExpression right) {
        return new BinaryArithmeticExpression(operator, left, right);
    }

    /**
     * Get a string representation of a member of this enum class. For example, + represents an ADD operator.
     *
     * @return string representation of an arithmetic operator
     */
    public String getStringOperatorRepresentation() {
        return this.arithmeticOperator.getStringOperatorRepresentation();
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
        this.left.accept(visitor);
        visitor.addOperator(this);
        this.right.accept(visitor);
        visitor.leave(this);
    }

    /**
     * This enum represents arithmetic operators in an SQL statement.
     */
    public enum BinaryArithmeticOperator {
        ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");

        private final String stringOperatorRepresentation;

        BinaryArithmeticOperator(final String stringOperatorRepresentation) {
            this.stringOperatorRepresentation = stringOperatorRepresentation;
        }

        private String getStringOperatorRepresentation() {
            return this.stringOperatorRepresentation;
        }
    }
}