package com.exasol.sql.ddl.create;

import com.exasol.sql.Fragment;

/**
 * This is the common interface for all fragments of a CREATE SCHEMA statement.
 */
public interface CreateSchemaFragment extends Fragment {
    /**
     * Accept a visitor (e.g. a renderer or validator)
     *
     * @param visitor visitor to accept
     */
    public void accept(CreateSchemaVisitor visitor);
}
