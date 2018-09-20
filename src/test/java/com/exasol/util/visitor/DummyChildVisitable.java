package com.exasol.util.visitor;

public class DummyChildVisitable implements Visitable {
    private boolean visitedHost;

    public void visit(final DummyChildVisitable host) {
        this.visitedHost = true;
    }

    public boolean visitedHost() {
        return this.visitedHost;
    }
}
