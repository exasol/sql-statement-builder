package com.exasol.sql.dml;

import com.exasol.sql.Fragment;

/**
 * This is the common interface for all fragments of a SELECT statement.
 */
public interface InsertFragment extends Fragment {
    /**
     * Accept a visitor (e.g. a renderer or validator)
     *
     * @param visitor visitor to accept
     */
    public void accept(InsertVisitor visitor);
}