package com.exasol.sql.expression.function;

import java.util.List;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;

/**
 * This is a base class for functions.
 */
public abstract class AbstractFunction implements Function {
    protected String functionName;
    protected List<ValueExpression> valueExpressions;

    /**
     * Create a new instance using {@link AbstractFunction}.
     * 
     * @param functionName     name of a function
     * @param valueExpressions zero or more value expressions
     */
    protected AbstractFunction(final String functionName, final List<ValueExpression> valueExpressions) {
        this.functionName = functionName;
        this.valueExpressions = valueExpressions;
    }

    @Override
    public String getFunctionName() {
        return this.functionName;
    }

    /**
     * Get the value expressions (parameters of the function)
     * 
     * @return list of value expressions
     */
    public List<ValueExpression> getValueExpressions() {
        return this.valueExpressions;
    }

    @Override
    public final void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}