package com.exasol.sql.dql.select;

import java.util.*;

import com.exasol.sql.*;
import com.exasol.sql.expression.*;

/**
 * This class represents the ORDER BY clause of an SQL statement.
 */
public class OrderByClause extends AbstractFragment implements SelectFragment {
    private final List<ColumnReference> columnReferences;
    private Boolean desc = null;
    private Boolean nullsFirst = null;

    /**
     * Create a new instance of a {@link OrderByClause}
     *
     * @param rootStatement    SQL statement this ORDER BY clause belongs to
     * @param columnReferences column references for the GROUP BY clause
     */
    public OrderByClause(final SqlStatement rootStatement, final ColumnReference... columnReferences) {
        super(rootStatement);
        this.columnReferences = Arrays.asList(columnReferences);
    }

    @Override
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Get list of column references.
     *
     * @return column name
     */
    public List<ColumnReference> getColumnReferences() {
        return columnReferences;
    }

    /**
     * Add DESC option to the {@link OrderByClause}
     *
     * @return instance of{@link OrderByClause} for fluent programming
     */
    public OrderByClause desc() {
        desc = Boolean.TRUE;
        return this;
    }

    /**
     * Add ASC option to the {@link OrderByClause}
     *
     * @return instance of{@link OrderByClause} for fluent programming
     */
    public OrderByClause asc() {
        desc = Boolean.FALSE;
        return this;
    }

    /**
     * Get descending.
     * 
     * @return true if descending
     */
    public Boolean getDesc() {
        return desc;
    }

    /**
     * Add NULLS FIRST option to the {@link OrderByClause}
     *
     * @return instance of{@link OrderByClause} for fluent programming
     */
    public OrderByClause nullsFirst() {
        nullsFirst = Boolean.TRUE;
        return this;
    }

    /**
     * Add NULLS LAST option to the {@link OrderByClause}
     *
     * @return instance of{@link OrderByClause} for fluent programming
     */
    public OrderByClause nullsLast() {
        nullsFirst = Boolean.FALSE;
        return this;
    }

    /**
     * Get nulls first.
     *
     * @return true if nulls first
     */
    public Boolean getNullsFirst() {
        return nullsFirst;
    }
}
