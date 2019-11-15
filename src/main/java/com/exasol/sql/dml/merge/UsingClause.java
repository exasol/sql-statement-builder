package com.exasol.sql.dml.merge;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;
import com.exasol.sql.Table;

/**
 * The {@code USING} clause of an SQL {@code MERGE} statement.
 */
public class UsingClause extends AbstractFragment implements MergeFragment {
    private final Table sourceTable;

    /**
     * Create a new instance of a {@link UsingClause}.
     *
     * @param root root SQL statement this {@code USING} clause belongs to
     * @param source origin of the data to be merged
     */
    public UsingClause(final Fragment root, final String source) {
        super(root);
        this.sourceTable = new Table(this, source);
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        visitor.visit(this.sourceTable);
    }
}