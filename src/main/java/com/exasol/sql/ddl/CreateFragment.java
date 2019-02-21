package com.exasol.sql.ddl;

import com.exasol.sql.Fragment;

public interface CreateFragment extends Fragment {
    /**
     * Accept a visitor (e.g. a renderer or validator)
     *
     * @param visitor visitor to accept
     */
    public void accept(CreateVisitor visitor);
}
