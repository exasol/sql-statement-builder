package com.exasol.sql.dql.select;

import com.exasol.sql.Fragment;

/**
 * This is the common interface for all fragments of a SELECT statement.
 */
public interface SelectFragment extends Fragment {
    /**
     * Accept a visitor (e.g. a renderer or validator)
     *
     * @param visitor visitor to accept
     */
    public void accept(SelectVisitor visitor);
}