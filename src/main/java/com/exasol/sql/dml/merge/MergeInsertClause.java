package com.exasol.sql.dml.merge;

import com.exasol.sql.Fragment;
import com.exasol.sql.dml.insert.AbstractInsertValueTable;

/**
 * Represents the {@code MERGE} strategy of inserting rows from the source that do not match any row in the destination.
 */
public class MergeInsertClause extends AbstractInsertValueTable<MergeInsertClause> implements MergeFragment {
    /**
     * Create a new instance of a {@link MergeInsertClause}.
     *
     * @param root root SQL statement this {@code THEN INSERT} clause belongs to
     */
    public MergeInsertClause(final Fragment root) {
        super(root);
    }

    @Override
    protected MergeInsertClause self() {
        return this;
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        if (this.insertFields != null) {
            this.insertFields.accept(visitor);
        }
        if (this.insertValueTable != null) {
            this.insertValueTable.accept(visitor);
        }
    }
}