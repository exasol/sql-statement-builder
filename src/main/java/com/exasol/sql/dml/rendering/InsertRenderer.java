package com.exasol.sql.dml.rendering;

import com.exasol.sql.Field;
import com.exasol.sql.Table;
import com.exasol.sql.dml.*;
import com.exasol.sql.rendering.AbstractFragmentRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

public class InsertRenderer extends AbstractFragmentRenderer implements InsertVisitor {
    /**
     * Create a new {@link InsertRenderer} with custom render settings.
     *
     * @param config render configuration settings
     */
    public InsertRenderer(final StringRendererConfig config) {
        super(config);
    }

    @Override
    public void visit(final Insert insert) {
        appendKeyWord("INSERT INTO ");
        setLastVisited(insert);
    }

    @Override
    public void visit(final Table table) {
        append(table.getName());
        setLastVisited(table);
    }

    @Override
    public void visit(final Field field) {
        appendCommaWhenNeeded(field);
        append(field.getName());
        setLastVisited(field);
    }

    @Override
    public void visit(final InsertFields insertFields) {
        append(" (");
    }

    @Override
    public void leave(final InsertFields insertFields) {
        append(")");
    }
}