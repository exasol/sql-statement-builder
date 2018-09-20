package com.exasol.util.visitor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestHierarchicalVisitor {
    private DummyParentVisitor parent;
    private DummyChildVisitor child;

    @BeforeEach
    void beforeEach() {
        this.parent = new DummyParentVisitor();
        this.child = new DummyChildVisitor();
        this.parent.register(this.child, DummyChildVisitable.class);
    }

    @Test
    void testRegisterSubVisitor() {
        assertThat(this.parent.getRegisted(), containsInAnyOrder(this.child));
    }

    @Test
    void testVisitInParent() {
        final DummyParentVisitable host = new DummyParentVisitable();
        this.parent.visit(host);
        assertThat(this.parent.visitedHost(), equalTo(true));
    }

    @Test
    void testVisitInParentThenDelegate() {
        final DummyChildVisitable host = new DummyChildVisitable();
        this.parent.visit(host);
        assertThat(this.parent.visitedHost(), equalTo(false));
        assertThat(this.child.visitedHost(), equalTo(true));
    }
}