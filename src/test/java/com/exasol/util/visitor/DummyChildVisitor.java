package com.exasol.util.visitor;

public class DummyChildVisitor extends AbstractHierarchicalVisitor {
    private boolean visitedHost;

    public void visit(final DummyChildVisitable host) {
        this.visitedHost = true;
    }

    public boolean visitedHost() {
        return this.visitedHost;
    }

    @Override
    public void visit(final Visitable host) {
        throw new UnsupportedOperationException();
    }
}
