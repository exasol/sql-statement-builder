package com.exasol.sql.dql;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.SqlStatement;
import com.exasol.sql.expression.BooleanExpression;

/**
 * This class represents the where clause of an SQL statement. It contains the filter criteria in form of a
 * {@link BooleanExpression}.
 */
public class WhereClause extends AbstractFragment implements SelectFragment {
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

    @Override
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
    }
}