package com.exasol.sql;

import com.exasol.util.TreeNode;

public interface Fragment extends TreeNode {
    public void accept(FragmentVisitor visitor);
}
