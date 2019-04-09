package com.exasol.sql.ddl.drop;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

/**
 * This class represents CASCADE CONSTRAINTS clause in the DROP TABLE SQL statement
 */
public final class CascadeConstraints extends AbstractFragment implements DropTableFragment {
    /**
     * Create an instance of {@link CascadeConstraints} class
     *
     * @param root root SQL statement this fragment belongs to
     */
    protected CascadeConstraints(final Fragment root) {
        super(root);
    }

    @Override
    public void accept(final DropTableVisitor visitor) {
        visitor.visit(this);
    }
}