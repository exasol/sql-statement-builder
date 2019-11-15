package com.exasol.sql.dml.merge.rendering;

import com.exasol.sql.Table;
import com.exasol.sql.dml.insert.rendering.InsertRenderer;
import com.exasol.sql.dml.merge.*;
import com.exasol.sql.rendering.AbstractFragmentRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * The {@link InsertRenderer} turns SQL statement structures in to SQL strings.
 */
// [impl->dsn~rendering.sql.merge~1]
public class MergeRenderer extends AbstractFragmentRenderer implements MergeVisitor {
    /**
     * Create a new {@link MergeRenderer} with custom render settings.
     *
     * @param config render configuration settings
     */
    public MergeRenderer(final StringRendererConfig config) {
        super(config);
    }

    @Override
    public void visit(final Merge merge) {
        appendKeyWord("MERGE INTO ");
        setLastVisited(merge);
    }

    @Override
    public void visit(final Table table) {
        appendAutoQuoted(table.getName());
        setLastVisited(table);
    }

    @Override
    public void visit(final UsingClause using) {
        appendKeyWord(" USING ");
        setLastVisited(using);
    }

    @Override
    public void visit(final OnClause onClause) {
        appendKeyWord(" ON ");
        appendRenderedBooleanExpression(onClause.getCondition());
        setLastVisited(onClause);
    }

    @Override
    public void visit(final MatchedClause matchedClause) {
        appendKeyWord(" WHEN MATCHED ");
        setLastVisited(matchedClause);
    }

    @Override
    public void visit(final MergeUpdateClause mergeUpdateClause) {
        appendKeyWord("THEN UPDATE SET ");
        setLastVisited(mergeUpdateClause);
    }

    @Override
    public void visit(final MergeColumnUpdate columnUpdate) {
        appendCommaWhenNeeded(columnUpdate);
        appendAutoQuoted(columnUpdate.getColumn());
        append(" = ");
        appendRenderedValueExpression(columnUpdate.getExpression());
        setLastVisited(columnUpdate);
    }

    @Override
    public void visit(final MergeDeleteClause mergeDeleteClause) {
        appendKeyWord("THEN DELETE");
        setLastVisited(mergeDeleteClause);
    }

    @Override
    public void visit(final MergeInsertClause mergeInsertClause) {
        appendKeyWord(" WHEN NOT MATCHED THEN INSERT");
        setLastVisited(mergeInsertClause);
    }

    /**
     * Create a {@link MergeRenderer}
     *
     * @param config renderer configuration
     * @return {@code MERGE} renderer
     */
    public static MergeRenderer create(final StringRendererConfig config) {
        return new MergeRenderer(config);
    }

    /**
     * Create a {@link MergeRenderer} using the default renderer configuration
     *
     * @return {@code MERGE} renderer
     */
    public static MergeRenderer create() {
        return create(StringRendererConfig.createDefault());
    }
}