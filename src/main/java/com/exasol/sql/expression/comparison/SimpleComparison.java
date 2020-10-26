package com.exasol.sql.expression.comparison;

import com.exasol.sql.expression.ValueExpression;

/**
 * This class implements all simple comparison operators. Simple means, that the operators do not have extra
 * configuration.
 */
//[impl->dsn~comparison-operations~1]
public class SimpleComparison extends AbstractComparison {

    /**
     * Create a new instance of SimpleComparison.
     *
     * @param comparisonOperator comparison operator
     * @param leftOperand        left-hand side operator of the comparison
     * @param rightOperand       right-hand side operator of the comparison
     */
    // [impl->dsn~boolean-operation.comparison.constructing-from-enum~1]
    public SimpleComparison(final SimpleComparisonOperator comparisonOperator, final ValueExpression leftOperand,
            final ValueExpression rightOperand) {
        super(comparisonOperator, leftOperand, rightOperand);
    }

    @Override
    public void accept(final ComparisonVisitor visitor) {
        visitor.visit(this);
    }
}