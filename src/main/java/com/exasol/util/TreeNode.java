package com.exasol.util;

import java.util.List;

/**
 * This class represents a node in a tree structure.
 */
public interface TreeNode {
    /**
     * Get the root of the tree
     *
     * @return root node
     */
    public TreeNode getRoot();

    /**
     * Get the parent of this node
     *
     * @return parent node
     */
    public TreeNode getParent();

    /**
     * Add a child node below this node. Children are registered in the order in
     * which they are added.
     *
     * @param child child node
     */
    public void addChild(TreeNode child);

    /**
     * Get all child nodes of this node
     *
     * @param child child nodes
     */
    public List<TreeNode> getChildren();

    /**
     * Get child node by position in the list of siblings. The position depends on
     * the order in which the children were added.
     *
     * @param index position in the list of siblings
     * @return child node at position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
     *                                   index >= size())
     */
    public TreeNode getChild(int index) throws IndexOutOfBoundsException;

    /**
     * Check whether this node is the root of the tree.
     * 
     * @return <code>true</code> if this node is the root
     */
    public boolean isRoot();

    /**
     * Check whether this node is a child node
     *
     * @return <code>true</code> if the node is a child of another node
     */
    public boolean isChild();

    /**
     * Check whether a child is the first in the list of siblings
     *
     * @return <code>true</code> if the child is the first in the list of siblings
     */
    public boolean isFirstSibling();
}
