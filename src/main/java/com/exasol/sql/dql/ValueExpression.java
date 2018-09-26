package com.exasol.sql.dql;

import com.exasol.sql.AbstractFragment;

/**
 * Abstract base class for all types of value expressions
 */
public abstract class ValueExpression extends AbstractFragment {
    /**
     * Create a new instance of a {@link ValueExpression}
     */
    public ValueExpression() {
        super();
    }
}