package com.exasol.sql.dml.merge;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

/**
 * Representation of the {@code WHEN NOT MATCHED} part of an SQL {@code MERGE statement}.
 */
public class NotMatchedClause extends AbstractFragment implements MergeFragment {
    private MergeInsertClause mergeInsertClause;

    /**
     * Create a new instance of a {@link NotMatchedClause}.
     *
     * @param root root SQL statement this {@code WHEN NOT MATCHED} clause belongs to
     */
    public NotMatchedClause(final Fragment root) {
        super(root);
    }

    /**
     * Select inserting as merge strategy for rows from the source for which no match in the destination exists.
     *
     * @return update clause
     */
    public MergeInsertClause thenInsert() {
        this.mergeInsertClause = new MergeInsertClause(this.root);
        return this.mergeInsertClause;
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        if (this.mergeInsertClause != null) {
            this.mergeInsertClause.accept(visitor);
        }
    }
}