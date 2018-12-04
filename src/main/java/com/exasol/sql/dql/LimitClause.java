package com.exasol.sql.dql;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.SqlStatement;

/**
 * This class represents the limit clause of an SQL statement. It lets you choose offset and / or count of rows to be
 * handed back in the result.
 */
public class LimitClause extends AbstractFragment implements SelectFragment {
    private final int count;
    private final int offset;

    /**
     * Create a new instance of a {@link LimitClause}
     *
     * @param root SQL statement this LIMIT clause belongs to
     *
     * @param count maximum number of rows to be included in the query result
     */
    public LimitClause(final SqlStatement root, final int count) {
        this(root, 0, count);
    }

    /**
     * Create a new instance of a {@link LimitClause}
     *
     * @param root SQL statement this LIMIT clause belongs to
     *
     * @param offset index of the first row to be included in the query result
     *
     * @param count maximum number of rows to be included in the query result
     */
    public LimitClause(final SqlStatement root, final int offset, final int count) {
        super(root);
        this.offset = offset;
        this.count = count;
    }

    /**
     * Get the offset row for the limit
     *
     * @return first row which should be handed back
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Get the maximum number of rows to be handed back
     *
     * @return maximum number of rows
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Check if the limit clause has an offset
     *
     * @return <code>true</code> if the limit clause has an offset
     */
    public boolean hasOffset() {
        return this.offset > 0;
    }

    @Override
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
    }
}