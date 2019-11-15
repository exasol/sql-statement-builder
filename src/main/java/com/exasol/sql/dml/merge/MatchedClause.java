package com.exasol.sql.dml.merge;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

/**
 * Representation of the {@code WHEN MATCHED} part of an SQL {@code MERGE statement}.
 * <p>
 * This class supports two different strategies, updating matching rows or deleting matching rows.
 */
public class MatchedClause extends AbstractFragment implements MergeFragment {
    private MergeUpdateClause mergeUpdateClause;
    private MergeDeleteClause mergeDeleteClause;

    /**
     * Create a new instance of a {@link MatchedClause}.
     *
     * @param root root SQL statement this {@code WHEN MATCHED} clause belongs to
     */
    public MatchedClause(final Fragment root) {
        super(root);
    }

    /**
     * Select updating as merge strategy for rows where that are considered matches between source and destination.
     *
     * @return update clause
     */
    public MergeUpdateClause thenUpdate() {
        this.mergeUpdateClause = new MergeUpdateClause(this.root);
        return this.mergeUpdateClause;
    }

    /**
     * Select deleting as merge strategy for rows where that are considered matches between source and destination.
     *
     * @return delete clause
     */
    public MergeDeleteClause thenDelete() {
        this.mergeDeleteClause = new MergeDeleteClause(this.root);
        return this.mergeDeleteClause;
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        if (this.mergeUpdateClause != null) {
            this.mergeUpdateClause.accept(visitor);
        }
        if (this.mergeDeleteClause != null) {
            this.mergeDeleteClause.accept(visitor);
        }
    }
}