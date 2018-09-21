package com.exasol.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract base class for nodes in a tree structure.
 */
public abstract class AbstractTreeNode implements TreeNode {
    private final TreeNode root;
    private final TreeNode parent;
    private final List<TreeNode> children = new ArrayList<>();

    /**
     * Create a new instance of a {@link AbstractTreeNode} that serves as root for a
     * tree.
     */
    public AbstractTreeNode() {
        this(null);
    }

    /**
     * Create a new instance of a {@link AbstractTreeNode}.
     *
     * @param parent the parent to which this node will be linked as a child or
     *               <code>null</code> if the current node is the root of the tree.
     */
    public AbstractTreeNode(final TreeNode parent) {
        this.parent = parent;
        if (this.parent == null) {
            this.root = this;
        } else {
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