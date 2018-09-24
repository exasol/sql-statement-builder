package com.exasol.sql.dql;

import com.exasol.sql.Fragment;
import com.exasol.sql.FragmentVisitor;
import com.exasol.sql.expression.BooleanExpression;

/**
 * Boolean value expression
 */
public class BooleanValueExpression extends ValueExpression {
    private final BooleanExpression expression;

    /**
     * Create a new instance of a {@link BooleanValueExpression}
     *
     * @param parent     parent fragment
     * @param expression nested boolean expression
     */
    public BooleanValueExpression(final Fragment parent, final BooleanExpression expression) {
        super(parent);
        this.expression = expression;

    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Get the boolean expression nested in this value expression
     * 
     * @return nested boolean expression
     */
    public BooleanExpression getExpression() {
        return this.expression;
    }
}
