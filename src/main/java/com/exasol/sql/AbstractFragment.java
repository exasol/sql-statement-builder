package com.exasol.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides an abstract base for SQL statement fragments. It also
 * keeps track of the relationships to other fragments.
 *
 * @param <T> the type of the concrete class implementing the missing parts.
 */
public abstract class AbstractFragment implements Fragment {
    private final Fragment root;
    protected final Fragment parent;
    protected final List<Fragment> children = new ArrayList<>();

    protected AbstractFragment(final Fragment parent) {
        if (parent == null) {
            this.root = this;
        } else {
            this.root = parent.getRoot();
        }
        this.parent = parent;
    }

    @Override
    public Fragment getRoot() {
        return this.root;
    }

    @Override
    public Fragment getParent() {
        return this.parent;
    }

    protected void addChild(final Fragment child) {
        this.children.add(child);
    }

    protected List<Fragment> getChildren() {
        return this.children;
    }

    @Override
    public Fragment getChild(final int index) {
        return this.children.get(index);
    }

    @Override
    public boolean isFirstSibling() {
        return (this.parent != null) && (this.getParent().getChild(0) == this);
    }

    @Override
    public void accept(final FragmentVisitor visitor) {
        acceptConcrete(visitor);
        for (final Fragment child : getChildren()) {
            child.accept(visitor);
        }
    }

    protected abstract void acceptConcrete(final FragmentVisitor visitor);
}