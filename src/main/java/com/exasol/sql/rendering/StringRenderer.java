package com.exasol.sql.rendering;

import java.util.Optional;

import com.exasol.sql.Fragment;
import com.exasol.sql.FragmentVisitor;
import com.exasol.sql.dql.*;
import com.exasol.util.visitor.AbstractHierarchicalVisitor;

/**
 * The {@link StringRenderer} turns SQL statement structures in to SQL strings.
 */
public class StringRenderer extends AbstractHierarchicalVisitor implements FragmentVisitor {
    private final StringBuilder builder = new StringBuilder();
    private final StringRendererConfig config;

    /**
     * Create a new {@link StringRenderer} using the default
     * {@link StringRendererConfig}.
     */
    public StringRenderer() {
        this(new StringRendererConfig.Builder().build());
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
        this.builder.append(produceLowerCase() ? "select" : "SELECT");
    }

    private boolean produceLowerCase() {
        return this.config.produceLowerCase();
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
        this.builder.append(produceLowerCase() ? " from" : " FROM");
    }

    @Override
    public void visit(final TableReference tableReference) {
    }

    @Override
    public void visit(final Table table) {
        appendCommaWhenNeeded(table);
        this.builder.append(" ");
        this.builder.append(table.getName());
        final Optional<String> as = table.getAs();
        if (as.isPresent()) {
            this.builder.append(produceLowerCase() ? " as " : " AS ");
            this.builder.append(as.get());
        }
    }

    @Override
    public void visit(final Join join) {
        final JoinType type = join.getType();
        if (type != JoinType.DEFAULT) {
            this.builder.append(" ");
            this.builder.append(produceLowerCase() ? type.toString().toLowerCase() : type.toString());
        }
        this.builder.append(produceLowerCase() ? " join " : " JOIN ");
        this.builder.append(join.getName());
        this.builder.append(produceLowerCase() ? " on " : " ON ");
        this.builder.append(join.getSpecification());
    }
}