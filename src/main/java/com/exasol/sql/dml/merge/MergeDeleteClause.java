package com.exasol.sql.dml.merge;

import com.exasol.sql.Fragment;
import com.exasol.sql.dql.select.WhereClause;
import com.exasol.sql.expression.BooleanExpression;

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

    /**
     * Add a {@code WHERE} clause to the deletion definition.
     *
     * @param expression filter expression
     * @return parent {@code MERGE} statement
     */
    public Merge where(final BooleanExpression expression) {
        final Merge merge = (Merge) this.getRoot();
        this.where = new WhereClause(merge, expression);
        return merge;
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        if (hasWhere()) {
            this.where.accept(visitor);
        }
    }
}