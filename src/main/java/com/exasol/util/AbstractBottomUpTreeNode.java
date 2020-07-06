package com.exasol.util;

import java.util.Arrays;
import java.util.List;

/**
 * This is an abstract base class for nodes in a tree structure.
 */
public abstract class AbstractBottomUpTreeNode extends AbstractTree {
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
        assertChildDoesNotAlreadyHaveAParent(child);
        ((AbstractBottomUpTreeNode) child).parent = this;
    }

    private void assertChildType(final TreeNode child) {
        if (!(child instanceof AbstractBottomUpTreeNode)) {
            throw new IllegalArgumentException("A bottom up tree can only be constructed from nodes of type \""
                    + AbstractBottomUpTreeNode.class.getName() + "\" but got an object of type \""
                    + child.getClass().getName() + "\"");
        }
    }

    private void assertChildDoesNotAlreadyHaveAParent(final TreeNode child) {
        if (child.getParent() != null) {
            throw new IllegalStateException(
                    "Tried to link node \"" + child.toString() + "\" in bottom-up tree to parent \"" + this.toString()
                            + "\" which already has a parent \"" + child.getParent() + "\"");
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
    public void addChild(final TreeNode child) {
        throw new UnsupportedOperationException("Node \"" + child.toString()
                + "\" can only be added as child node in parent constructor in a bottom-up tree.");
    }

    @Override
    public void setParent(final TreeNode parent) {
        this.parent = parent;
    }
}