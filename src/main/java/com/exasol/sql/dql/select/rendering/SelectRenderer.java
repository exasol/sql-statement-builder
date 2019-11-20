package com.exasol.sql.dql.select.rendering;

import com.exasol.sql.*;
import com.exasol.sql.dql.select.*;
import com.exasol.sql.expression.BooleanExpression;
import com.exasol.sql.rendering.AbstractFragmentRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * The {@link SelectRenderer} turns SQL statement structures in to SQL strings.
 */
// [impl->dsn~rendering.sql.select~1]
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
        appendAutoQuoted(field.getName());
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
        appendAutoQuoted(table.getName());
        if (table.hasAlias()) {
            appendKeyWord(" AS ");
            append(table.getAlias());
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
        appendAutoQuoted(join.getName());
        appendKeyWord(" ON ");
        append(join.getSpecification());
        setLastVisited(join);
    }

    @Override
    public void visit(final WhereClause whereClause) {
        appendKeyWord(" WHERE ");
        appendRenderedBooleanExpression(whereClause.getExpression());
        setLastVisited(whereClause);
    }

    @Override
    public void visit(final GroupByClause groupByClause) {
        appendKeyWord(" GROUP BY ");
        appendListOfColumnReferences(groupByClause.getColumnReferences());
        final BooleanExpression having = groupByClause.getHavingBooleanExpression();
        if (having != null) {
            appendKeyWord(" HAVING ");
            appendRenderedBooleanExpression(having);
        }
        setLastVisited(groupByClause);
    }

    @Override
    public void visit(final OrderByClause orderByClause) {
        appendKeyWord(" ORDER BY ");
        appendListOfColumnReferences(orderByClause.getColumnReferences());
        final Boolean desc = orderByClause.getDesc();
        appendStringDependingOnBoolean(desc, " DESC", " ASC");
        final Boolean nullsFirst = orderByClause.getNullsFirst();
        appendStringDependingOnBoolean(nullsFirst, " NULLS FIRST", " NULLS LAST");
        setLastVisited(orderByClause);
    }

    private void appendStringDependingOnBoolean(final Boolean booleanValue, final String string1,
            final String string2) {
        if (booleanValue != null) {
            if (booleanValue) {
                appendKeyWord(string1);
            } else {
                appendKeyWord(string2);
            }
        }
    }

    @Override
    public void visit(final LimitClause limit) {
        appendKeyWord(" LIMIT ");
        if (limit.hasOffset()) {
            append(limit.getOffset());
            append(", ");
        }
        append(limit.getCount());
        setLastVisited(limit);
    }

    @Override
    public void visit(final ValueTable valueTable) {
        appendKeyWord("(VALUES ");
        setLastVisited(valueTable);
    }

    @Override
    public void leave(final ValueTable valueTable) {
        append(")");
        setLastVisited(valueTable);
    }

    @Override
    public void visit(final ValueTableRow valueTableRow) {
        appendCommaWhenNeeded(valueTableRow);
        append("(");
        appendValueTableRow(valueTableRow);
        setLastVisited(valueTableRow);
    }

    @Override
    public void leave(final ValueTableRow valueTableRow) {
        append(")");
        setLastVisited(valueTableRow);
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