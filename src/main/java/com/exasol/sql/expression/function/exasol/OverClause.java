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
    private PartitionByClause partitionByClause;
    private OrderByClause orderByClause;
    private WindowFrameClause windowFrameClause;

    public static OverClause of(final String windowName) {
        return new OverClause().windowName(windowName);
    }

    public OverClause windowName(final String windowName) {
        this.windowName = windowName;
        return this;
    }

    public OverClause orderBy(final OrderByClause orderByClause) {
        this.orderByClause = orderByClause;
        return this;
    }

    public OverClause partitionBy(final ValueExpression... columns) {
        this.partitionByClause = new PartitionByClause(asList(columns));
        return this;
    }

    public OverClause windowFrame(final UnaryOperator<WindowFrameClause> configurator) {
        final WindowFrameClause builder = new WindowFrameClause();
        this.windowFrameClause = configurator.apply(builder);
        return this;
    }

    public String getWindowName() {
        return this.windowName;
    }

    public PartitionByClause getPartitionClause() {
        return this.partitionByClause;
    }

    public OrderByClause getOrderByClause() {
        return this.orderByClause;
    }

    public WindowFrameClause getWindowFrameClause() {
        return this.windowFrameClause;
    }

    public static class PartitionByClause {

        private final List<ValueExpression> columns;

        private PartitionByClause(final List<ValueExpression> columns) {
            this.columns = columns;
        }

        public List<ValueExpression> getColumns() {
            return this.columns;
        }
    }
}