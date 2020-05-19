package com.exasol.sql.expression.function;

import java.util.List;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.util.AbstractTreeNode;

/**
 * This is a base class for functions.
 */
public abstract class AbstractFunction extends AbstractTreeNode implements Function {
    protected String functionName;
    protected List<ValueExpression> valueExpressions;

    /**
     * Create a new instance using {@link AbstractFunction}.
     * 
     * @param functionName name of a function
     * @param valueExpressions zero or more value expressions
     */
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
}