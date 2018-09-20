package com.exasol.util.visitor;

public class DummyParentVisitor extends AbstractHierarchicalVisitor {
    private boolean visitedHost;

    public void visit(final DummyParentVisitable host) {
        this.visitedHost = true;
    }

    public void visit(final Visitable host) {
        getRegisteredVisitorForType(host).visit(host);
    }

    public boolean visitedHost() {
        return this.visitedHost;
    }
}