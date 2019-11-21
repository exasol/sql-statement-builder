package com.exasol.sql.dml.merge;

import com.exasol.sql.*;

/**
 * The {@code USING} clause of an SQL {@code MERGE} statement.
 */
public class UsingClause extends AbstractFragment implements MergeFragment {
    private final Table sourceTable;

    /**
     * Create a new instance of a {@link UsingClause}.
     *
     * @param root root SQL statement this {@code USING} clause belongs to
     * @param sourceTable origin of the data to be merged
     */
    public UsingClause(final Fragment root, final String sourceTable) {
        super(root);
        this.sourceTable = new Table(this, sourceTable);
    }

    /**
     * Create a new instance of a {@link UsingClause}.
     *
     * @param root root SQL statement this {@code USING} clause belongs to
     * @param as table alias
     * @param sourceTable origin of the data to be merged
     */
    public UsingClause(final Fragment root, final String sourceTable, final String as) {
        super(root);
        this.sourceTable = new Table(this, sourceTable, as);
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        visitor.visit(this.sourceTable);
    }
}