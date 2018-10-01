package com.exasol.sql.expression;

//[impl->dsn~comparison-operations~1]
public class Comparison extends AbstractBooleanExpression {
    private final ComparisonOperator operator;
    private final Literal leftOperand;
    private final Literal rightOperand;

    public Comparison(final ComparisonOperator equal, final Literal leftOperand, final Literal rightOperand) {
        this.operator = equal;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public void acceptConcrete(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void dismissConcrete(final BooleanExpressionVisitor visitor) {
        visitor.leave(this);
    }

    /**
     * Get the left-hand side operator of the comparison
     * 
     * @return left operator
     */
    public AbstractBooleanExpression getLeftOperand() {
        return this.leftOperand;
    }

    /**
     * Get the right-hand side operator of the comparison
     * 
     * @return right operator
     */
    public AbstractBooleanExpression getRightOperand() {
        return this.rightOperand;
    }

    /**
     * Get the comparison operator
     * 
     * @return comparison operator
     */
    public ComparisonOperator getOperator() {
        return this.operator;
    }
}