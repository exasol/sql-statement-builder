package com.exasol.sql.dml.merge;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;
import com.exasol.sql.dql.select.WhereClause;
import com.exasol.sql.expression.BooleanExpression;

/**
 * Abstract base class for merge method definitions like {@code WHEN MATCHED THEN UPDATE}.
 */
public abstract class MergeMethodDefinition extends AbstractFragment {
    protected WhereClause where = null;

    /**
     * Create the abstract base for a merge method definition.
     *
     * @param root root {@code MEREG} statement
     */
    public MergeMethodDefinition(final Fragment root) {
        super(root);
    }

    /**
     * Add a {@code WHERE} clause {@code MERGE} definition.
     *
     * @param expression filter expression
     * @return parent {@code MERGE} statement
     */
    public Merge where(final BooleanExpression expression) {
        final Merge merge = (Merge) this.getRoot();
        this.where = new WhereClause(merge, expression);
        return merge;
    }

    /**
     * Get the {@code WHERE} clause of the update definition.
     *
     * @return {@code WHERE} clause
     */
    public WhereClause getWhere() {
        return this.where;
    }

    /**
     * Check if the {@code WHERE} clause exists.
     *
     * @return {@code true} if the {@code WHERE} clause exists
     */
    public boolean hasWhere() {
        return this.where != null;
    }
}