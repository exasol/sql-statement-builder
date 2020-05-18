package com.exasol.sql.expression.function;

import java.util.List;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;
import com.exasol.util.AbstractTreeNode;

/**
 * This is a base class for functions.
 */
public abstract class AbstractFunction extends AbstractTreeNode implements Function {
    protected String functionName;
    protected List<ValueExpression> valueExpressions;

    protected AbstractFunction(final String functionName, final List<ValueExpression> valueExpressions) {
        this.functionName = functionName;
        this.valueExpressions = valueExpressions;
        for (final ValueExpression valueExpression : this.valueExpressions) {
            addChild(valueExpression);
            valueExpression.setParent(this);
        }
    }

    @Override
    public String getFunctionName() {
        return this.functionName;
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
        for (final ValueExpression valueExpression : this.valueExpressions) {
            valueExpression.accept(visitor);
        }
        visitor.leave(this);
    }
}