package com.exasol.sql.expression.function.exasol;

import java.util.Arrays;
import java.util.List;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.function.*;

/**
 * This class represents an analytic function in the Exasol database that supports keywords {@code DISTINCT} and
 * {@code ALL} and the over clause.
 */
public class AnalyticFunction extends AbstractFunction {

    public enum Keyword {
        DISTINCT, ALL
    }

    private final Keyword keyword;
    private OverClause overClause;

    private AnalyticFunction(final FunctionName functionName, final Keyword keyword,
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
    public static AnalyticFunction of(final FunctionName functionName, final ValueExpression... valueExpressions) {
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
    public static AnalyticFunction of(final FunctionName functionName, final Keyword keyword,
            final ValueExpression... valueExpressions) {
        return new AnalyticFunction(functionName, keyword, Arrays.asList(valueExpressions));
    }

    /**
     * Get the keyword for the function call, may be {@code null}.
     *
     * @return keyword for the function call
     */
    public Keyword getKeyword() {
        return this.keyword;
    }

    /**
     * Add the given over clause to the function call.
     *
     * @param overClause over clause to add
     * @return this {@link AnalyticFunction} for fluent programming
     */
    public AnalyticFunction over(final OverClause overClause) {
        this.overClause = overClause;
        return this;
    }

    public OverClause over(final String windowName) {
        if (this.overClause == null) {
            this.overClause = OverClause.of(windowName);
        }
        return this.overClause;
    }

    /**
     * Get the "over clause" appended to the function call, may be {@code null}.
     *
     * @return "over clause" appended to the function call
     */
    public OverClause getOverClause() {
        return this.overClause;
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