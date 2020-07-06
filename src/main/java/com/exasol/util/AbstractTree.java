package com.exasol.util;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract base for {@link TreeNode} implementations.
 */
public abstract class AbstractTree implements TreeNode {
    protected TreeNode parent = null;
    protected List<TreeNode> children = new ArrayList<>();

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public List<TreeNode> getChildren() {
        return this.children;
    }

    @Override
    public TreeNode getChild(final int index) {
        return this.children.get(index);
    }

    @Override
    public boolean isRoot() {
        return (this == getRoot());
    }

    @Override
    public boolean isChild() {
        return (this.parent != null);
    }

    @Override
    public boolean isFirstSibling() {
        return (this.parent != null) && (this.getParent().getChild(0) == this);
    }

    @Override
    public boolean isSibling(final TreeNode node) {
        return (this.parent != null) && (this.getParent().getChildren().contains(node));
    }
}
