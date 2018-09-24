package com.exasol.sql.rendering;

import java.util.Optional;

import com.exasol.sql.Fragment;
import com.exasol.sql.FragmentVisitor;
import com.exasol.sql.dql.*;
import com.exasol.sql.expression.rendering.BooleanExpressionRenderer;

/**
 * The {@link SqlStatementRenderer} turns SQL statement structures in to SQL
 * strings.
 */
public class SqlStatementRenderer implements FragmentVisitor {
    private final StringBuilder builder = new StringBuilder();
    private final StringRendererConfig config;

    /**
     * Create a new {@link SqlStatementRenderer} using the default
     * {@link StringRendererConfig}.
     */
    public SqlStatementRenderer() {
        this(new StringRendererConfig.Builder().build());
    }

    /**
     * Create a new {@link SqlStatementRenderer} with custom render settings.
     *
     * @param config render configuration settings
     */
    public SqlStatementRenderer(final StringRendererConfig config) {
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
        appendKeyWord("select");
    }

    private void appendKeyWord(final String keyWord) {
        append(this.config.produceLowerCase() ? keyWord : keyWord.toUpperCase());
    }

    private StringBuilder append(final String string) {
        return this.builder.append(string);
    }

    @Override
    public void visit(final Field field) {
        appendCommaWhenNeeded(field);
        append(" ");
        append(field.getName());
    }

    private void appendCommaWhenNeeded(final Fragment fragment) {
        if (!fragment.isFirstSibling()) {
            append(",");
        }
    }

    @Override
    public void visit(final FromClause fromClause) {
        appendKeyWord(" from");
    }

    @Override
    public void visit(final Table table) {
        appendCommaWhenNeeded(table);
        append(" ");
        append(table.getName());
        final Optional<String> as = table.getAs();
        if (as.isPresent()) {
            appendKeyWord(" as ");
            append(as.get());
        }
    }

    @Override
    public void visit(final Join join) {
        final JoinType type = join.getType();
        if (type != JoinType.DEFAULT) {
            append(" ");
            appendKeyWord(type.toString());
        }
        appendKeyWord(" join ");
        append(join.getName());
        appendKeyWord(" on ");
        append(join.getSpecification());
    }

    @Override
    public void visit(final BooleanValueExpression value) {
        final BooleanExpressionRenderer subRenderer = new BooleanExpressionRenderer();
        value.getExpression().accept(subRenderer);
        append(" ");
        append(subRenderer.render());
    }
}