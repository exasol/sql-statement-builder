package com.exasol.sql.dql;

import com.exasol.sql.FragmentVisitor;
import com.exasol.sql.SqlStatement;
import com.exasol.sql.expression.BooleanExpression;

/**
 * Boolean value expression
 */
public class BooleanValueExpression extends ValueExpression {
    private final BooleanExpression expression;

    /**
     * Create a new instance of a {@link BooleanValueExpression}
     *
     * @param root SQL statement this expression belongs to
     * @param expression nested boolean expression
     */
    public BooleanValueExpression(final SqlStatement root, final BooleanExpression expression) {
        super(root);
        this.expression = expression;
    }

    /**
     * Get the boolean expression nested in this value expression
     *
     * @return nested boolean expression
     */
    public BooleanExpression getExpression() {
        return this.expression;
    }

    @Override
    public void accept(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}