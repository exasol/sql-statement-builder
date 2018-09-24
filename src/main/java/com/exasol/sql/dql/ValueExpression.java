package com.exasol.sql.dql;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

/**
 * Abstract base class for all types of value expressions
 */
public abstract class ValueExpression extends AbstractFragment {
    /**
     * Create a new instance of a {@link ValueExpression}
     * 
     * @param parent parent fragement
     */
    public ValueExpression(final Fragment parent) {
        super(parent);
    }
}