package com.exasol.sql.expression;

//[impl->dsn~comparison-operations~1]
public class Comparison extends AbstractBooleanExpression {
    private final ComparisonOperator operator;
    private final ValueExpression leftOperand;
    private final ValueExpression rightOperand;

    // [impl->dsn~boolean-operation.comparison.constructing-from-enum~1]
    public Comparison(final ComparisonOperator comparisonOperator, final ValueExpression leftOperand,
            final ValueExpression rightOperand) {
        this.operator = comparisonOperator;
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
    public ValueExpression getLeftOperand() {
        return this.leftOperand;
    }

    /**
     * Get the right-hand side operator of the comparison
     *
     * @return right operator
     */
    public ValueExpression getRightOperand() {
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