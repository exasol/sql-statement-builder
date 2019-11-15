package com.exasol.sql.dml.merge;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;
import com.exasol.sql.expression.BooleanExpression;

/**
 * The {@code ON} clause of an SQL {@code MERGE} statement.
 */
public class OnClause extends AbstractFragment implements MergeFragment {
    private final BooleanExpression condition;

    /**
     * Create a new instance of a {@link OnClause}.
     *
     * @param root root SQL statement this {@code ON} clause belongs to
     * @param condition match condition
     */
    public OnClause(final Fragment root, final BooleanExpression condition) {
        super(root);
        this.condition = condition;
    }

    /**
     * Get the merge condition.
     *
     * @return merge condition
     */
    public BooleanExpression getCondition() {
        return this.condition;
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
    }
}