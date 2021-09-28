package com.exasol.sql.dql.select;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

/**
 * This class represents the {@code WINDOW} clause following the {@code HAVING} clause of an SQL statement.
 */
public class WindowClause extends AbstractFragment implements SelectFragment {

    private WindowClause(final Fragment root) {
        super(root);
    }

    public static WindowClause of(final Fragment root) {
        return new WindowClause(root);
    }

    @Override
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
    }
}
