package com.exasol.sql.expression;

import com.exasol.util.AbstractBottomUpTreeNode;

/**
 * Abstract base class for all types of value expressions
 */
public abstract class AbstractValueExpression extends AbstractBottomUpTreeNode implements ValueExpression {
    /**
     * Create a new instance of a {@link AbstractValueExpression}
     */
    public AbstractValueExpression() {
        super();
    }
}