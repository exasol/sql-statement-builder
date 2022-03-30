package com.exasol.sql.dml.merge;

import com.exasol.sql.Fragment;
import com.exasol.sql.dml.insert.AbstractInsertValueTable;
import com.exasol.sql.dql.select.WhereClause;
import com.exasol.sql.expression.BooleanExpression;

/**
 * Represents the {@code MERGE} strategy of inserting rows from the source that do not match any row in the destination.
 */
public class MergeInsertClause extends AbstractInsertValueTable<MergeInsertClause> implements MergeFragment {
    /** {@code WHERE} clause  */
    protected WhereClause where = null;

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

    /**
     * Add a {@code WHERE} clause insertion definition.
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
     * Get the {@code WHERE} clause of the insert definition.
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

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        if (hasFields()) {
            this.insertFields.accept(visitor);
        }
        if (hasValues()) {
            this.insertValueTable.accept(visitor);
        }
        if (hasWhere()) {
            this.where.accept(visitor);
        }
    }
}