package com.exasol.sql.dml.merge;

import com.exasol.sql.Fragment;

/**
 * This class represents the {@code MERGE} strategy of deleting matched rows.
 */
public class MergeDeleteClause extends MergeMethodDefinition implements MergeFragment {
    /**
     * Create a new instance of a {@link MergeDeleteClause}.
     *
     * @param root root SQL statement this {@code THEN DELETE} clause belongs to
     */
    public MergeDeleteClause(final Fragment root) {
        super(root);
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        if (hasWhere()) {
            this.where.accept(visitor);
        }
    }
}