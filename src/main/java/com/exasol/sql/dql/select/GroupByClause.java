package com.exasol.sql.dql.select;

import java.util.Arrays;
import java.util.List;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.SqlStatement;
import com.exasol.sql.expression.BooleanExpression;
import com.exasol.sql.expression.ColumnReference;

/**
 * This class represents the GROUP BY clause of an SQL statement.
 */
public class GroupByClause extends AbstractFragment implements SelectFragment {
    private final SqlStatement rootStatement;
    private final List<ColumnReference> columnReferences;
    private BooleanExpression booleanExpression;
    private WindowClause windowClause;

    /**
     * Create a new instance of a {@link GroupByClause}.
     *
     * @param rootStatement    SQL statement this {@code GROUP BY} clause belongs to
     * @param columnReferences column references for the {@code GROUP BY} clause
     */
    public GroupByClause(final SqlStatement rootStatement, final ColumnReference... columnReferences) {
        super(rootStatement);
        this.rootStatement = rootStatement;
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
        return this.columnReferences;
    }

    /**
     * Add having statement to the SQL query.
     *
     * @param booleanExpression boolean expression
     * @return instance of{@link Select} for fluent programming
     */
    public Select having(final BooleanExpression booleanExpression) {
        this.booleanExpression = booleanExpression;
        return (Select) this.rootStatement;
    }

    /**
     * Get the "having" boolean expression.
     *
     * @return "having" boolean expression
     */
    public BooleanExpression getHavingBooleanExpression() {
        return this.booleanExpression;
    }

    /**
     * Add a window clause to the having statement. Return the existing window clause if one was added before.
     *
     * @return the {@link WindowClause} for fluent programming
     */
    public WindowClause window() {
        if (this.windowClause == null) {
            this.windowClause = WindowClause.of(this.root);
        }
        return this.windowClause;
    }

    /**
     * Add the given window clause to the having statement.
     *
     * @return this {@link GroupByClause} for fluent programming
     */
    public GroupByClause window(final WindowClause windowClause) {
        this.windowClause = windowClause;
        return this;
    }

    /**
     * Get the window clause of the "having" statement.
     *
     * @return the window clause
     */
    public WindowClause getWindowClause() {
        return this.windowClause;
    }
}
