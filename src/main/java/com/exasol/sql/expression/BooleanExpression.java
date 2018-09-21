package com.exasol.sql.expression;

import com.exasol.util.TreeNode;

/**
 * Common interface for all types of boolean expressions
 */
public interface BooleanExpression extends TreeNode {
    /**
     * Accept a visitor
     *
     * @param visitor visitor to accept
     */
    public void accept(final BooleanExpressionVisitor visitor);
}