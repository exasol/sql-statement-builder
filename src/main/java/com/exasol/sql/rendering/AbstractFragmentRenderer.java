package com.exasol.sql.rendering;

import java.util.*;

import com.exasol.sql.*;
import com.exasol.sql.expression.*;
import com.exasol.sql.expression.rendering.*;

/**
 * Abstract base class for SQL fragment renderers
 */
public abstract class AbstractFragmentRenderer implements FragmentRenderer {
    private final StringBuilder builder = new StringBuilder();
    protected final StringRendererConfig config;
    private Fragment lastVisited;

    public AbstractFragmentRenderer(final StringRendererConfig config) {
        this.config = config;
        this.lastVisited = null;
    }

    // [impl->dsn~rendering.sql.configurable-case~1]
    protected void appendKeyWord(final String keyword) {
        append(this.config.useLowerCase() ? keyword.toLowerCase() : keyword);
    }

    protected void appendListOfColumnReferences(final List<ColumnReference> columnReferences) {
        if (columnReferences != null && !columnReferences.isEmpty()) {
            for (int i = 0; i < columnReferences.size() - 1; i++) {
                appendColumnReference(columnReferences.get(i), false);
            }
            appendColumnReference(columnReferences.get(columnReferences.size() - 1), true);
        }
    }

    protected void appendColumnReference(final ColumnReference columnReference, final boolean last) {
        final ValueExpressionRenderer valueExpressionRenderer = new ValueExpressionRenderer(config);
        columnReference.accept(valueExpressionRenderer);
        this.builder.append(valueExpressionRenderer.render());
        if (!last) {
            append(", ");
        }
    }

    protected void appendStringList(final List<String> strings) {
        for (int i = 0; i < strings.size() - 1; i++) {
            append(strings.get(i));
            append(", ");
        }
        append(strings.get(strings.size() - 1));
    }

    protected StringBuilder append(final String string) {
        return this.builder.append(string);
    }

    protected void setLastVisited(final Fragment fragment) {
        this.lastVisited = fragment;
    }

    protected void appendSpace() {
        append(" ");
    }

    protected void appendCommaWhenNeeded(final Fragment fragment) {
        if (this.lastVisited.getClass().equals(fragment.getClass())) {
            append(", ");
        }
    }

    protected void appendRenderedBooleanExpression(final BooleanExpression expression) {
        final BooleanExpressionRenderer expressionRenderer = new BooleanExpressionRenderer();
        expression.accept(expressionRenderer);
        append(expressionRenderer.render());
    }

    protected void append(final int number) {
        this.builder.append(number);
    }

    protected void appendRenderedValueExpression(final ValueExpression expression) {
        final ValueExpressionRenderer renderer = new ValueExpressionRenderer(this.config);
        expression.accept(renderer);
        append(renderer.render());
    }

    // [impl->dsn~rendering.add-double-quotes-for-schema-table-and-column-identifiers~1]
    protected void appendAutoQuoted(final String identifier) {
        if (this.config.useQuotes()) {
            appendQuoted(identifier);
        } else {
            append(identifier);
        }
    }

    private void appendQuoted(final String identifier) {
        boolean first = true;
        for (final String part : identifier.split("\\.")) {
            if (!first) {
                append(".");
            }
            quoteIdentifierPart(part);
            first = false;
        }
    }

    private void quoteIdentifierPart(final String part) {
        if ("*".equals(part)) {
            append("*");
        } else {
            if (!part.startsWith("\"")) {
                append("\"");
            }
            append(part);
            if (!part.endsWith("\"")) {
                append("\"");
            }
        }
    }

    protected void appendValueTableRow(final ValueTableRow valueTableRow) {
        boolean first = true;
        for (final ValueExpression expression : valueTableRow.getExpressions()) {
            if (first) {
                first = false;
            } else {
                append(", ");
            }
            appendRenderedValueExpression(expression);
        }
    }

    @Override
    public String render() {
        return this.builder.toString();
    }
}