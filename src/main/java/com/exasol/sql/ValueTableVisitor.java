package com.exasol.sql;

/**
 * Visitor for {@link ValueTable}.
 */
public interface ValueTableVisitor
{
    /**
     * Visit a value table.
     *
     * @param valueTable value table to visit
     */
    public void visit(final ValueTable valueTable);

    /**
     * Leave a value table.
     *
     * @param valueTable value table to leave
     */
    public void leave(final ValueTable valueTable);

    /**
     * Visit a row in a value table.
     *
     * @param valueTableRow row to visit
     */
    public void visit(final ValueTableRow valueTableRow);

    /**
     * Leave a row in a value table
     *
     * @param valueTableRow row to leave
     */
    public void leave(final ValueTableRow valueTableRow);
}