package com.exasol.sql.rendering;

import java.util.Optional;

import com.exasol.sql.Fragment;
import com.exasol.sql.FragmentVisitor;
import com.exasol.sql.dql.*;
import com.exasol.sql.expression.BooleanExpression;
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
        appendKeyWord("SELECT");
    }

    private void appendKeyWord(final String keyword) {
        append(this.config.produceLowerCase() ? keyword.toLowerCase() : keyword);
    }

    private StringBuilder append(final String string) {
        return this.builder.append(string);
    }

    @Override
    public void visit(final Field field) {
        appendCommaWhenNeeded(field);
        appendSpace();
        append(field.getName());
    }

    private void appendSpace() {
        append(" ");
    }

    private void appendCommaWhenNeeded(final Fragment fragment) {
        if (!fragment.isFirstSibling()) {
            append(",");
        }
    }

    @Override
    public void visit(final FromClause fromClause) {
        appendKeyWord(" FROM");
    }

    @Override
    public void visit(final Table table) {
        appendCommaWhenNeeded(table);
        appendSpace();
        append(table.getName());
        final Optional<String> as = table.getAs();
        if (as.isPresent()) {
            appendKeyWord(" AS ");
            append(as.get());
        }
    }

    @Override
    public void visit(final Join join) {
        final JoinType type = join.getType();
        if (type != JoinType.DEFAULT) {
            appendSpace();
            appendKeyWord(type.toString());
        }
        appendKeyWord(" JOIN ");
        append(join.getName());
        appendKeyWord(" ON ");
        append(join.getSpecification());
    }

    @Override
    public void visit(final BooleanValueExpression value) {
        appendSpace();
        appendRenderedExpression(value.getExpression());
    }

    private void appendRenderedExpression(final BooleanExpression expression) {
        final BooleanExpressionRenderer expressionRenderer = new BooleanExpressionRenderer();
        expression.accept(expressionRenderer);
        append(expressionRenderer.render());
    }

    @Override
    public void visit(final LimitClause limit) {
        appendKeyWord(" LIMIT ");
        if (limit.hasOffset()) {
            append(limit.getOffset());
            appendKeyWord(", ");
        }
        append(limit.getCount());
    }

    private void append(final int number) {
        this.builder.append(number);
    }

    @Override
    public void visit(final WhereClause whereClause) {
        appendKeyWord(" WHERE ");
        appendRenderedExpression(whereClause.getExpression());
    }

    /**
     * Create a renderer for the given {@link Fragment} and render it.
     *
     * @param fragment SQL statement fragment to be rendered
     * @return rendered statement
     */
    public static String render(final Fragment fragment) {
        final SqlStatementRenderer renderer = new SqlStatementRenderer();
        ((Fragment) fragment.getRoot()).accept(renderer);
        return renderer.render();
    }
}