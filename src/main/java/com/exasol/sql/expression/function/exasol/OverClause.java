package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.dql.select.OrderByClause;

public class OverClause {

    private String windowName;
    private PartitionClause partitionClause;
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

    public String getWindowName() {
        return this.windowName;
    }

    public PartitionClause getPartitionClause() {
        return this.partitionClause;
    }

    public OrderByClause getOrderByClause() {
        return this.orderByClause;
    }

    public WindowFrameClause getWindowFrameClause() {
        return this.windowFrameClause;
    }

    public static class PartitionClause {

    }

    public static class WindowFrameClause {

    }
}