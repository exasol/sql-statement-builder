package com.exasol.sql.dml.merge;

import com.exasol.sql.*;
import com.exasol.sql.dml.insert.InsertFields;

public interface MergeVisitor extends TableValuesVisitor {
    public void visit(final Merge merge);

    public void visit(final Table table);

    public void visit(final UsingClause using);

    public void visit(final OnClause onClause);

    public void visit(final MatchedClause matchedClause);

    public void visit(final MergeUpdateClause mergeUpdateClause);

    public void visit(final MergeColumnUpdate mergeColumnUpdate);

    public void visit(final MergeDeleteClause mergeDeleteClause);

    public void visit(final NotMatchedClause notMatchedClause);

    public void visit(final MergeInsertClause mergeInsertClause);

    public void visit(final InsertFields insertFields);

    public void leave(final InsertFields insertFields);

    public void visit(final Field field);
}