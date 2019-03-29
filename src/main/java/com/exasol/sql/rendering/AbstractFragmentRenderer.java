package com.exasol.sql.rendering;

import com.exasol.sql.Fragment;
import com.exasol.sql.dql.ValueTableRow;
import com.exasol.sql.expression.BooleanExpression;
import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.rendering.BooleanExpressionRenderer;
import com.exasol.sql.expression.rendering.ValueExpressionRenderer;

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

    protected void appendRenderedExpression(final BooleanExpression expression) {
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
            quoteIdentiferPart(part);
            first = false;
        }
    }

    private void quoteIdentiferPart(final String part) {
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