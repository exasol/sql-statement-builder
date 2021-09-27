package com.exasol.sql.expression.function.exasol;

import java.util.Arrays;
import java.util.List;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.function.AbstractFunction;
import com.exasol.sql.expression.function.FunctionVisitor;

/**
 * This class represents an analytic function in the Exasol database that supports keywords {@code DISTINCT} and
 * {@code ALL} and the over clause.
 */
public class AnalyticFunction extends AbstractFunction {

    public enum Keyword {
        DISTINCT, ALL
    }

    private final Keyword keyword;

    private AnalyticFunction(final ExasolAnalyticFunction functionName, final Keyword keyword,
            final List<ValueExpression> valueExpressions) {
        super(functionName.toString(), valueExpressions);
        this.keyword = keyword;
    }

    /**
     * Create a new {@link AnalyticFunction} instance.
     *
     * @param functionName     name of the function
     * @param valueExpressions zero or more value expressions
     * @return new {@link AnalyticFunction}
     */
    public static AnalyticFunction of(final ExasolAnalyticFunction functionName,
            final ValueExpression... valueExpressions) {
        return of(functionName, null, valueExpressions);
    }

    /**
     * Create a new {@link AnalyticFunction} instance with a keyword.
     *
     * @param functionName     name of the function
     * @param keyword          keyword used in the function
     * @param valueExpressions zero or more value expressions
     * @return new {@link AnalyticFunction}
     */
    public static AnalyticFunction of(final ExasolAnalyticFunction functionName, final Keyword keyword,
            final ValueExpression... valueExpressions) {
        return new AnalyticFunction(functionName, keyword, Arrays.asList(valueExpressions));
    }

    public Keyword getKeyword() {
        return this.keyword;
    }

    @Override
    public boolean hasParenthesis() {
        return true;
    }

    @Override
    public void accept(final FunctionVisitor visitor) {
        visitor.visit(this);
    }
}