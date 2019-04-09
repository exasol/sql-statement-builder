package com.exasol.sql.ddl.drop;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

/**
 * This class represents RESTRICT clause in DROP SCHEMA SQL statement
 */
public final class Restrict extends AbstractFragment implements DropSchemaFragment {
    /**
     * Create an instance of {@link Restrict} class
     *
     * @param root root SQL statement this fragment belongs to
     */
    protected Restrict(final Fragment root) {
        super(root);
    }

    @Override
    public void accept(final DropSchemaVisitor visitor) {
        visitor.visit(this);
    }
}
