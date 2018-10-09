package com.exasol.sql.expression;

import com.exasol.util.TreeNode;

public interface ValueExpression extends TreeNode {
    void accept(ValueExpressionVisitor visitor);
}
