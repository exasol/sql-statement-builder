package com.exasol.sql.dml.merge;

import com.exasol.sql.Table;

public interface MergeVisitor {
    public void visit(Merge merge);

    public void visit(Table table);

    public void visit(UsingClause using);

    public void visit(OnClause onClause);

    public void visit(MatchedClause matchedClause);

    public void visit(MergeUpdateClause mergeUpdateClause);

    public void visit(MergeColumnUpdate mergeColumnUpdate);

    public void visit(MergeDeleteClause mergeDeleteClause);

    public void visit(MergeInsertClause mergeInsertClause);
}