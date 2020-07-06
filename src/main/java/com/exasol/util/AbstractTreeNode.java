package com.exasol.util;

/**
 * This is an abstract base class for nodes in a tree structure.
 */
public abstract class AbstractTreeNode extends AbstractTree {
    private TreeNode root;

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
    @Override
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
    public void addChild(final TreeNode child) {
        this.children.add(child);
        child.setParent(this);
    }
}