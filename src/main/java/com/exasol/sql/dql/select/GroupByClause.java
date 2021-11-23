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
}
