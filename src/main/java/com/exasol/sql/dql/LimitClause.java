package com.exasol.sql.dql;

import com.exasol.sql.*;

/**
 * This class represents the limit clause of an SQL statement. It lets you
 * choose offset and / or count of rows to be handed back in the result.
 */
public class LimitClause extends AbstractFragment {
    private final int count;
    private final int offset;

    /**
     * Create a new instance of a {@link LimitClause}
     *
     * @param parent parent SQL statement fragment
     *
     * @param count maximum number of rows to be handed back
     */
    public LimitClause(final Fragment parent, final int count) {
        this(parent, 0, count);
    }

    /**
     * Create a new instance of a {@link LimitClause}
     *
     * @param parent parent SQL statement fragment
     * @param offset first row to be handed back
     *
     * @param count maximum number of rows to be handed back
     */
    public LimitClause(final Fragment parent, final int offset, final int count) {
        super(parent);
        this.offset = offset;
        this.count = count;
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
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

    /**
     * Check if the limit clause has a count
     *
     * @return <code>true</code> if the limit clause has a count
     */
    public boolean hasCount() {
        return this.count > 0;
    }
}