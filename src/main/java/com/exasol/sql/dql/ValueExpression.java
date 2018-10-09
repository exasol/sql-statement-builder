package com.exasol.sql.dql;

import com.exasol.sql.*;

/**
 * Abstract base class for all types of value expressions
 */
public abstract class ValueExpression extends AbstractFragment implements Fragment {
    /**
     * Create a new instance of a {@link ValueExpression}
     */
    public ValueExpression() {
        super(null);
    }

    /**
     * Create a new instance of a {@link ValueExpression}
     *
     * @param root root SQL statement this expression belongs to
     */
    public ValueExpression(final SqlStatement root) {
        super(root);
    }
}