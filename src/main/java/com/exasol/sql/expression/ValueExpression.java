package com.exasol.sql.expression;

import com.exasol.util.TreeNode;

/**
 * Common interface for all types of value expressions
 */
public interface ValueExpression extends TreeNode {
    /**
     * Accept a visitor
     *
     * @param visitor visitor to accept
     */
    public void accept(ValueExpressionVisitor visitor);
}
