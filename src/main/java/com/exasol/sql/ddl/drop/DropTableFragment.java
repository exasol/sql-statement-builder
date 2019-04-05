package com.exasol.sql.ddl.drop;

import com.exasol.sql.Fragment;

/**
 * This is the common interface for all fragments of a DROP TABLE statement.
 */
public interface DropTableFragment extends Fragment {
    /**
     * Accept a visitor (e.g. a renderer or validator)
     *
     * @param visitor visitor to accept
     */
    public void accept(DropTableVisitor visitor);
}
