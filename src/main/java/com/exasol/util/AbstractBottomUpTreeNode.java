package com.exasol.util;

import java.util.*;

/**
 * This is an abstract base class for nodes in a tree structure.
 */
public abstract class AbstractBottomUpTreeNode implements TreeNode {
    private TreeNode parent = null;
    private final List<TreeNode> children;

    /**
     * Create a new instance of a {@link AbstractBottomUpTreeNode} that serves as
     * leaf node for a tree.
     */
    public AbstractBottomUpTreeNode() {
        this.children = Collections.emptyList();
    }

    /**
     * Create a new instance of a {@link AbstractBottomUpTreeNode} that has one
     * child.
     */
    public AbstractBottomUpTreeNode(final TreeNode child) {
        this.children = new ArrayList<TreeNode>();
        this.children.add(child);
        assignThisAsParentTo(child);
    }

    /**
     * Create a new instance of a {@link AbstractBottomUpTreeNode}.
     *
     * @param children child nodes to be linked to this node.
     */
    public AbstractBottomUpTreeNode(final List<TreeNode> children) {
        this.children = children;
        for (final TreeNode child : children) {
            assignThisAsParentTo(child);
        }
    }

    /**
     * Create a new instance of a {@link AbstractBottomUpTreeNode}.
     *
     * @param children child nodes to be linked to this node.
     */
    public AbstractBottomUpTreeNode(final TreeNode... children) {
        this(Arrays.asList(children));
    }

    private void assignThisAsParentTo(final TreeNode child) {
        assertChildType(child);
        final TreeNode existingParent = child.getParent();
        if (existingParent == null) {
            ((AbstractBottomUpTreeNode) child).parent = this;
        } else {
            assertChildCanAcceptThisAsParent(child, existingParent);
        }
    }

    private void assertChildType(final TreeNode child) {
        if (!(child instanceof AbstractBottomUpTreeNode)) {
            throw new IllegalArgumentException("A bottom up tree can only be constructed from nodes of type \""
                    + AbstractBottomUpTreeNode.class.getName() + "\" but got an object of type \""
                    + child.getClass().getName() + "\"");
        }
    }

    private void assertChildCanAcceptThisAsParent(final TreeNode child, final TreeNode existingParent) {
        if (existingParent != this) {
            throw new IllegalStateException(
                    "Tried to link node \"" + child.toString() + "\" in bottom-up tree to parent \"" + this.toString()
                            + "\" which already has a parent \"" + existingParent + "\"");
        }
    }

    @Override
    public TreeNode getRoot() {
        if (getParent() == null) {
            return this;
        } else {
            return getParent().getRoot();
        }
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public void addChild(final TreeNode child) {
        throw new UnsupportedOperationException("Node \"" + child.toString()
                + "\" can only be added as child node in parent constructor in a bottom-up tree.");
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