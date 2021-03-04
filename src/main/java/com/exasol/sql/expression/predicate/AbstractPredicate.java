package com.exasol.sql.expression.predicate;

import com.exasol.sql.expression.BooleanExpressionVisitor;
import com.exasol.sql.expression.ValueExpressionVisitor;

/**
 * An abstract basis for predicate classes.
 */
public abstract class AbstractPredicate implements Predicate {
    private final PredicateOperator operator;

    /**
     * Creates a new instance of {@link AbstractPredicate}.
     *
     * @param operator a predicate operator
     */
    protected AbstractPredicate(final PredicateOperator operator) {
        this.operator = operator;
    }

    @Override
    public PredicateOperator getOperator() {
        return this.operator;
    }

    @Override
    public void accept(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

}
