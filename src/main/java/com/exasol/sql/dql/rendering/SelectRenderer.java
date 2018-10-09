package com.exasol.sql.dql.rendering;

import java.util.Optional;

import com.exasol.sql.Field;
import com.exasol.sql.Table;
import com.exasol.sql.dql.*;
import com.exasol.sql.rendering.AbstractFragmentRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * The {@link SelectRenderer} turns SQL statement structures in to SQL strings.
 */
public class SelectRenderer extends AbstractFragmentRenderer implements SelectVisitor {
    /**
     * Create a new {@link SelectRenderer} with custom render settings.
     *
     * @param config render configuration settings
     */
    public SelectRenderer(final StringRendererConfig config) {
        super(config);
    }

    @Override
    public void visit(final Select select) {
        appendKeyWord("SELECT ");
        setLastVisited(select);
    }

    @Override
    public void visit(final Field field) {
        appendCommaWhenNeeded(field);
        append(field.getName());
        setLastVisited(field);
    }

    @Override
    public void visit(final FromClause fromClause) {
        appendKeyWord(" FROM ");
        setLastVisited(fromClause);
    }

    @Override
    public void visit(final Table table) {
        appendCommaWhenNeeded(table);
        append(table.getName());
        final Optional<String> as = table.getAs();
        if (as.isPresent()) {
            appendKeyWord(" AS ");
            append(as.get());
        }
        setLastVisited(table);
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
        setLastVisited(join);
    }

    @Override
    public void visit(final WhereClause whereClause) {
        appendKeyWord(" WHERE ");
        appendRenderedExpression(whereClause.getExpression());
        setLastVisited(whereClause);
    }

    @Override
    public void visit(final LimitClause limit) {
        appendKeyWord(" LIMIT ");
        if (limit.hasOffset()) {
            append(limit.getOffset());
            appendKeyWord(", ");
        }
        append(limit.getCount());
        setLastVisited(limit);
    }

    /**
     * Create an {@link SelectRenderer} using the default renderer configuration
     *
     * @return select renderer
     */
    public static SelectRenderer create() {
        return create(StringRendererConfig.createDefault());
    }

    /**
     * Create an {@link SelectRenderer}
     *
     * @param config renderer configuration
     * @return select renderer
     */
    public static SelectRenderer create(final StringRendererConfig config) {
        return new SelectRenderer(config);
    }
}