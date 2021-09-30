package com.exasol.sql.dql.select.rendering;

import java.util.List;

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
    public void visit(final DerivedColumn derivedColumn) {
        appendCommaWhenNeeded(derivedColumn);
        appendRenderedValueExpression(derivedColumn.getValueExpression());
        if (derivedColumn.hasDerivedColumnName()) {
            appendSpace();
            append(derivedColumn.getDerivedColumnName());
        }
        setLastVisited(derivedColumn);
    }

    @Override
    public void visit(final FromClause fromClause) {
        appendKeyWord(" FROM ");
        if (fromClause.hasSubSelect()) {
            startParenthesis();
        }
        setLastVisited(fromClause);
    }

    @Override
    public void leave(final FromClause fromClause) {
        if (fromClause.hasSubSelect()) {
            endParenthesis();
        }
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
        appendRenderedValueExpression(whereClause.getExpression());
        setLastVisited(whereClause);
    }

    @Override
    public void visit(final GroupByClause groupByClause) {
        appendKeyWord(" GROUP BY ");
        appendListOfValueExpressions(groupByClause.getColumnReferences());
        final BooleanExpression having = groupByClause.getHavingBooleanExpression();
        if (having != null) {
            appendKeyWord(" HAVING ");
            appendRenderedValueExpression(having);
        }
        setLastVisited(groupByClause);
    }

    @Override
    public void visit(final OrderByClause orderByClause) {
        appendKeyWord(" ORDER BY ");
        appendListOfValueExpressions(orderByClause.getColumnReferences());
        final Boolean desc = orderByClause.getDesc();
        if (desc != null) {
            appendStringDependingOnBoolean(desc, " DESC", " ASC");
        }
        final Boolean nullsFirst = orderByClause.getNullsFirst();
        if (nullsFirst != null) {
            appendStringDependingOnBoolean(nullsFirst, " NULLS FIRST", " NULLS LAST");
        }
        setLastVisited(orderByClause);
    }

    private void appendStringDependingOnBoolean(final boolean booleanValue, final String string1,
            final String string2) {
        if (booleanValue) {
            appendKeyWord(string1);
        } else {
            appendKeyWord(string2);
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
        endParenthesis();
        if (valueTable.hasAlias()) {
            appendKeyWord(" AS ");
            appendAutoQuoted(valueTable.getTableNameAlias());
            startParenthesis();
            final List<String> columnNameAliases = valueTable.getColumnNameAliases();
            for (int index = 0; index < columnNameAliases.size(); index++) {
                appendAutoQuoted(columnNameAliases.get(index));
                if (index < (columnNameAliases.size() - 1)) {
                    append(", ");
                }
            }
            endParenthesis();
        }
        setLastVisited(valueTable);
    }

    @Override
    public void visit(final ValueTableRow valueTableRow) {
        appendCommaWhenNeeded(valueTableRow);
        startParenthesis();
        appendValueTableRow(valueTableRow);
        setLastVisited(valueTableRow);
    }

    @Override
    public void leave(final ValueTableRow valueTableRow) {
        endParenthesis();
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