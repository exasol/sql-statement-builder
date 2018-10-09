package com.exasol.sql;

/**
 * Common interface for all SQL statement fragments which are used in multiple types of statements, like tables and
 * fields.
 */
public interface GenericFragment extends Fragment {
    /**
     * Accept a generic fragment visitor
     *
     * @param visitor visitor
     */
    public void accept(final FragmentVisitor visitor);
}