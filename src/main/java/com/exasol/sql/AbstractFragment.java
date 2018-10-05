package com.exasol.sql;

/**
 * Common base class for SQL statement fragments
 */
public abstract class AbstractFragment implements Fragment {
    protected final SqlStatement rootStatement;

    /**
     * Create an instance of an SQL fragment
     *
     * @param rootStatement root SQL statement this fragment belongs to.
     */
    public AbstractFragment(final SqlStatement rootStatement) {
        this.rootStatement = rootStatement;
    }

    @Override
    public Fragment getRoot() {
        return this.rootStatement;
    }
}