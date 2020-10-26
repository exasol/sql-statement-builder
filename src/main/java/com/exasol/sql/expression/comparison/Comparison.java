package com.exasol.sql.expression.comparison;

import com.exasol.sql.expression.BooleanExpression;
import com.exasol.sql.expression.ValueExpression;

public interface Comparison extends BooleanExpression {

    /**
     * Get the left-hand side operator of the comparison
     *
     * @return left operator
     */
    public ValueExpression getLeftOperand();

    /**
     * Get the right-hand side operator of the comparison
     *
     * @return right operator
     */
    public ValueExpression getRightOperand();

    /**
     * Get the comparison operator
     *
     * @return comparison operator
     */
    public ComparisonOperator getOperator();

    /**
     * Accept {@link ComparisonVisitor}.
     * 
     * @param visitor visitor to accept
     */
    public void accept(ComparisonVisitor visitor);
}
