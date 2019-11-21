package com.exasol.sql.dml.merge;

import com.exasol.sql.Fragment;

/**
 * This is the common interface for all fragments of a {@code MERGE} statement.
 */
public interface MergeFragment extends Fragment {

    /**
     * Accept a visitor (e.g. a renderer or validator).
     *
     * @param visitor visitor to accept
     */
    public void accept(final MergeVisitor visitor);
}
