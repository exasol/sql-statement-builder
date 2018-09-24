package com.exasol.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAbstractTreeNode {
    private TreeNode node;

    @BeforeEach
    void beforeEach() {
        this.node = new DummyTreeNode();
    }

    @Test
    void testIsRootOnRootNode() {
        assertTrue(this.node.isRoot());
    }

    @Test
    void testIsChildOnRootNode() {
        assertFalse(this.node.isChild());
    }

    @Test
    void testIsFirstSiblingOnRootNode() {
        assertFalse(this.node.isFirstSibling());
    }

    @Test
    void testIsRootOnChild() {
        final TreeNode child = new DummyTreeNode(this.node);
        this.node.addChild(child);
        assertFalse(child.isRoot());
    }

    @Test
    void testIsChildOnChild() {
        final TreeNode child = new DummyTreeNode(this.node);
        this.node.addChild(child);
        assertTrue(child.isChild());
    }

    @Test
    void testIsFirstSiblingOnChild() {
        final TreeNode child = new DummyTreeNode(this.node);
        this.node.addChild(child);
        assertTrue(child.isFirstSibling());
    }

    @Test
    void testIsFirstSiblingOnFirstChild() {
        final TreeNode child = new DummyTreeNode(this.node);
        final TreeNode otherChild = new DummyTreeNode(this.node);
        this.node.addChild(child);
        this.node.addChild(otherChild);
        assertTrue(child.isFirstSibling());
    }

    @Test
    void testIsFirstSiblingOnSecondChild() {
        final TreeNode child = new DummyTreeNode(this.node);
        final TreeNode otherChild = new DummyTreeNode(this.node);
        this.node.addChild(child);
        this.node.addChild(otherChild);
        assertFalse(otherChild.isFirstSibling());
    }

    @Test
    void testGetChildren() {
        final TreeNode child = new DummyTreeNode(this.node);
        final TreeNode otherChild = new DummyTreeNode(this.node);
        this.node.addChild(child);
        this.node.addChild(otherChild);
        assertThat(this.node.getChildren(), contains(child, otherChild));
    }

    @Test
    void testGetChild() {
        final TreeNode child = new DummyTreeNode(this.node);
        final TreeNode otherChild = new DummyTreeNode(this.node);
        this.node.addChild(child);
        this.node.addChild(otherChild);
        assertThat(this.node.getChild(1), equalTo(otherChild));
    }

    @Test
    void testGetParent() {
        final TreeNode child = new DummyTreeNode(this.node);
        this.node.addChild(child);
        assertThat(child.getParent(), equalTo(this.node));
    }
}