package com.exasol.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAbstractBottomUpTreeNode {
    private TreeNode node;

    @BeforeEach
    void beforeEach() {
        this.node = new DummyBottomUpTreeNode();
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
        new DummyBottomUpTreeNode(this.node);
        assertFalse(this.node.isRoot());
    }

    @Test
    void testIsChildOnChild() {
        new DummyBottomUpTreeNode(this.node);
        assertTrue(this.node.isChild());
    }

    @Test
    void testIsFirstSiblingOnChild() {
        new DummyBottomUpTreeNode(this.node);
        assertTrue(this.node.isFirstSibling());
    }

    @Test
    void testIsFirstSiblingOnFirstChild() {
        new DummyBottomUpTreeNode(this.node, new DummyBottomUpTreeNode());
        assertTrue(this.node.isFirstSibling());
    }

    @Test
    void testIsFirstSiblingOnSecondChild() {
        new DummyBottomUpTreeNode(new DummyBottomUpTreeNode(), this.node);
        assertFalse(this.node.isFirstSibling());
    }

    @Test
    void testAddingChildAfterConstructurThrowsExpection() {
        assertThrows(UnsupportedOperationException.class, () -> this.node.addChild(new DummyBottomUpTreeNode()));
    }

    @Test
    void testGetChildren() {
        final TreeNode otherNode = new DummyBottomUpTreeNode();
        final TreeNode parent = new DummyBottomUpTreeNode(this.node, otherNode);
        assertThat(parent.getChildren(), contains(this.node, otherNode));
    }

    @Test
    void testAddingChildToTwoParentsThrowsException() {
        new DummyBottomUpTreeNode(this.node);
        assertThrows(IllegalStateException.class, () -> new DummyBottomUpTreeNode(this.node));
    }

    @Test
    void testAddingWrongChildTypeThrowsException() {
        final TreeNode wrongChild = new WrongNodeType();
        assertThrows(IllegalArgumentException.class, () -> new DummyBottomUpTreeNode(wrongChild));
    }

    private static class WrongNodeType implements TreeNode {
        @Override
        public TreeNode getRoot() {
            return null;
        }

        @Override
        public TreeNode getParent() {
            return null;
        }

        @Override
        public void addChild(final TreeNode child) {
        }

        @Override
        public List<TreeNode> getChildren() {
            return null;
        }

        @Override
        public TreeNode getChild(final int index) throws IndexOutOfBoundsException {
            return null;
        }

        @Override
        public boolean isRoot() {
            return false;
        }

        @Override
        public boolean isChild() {
            return false;
        }

        @Override
        public boolean isFirstSibling() {
            return false;
        }

        @Override
        public boolean isSibling(final TreeNode node) {
            return false;
        }

        @Override
        public void setParent(final TreeNode parent) {
        }
    }
}