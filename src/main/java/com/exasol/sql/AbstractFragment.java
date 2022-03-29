package com.exasol.sql;

/**
 * Common base class for SQL statement fragments
 */
public abstract class AbstractFragment implements Fragment {
    /** Root fragment of the statement fragment tree */
    protected final Fragment root;

    /**
     * Create an instance of an SQL fragment
     *
     * @param root root SQL statement this fragment belongs to.
     */
    protected AbstractFragment(final Fragment root) {
        this.root = root;
    }

    @Override
    public Fragment getRoot() {
        return (this.root == null) ? this : this.root;
    }
}