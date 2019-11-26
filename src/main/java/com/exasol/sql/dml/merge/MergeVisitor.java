package com.exasol.sql.dml.merge;

import com.exasol.sql.*;
import com.exasol.sql.dml.insert.InsertFields;
import com.exasol.sql.dql.select.WhereClause;

/**
 * Visitor for value tables.
 */
public interface MergeVisitor extends ValueTableVisitor {
    /**
     * Visit {@code MERGE} statements.
     *
     * @param merge {@code MERGE} statement to be visited.
     */
    public void visit(final Merge merge);

    /**
     * Visit database tables.
     *
     * @param table table to be visited.
     */
    public void visit(final Table table);

    /**
     * Visit the source definition of a {@code MERGE} statement.
     *
     * @param using source definition
     */
    public void visit(final UsingClause using);

    /**
     * Visit the merge condition definition.
     *
     * @param onClause merge condition definition
     */
    public void visit(final OnClause onClause);

    /**
     * Visit the definition of the merge strategy in case of matching rows.
     *
     * @param matchedClause merge strategy definition for matches
     */
    public void visit(final MatchedClause matchedClause);

    /**
     * Visit the update definition.
     *
     * @param mergeUpdateClause update definition.
     */
    public void visit(final MergeUpdateClause mergeUpdateClause);

    /**
     * Visit the update of a single column inside a {@code MERGE} statement.
     *
     * @param mergeColumnUpdate update definition for a single column
     */
    public void visit(final MergeColumnUpdate mergeColumnUpdate);

    /**
     * Visit the deletion definition.
     *
     * @param mergeDeleteClause deletion definition
     */
    public void visit(final MergeDeleteClause mergeDeleteClause);

    /**
     * Visit the merge strategy in case of rows that have no match in the destination table.
     *
     * @param notMatchedClause merge strategy for rows without match
     */
    public void visit(final NotMatchedClause notMatchedClause);

    /**
     * Visit the insert definition.
     *
     * @param mergeInsertClause insert definition
     */
    public void visit(final MergeInsertClause mergeInsertClause);

    /**
     * Visit the insert field list.
     *
     * @param insertFields insert field list
     */
    public void visit(final InsertFields insertFields);

    /**
     * Leave the insert field list.
     *
     * @param insertFields insert field list
     */
    public void leave(final InsertFields insertFields);

    /**
     * Visit a field reference.
     *
     * @param field field reference
     */
    public void visit(final Field field);

    /**
     * Visit a {@code WHERE} clause.
     *
     * @param whereClause the {@code WHERE} clause to be visited
     */
    public void visit(WhereClause whereClause);
}