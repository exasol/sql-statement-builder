package com.exasol.sql.expression.function.exasol;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.function.AbstractFunction;
import com.exasol.sql.expression.function.FunctionVisitor;

/**
 * This class represents an analytic function in the Exasol database that supports keywords {@code DISTINCT} and
 * {@code ALL} and the over clause.
 */
public class AnalyticFunction extends AbstractFunction {
    /** Analytic function keywords */
    public enum Keyword {
        /** Function over distinct values */
        DISTINCT,
        /** Function over all values (including duplicates) */
        ALL
    }

    private Keyword keyword;
    private OverClause overClause;

    private AnalyticFunction(final ExasolAnalyticAggregateFunctions functionName,
            final List<ValueExpression> valueExpressions) {
        super(functionName.toString(), valueExpressions);
    }

    /**
     * Create a new {@link AnalyticFunction} instance.
     *
     * @param functionName     name of the function
     * @param valueExpressions zero or more value expressions
     * @return new {@link AnalyticFunction}
     */
    public static AnalyticFunction of(final ExasolAnalyticAggregateFunctions functionName,
            final ValueExpression... valueExpressions) {
        return new AnalyticFunction(functionName, Arrays.asList(valueExpressions));
    }

    /**
     * Add keyword {@code DISTINCT} to the function call
     *
     * @return this {@link AnalyticFunction} for fluent programming
     */
    public AnalyticFunction keywordDistinct() {
        return this.keyword(Keyword.DISTINCT);
    }

    /**
     * Add keyword {@code ALL} to the function call
     *
     * @return this {@link AnalyticFunction} for fluent programming
     */
    public AnalyticFunction keywordAll() {
        return this.keyword(Keyword.ALL);
    }

    private AnalyticFunction keyword(final Keyword keyword) {
        this.keyword = keyword;
        return this;
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

    /**
     * Add an {@code OVER} clause to the function call. You configure the clause in the given lambda.
     *
     * @param configurator lambda configuring the {@link OverClause}.
     * @return this {@link AnalyticFunction} for fluent programming
     */
    public AnalyticFunction over(final UnaryOperator<OverClause> configurator) {
        return this.over(configurator.apply(new OverClause()));
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