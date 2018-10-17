package com.exasol.sql;

/**
 * This is the common interface for all fragments of SQL statements. Fragments can be clauses like the WHERE clause of
 * an SELECT statement but also lower level concepts like boolean expressions.
 */
public interface Fragment {
    /**
     * Get the root statement of this SQL fragment
     *
     * @return the root fragment
     */
    public Fragment getRoot();
}
