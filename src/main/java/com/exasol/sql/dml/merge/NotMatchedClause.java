package com.exasol.sql.dml.merge;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

/**
 * Representation of the {@code WHEN NOT MATCHED} part of an SQL {@code MERGE statement}.
 */
public class NotMatchedClause extends AbstractFragment implements MergeFragment {
    private MergeInsertClause mergeInsertClause;

    public NotMatchedClause(final Fragment root) {
        super(root);
    }

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