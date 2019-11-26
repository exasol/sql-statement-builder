package com.exasol.sql.dql.select;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.SqlStatement;
import com.exasol.sql.dml.merge.MergeFragment;
import com.exasol.sql.dml.merge.MergeVisitor;
import com.exasol.sql.expression.BooleanExpression;

/**
 * This class represents the where clause of an SQL statement. It contains the filter criteria in form of a
 * {@link BooleanExpression}.
 */
public class WhereClause extends AbstractFragment implements SelectFragment, MergeFragment {
    private final BooleanExpression expression;

    /**
     * Create a new instance of a {@link WhereClause}
     *
     * @param root SQL statement this WHERE clause belongs to
     * @param expression boolean expression servicing as criteria for the WHERE clause
     */
    public WhereClause(final SqlStatement root, final BooleanExpression expression) {
        super(root);
        this.expression = expression;
    }

    /**
     * Get the boolean expression defining the filter criteria
     *
     * @return boolean expression
     */
    public BooleanExpression getExpression() {
        return this.expression;
    }

    /**
     * Accept a visitor for {@code SELECT} statements
     *
     * @param visitor {@code SELECT} visitor
     */
    @Override
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Accept a visitor for {@code MERGE} statements
     *
     * @param visitor {@code MERGE} visitor
     */
    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
    }
}