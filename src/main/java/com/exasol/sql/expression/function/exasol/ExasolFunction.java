package com.exasol.sql.expression.function.exasol;

import java.util.*;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;
import com.exasol.sql.expression.function.AbstractFunction;
import com.exasol.sql.expression.function.FunctionName;

/**
 * This class represents a function in the Exasol database.
 */
public class ExasolFunction extends AbstractFunction {
    private static final List<String> functionsWithoutParenthesis = Arrays.asList("SYSDATE", "CURRENT_SCHEMA",
            "CURRENT_SESSION", "CURRENT_STATEMENT", "CURRENT_USER", "ROWNUM", "ROWID", "SCOPE_USER", "USER");

    private ExasolFunction(final FunctionName functionName, final List<ValueExpression> valueExpressions) {
        super(functionName.toString(), valueExpressions);
    }

    /**
     * Create a new {@link ExasolFunction} instance.
     * 
     * @param functionName name of the function
     * @return new {@link ExasolFunction}
     */
    public static ExasolFunction of(final FunctionName functionName) {
        return new ExasolFunction(functionName, Collections.emptyList());
    }

    /**
     * Create a new {@link ExasolFunction} instance.
     *
     * @param functionName name of the function
     * @param valueExpressions zero or more value expressions
     * @return new {@link ExasolFunction}
     */
    public static ExasolFunction of(final FunctionName functionName, final ValueExpression... valueExpressions) {
        return new ExasolFunction(functionName, Arrays.asList(valueExpressions));
    }

    @Override
    public boolean hasParenthesis() {
        return !functionsWithoutParenthesis.contains(this.functionName);
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