package com.exasol.sql.dql;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.FragmentVisitor;
import com.exasol.sql.expression.BooleanExpression;

public class WhereClause extends AbstractFragment {
    private final BooleanExpression expression;

    /**
     * Create a new instance of a WhereClause
     *
     * @param expression boolean expression that defines the filter criteria
     */
    public WhereClause(final BooleanExpression expression) {
        this.expression = expression;
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Get the boolean expression defining the filter criteria
     *
     * @return boolean expression
     */
    public BooleanExpression getExpression() {
        return this.expression;
    }
}