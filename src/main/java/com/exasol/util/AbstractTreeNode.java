package com.exasol.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract base class for nodes in a tree structure.
 */
public abstract class AbstractTreeNode implements TreeNode {
    private TreeNode root;
    private TreeNode parent;
    private final List<TreeNode> children = new ArrayList<>();

    /**
     * Create a new instance of a {@link AbstractTreeNode} that serves as root for a tree.
     */
    public AbstractTreeNode() {
        this.root = this;
        this.parent = null;
    }

    /**
     * Link to a parent node
     *
     * @param parent the parent to which this node will be linked as a child
     *
     * @throws IllegalArgumentException if parent is <code>null</code> or parent and child are identical
     */
    public void setParent(final TreeNode parent) {
        if (parent == null) {
            throw new IllegalArgumentException("Parent tree node cannot be NULL.");
        } else if (parent == this) {
            throw new IllegalArgumentException("Parent tree node cannot be the same as child tree node.");
        } else {
            this.parent = parent;
            this.root = this.parent.getRoot();
        }
    }

    @Override
    public TreeNode getRoot() {
        return this.root;
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public void addChild(final TreeNode child) {
        this.children.add(child);
        ((AbstractTreeNode) child).setParent(this);
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
}