package com.exasol.sql.rendering;

import com.exasol.sql.Fragment;
import com.exasol.sql.FragmentVisitor;
import com.exasol.sql.dql.*;

/**
 * The {@link StringRenderer} turns SQL statement structures in to SQL strings.
 */
public class StringRenderer implements FragmentVisitor {
    private final StringBuilder builder = new StringBuilder();
    private final StringRendererConfig config;

    /**
     * Create a new {@link StringRenderer} using the default
     * {@link StringRendererConfig}.
     */
    public StringRenderer() {
        this.config = new StringRendererConfig.Builder().build();
    }

    /**
     * Create a new {@link StringRenderer} with custom render settings.
     *
     * @param config render configuration settings
     */
    public StringRenderer(final StringRendererConfig config) {
        this.config = config;
    }

    /**
     * Render an SQL statement to a string.
     *
     * @return rendered string
     */
    public String render() {
        return this.builder.toString();
    }

    @Override
    public void visit(final Select select) {
        this.builder.append(this.config.produceLowerCase() ? "select" : "SELECT");
    }

    @Override
    public void visit(final Field field) {
        appendCommaWhenNeeded(field);
        this.builder.append(" ");
        this.builder.append(field.getName());
    }

    private void appendCommaWhenNeeded(final Fragment fragment) {
        if (!fragment.isFirstSibling()) {
            this.builder.append(",");
        }
    }

    @Override
    public void visit(final FromClause fromClause) {
        this.builder.append(this.config.produceLowerCase() ? " from" : " FROM");
    }

    @Override
    public void visit(final TableReference tableReference) {
    }

    @Override
    public void visit(final Table table) {
        appendCommaWhenNeeded(table);
        this.builder.append(" ");
        this.builder.append(table.getName());
    }
}