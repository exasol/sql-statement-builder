package com.exasol.sql.expression.comparison;

import com.exasol.sql.expression.AbstractBooleanExpression;
import com.exasol.sql.expression.BooleanExpressionVisitor;
import com.exasol.sql.expression.ValueExpression;

/**
 * Abstract basis for comparisons.
 */
public abstract class AbstractComparison extends AbstractBooleanExpression implements Comparison {
    /** comparison operator */
    protected final ComparisonOperator operator;
    /** left part of the comparison */
    protected final ValueExpression leftOperand;
    /** right part of the comparison */
    protected final ValueExpression rightOperand;

    /**
     * Create a new instance of {@link AbstractComparison}.
     * 
     * @param comparisonOperator comparison operator
     * @param leftOperand        left-hand side operator of the comparison
     * @param rightOperand       right-hand side operator of the comparison
     */
    protected AbstractComparison(final ComparisonOperator comparisonOperator, final ValueExpression leftOperand,
            final ValueExpression rightOperand) {
        this.operator = comparisonOperator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * Get the left-hand side operator of the comparison
     *
     * @return left operator
     */
    @Override
    public ValueExpression getLeftOperand() {
        return this.leftOperand;
    }

    /**
     * Get the right-hand side operator of the comparison
     *
     * @return right operator
     */
    @Override
    public ValueExpression getRightOperand() {
        return this.rightOperand;
    }

    /**
     * Get the comparison operator
     *
     * @return comparison operator
     */
    @Override
    public ComparisonOperator getOperator() {
        return this.operator;
    }

    @Override
    public void accept(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
