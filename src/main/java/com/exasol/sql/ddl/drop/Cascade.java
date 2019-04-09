package com.exasol.sql.ddl.drop;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

/**
 * This class represents CASCADE clause in DROP SCHEMA SQL statement
 */
public final class Cascade extends AbstractFragment implements DropSchemaFragment {
    /**
     * Create an instance of {@link Cascade} class
     *
     * @param root root SQL statement this fragment belongs to
     */
    protected Cascade(final Fragment root) {
        super(root);
    }

    @Override
    public void accept(final DropSchemaVisitor visitor) {
        visitor.visit(this);
    }
}
