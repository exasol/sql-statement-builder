package com.exasol.sql.expression.function;

import java.util.List;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;

/**
 * This is a base class for functions.
 */
public abstract class AbstractFunction implements Function {
    protected String functionName;
    protected List<ValueExpression> parameters;

    /**
     * Create a new instance using {@link AbstractFunction}.
     * 
     * @param functionName name of a function
     * @param parameters   zero or more value expressions
     */
    protected AbstractFunction(final String functionName, final List<ValueExpression> parameters) {
        this.functionName = functionName;
        this.parameters = parameters;
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
    public List<ValueExpression> getParameters() {
        return this.parameters;
    }

    @Override
    public final void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}