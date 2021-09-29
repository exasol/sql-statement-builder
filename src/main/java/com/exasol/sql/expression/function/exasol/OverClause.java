package com.exasol.sql.expression.function.exasol;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.UnaryOperator;

import com.exasol.sql.dql.select.OrderByClause;
import com.exasol.sql.expression.ValueExpression;

/**
 * This represents an {@code OVER} clause of an analytic function in Exasol. See the
 * <a href="https://docs.exasol.com/sql_references/functions/analyticfunctions.htm">documentation</a> for details.
 */
public class OverClause {

    private String windowName;
    private OrderByClause orderByClause;
    private WindowFrameClause windowFrameClause;
    private List<ValueExpression> partitionByColumns;

    /**
     * Create a new {@link OverClause} with the given window name.
     *
     * @param windowName window name of the new {@link OverClause}
     * @return a new {@link OverClause}
     */
    public static OverClause of(final String windowName) {
        return new OverClause().windowName(windowName);
    }

    /**
     * Set the window name for this {@link OverClause}.
     *
     * @param windowName name of the window.
     * @return this {@link OverClause} for fluent programming
     */
    public OverClause windowName(final String windowName) {
        this.windowName = windowName;
        return this;
    }

    /**
     * Set the {@link OrderByClause} for this {@link OverClause}.
     *
     * @param orderByClause {@link OrderByClause} for this {@link OverClause}
     * @return this {@link OverClause} for fluent programming
     */
    public OverClause orderBy(final OrderByClause orderByClause) {
        this.orderByClause = orderByClause;
        return this;
    }

    /**
     * Set the columns for the {@code PARTITION BY} clause of this {@link OverClause}.
     *
     * @param columns columns for the {@code PARTITION BY} clause.
     * @return this {@link OverClause} for fluent programming
     */
    public OverClause partitionBy(final ValueExpression... columns) {
        this.partitionByColumns = asList(columns);
        return this;
    }

    /**
     * Set and configure the {@link WindowFrameClause} for this {@link OverClause}. You configure the clause in the
     * given lambda.
     *
     * @param configurator lambda configuring the {@link WindowFrameClause}.
     * @return this {@link OverClause} for fluent programming
     */
    public OverClause windowFrame(final UnaryOperator<WindowFrameClause> configurator) {
        this.windowFrameClause = configurator.apply(new WindowFrameClause());
        return this;
    }

    /**
     * Get the window name of this {@link OverClause}.
     *
     * @return window name
     */
    public String getWindowName() {
        return this.windowName;
    }

    /**
     * Get the columns of the partition by clause of this {@link OverClause}.
     *
     * @return partition by columns
     */
    public List<ValueExpression> getPartitionByColumns() {
        return this.partitionByColumns;
    }

    /**
     * Get the order by clause of this {@link OverClause}.
     *
     * @return the order by clause
     */
    public OrderByClause getOrderByClause() {
        return this.orderByClause;
    }

    /**
     * Get the window frame clause of this {@link OverClause}.
     *
     * @return the window frame clause
     */
    public WindowFrameClause getWindowFrameClause() {
        return this.windowFrameClause;
    }
}