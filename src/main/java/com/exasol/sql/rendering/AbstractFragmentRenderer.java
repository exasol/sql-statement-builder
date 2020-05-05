package com.exasol.sql.rendering;

import java.util.List;

import com.exasol.sql.Fragment;
import com.exasol.sql.ValueTableRow;
import com.exasol.sql.expression.*;
import com.exasol.sql.expression.rendering.BooleanExpressionRenderer;
import com.exasol.sql.expression.rendering.ValueExpressionRenderer;
import com.exasol.util.QuotesApplier;

/**
 * Abstract base class for SQL fragment renderers.
 */
public abstract class AbstractFragmentRenderer implements FragmentRenderer {
    private final StringBuilder builder = new StringBuilder();
    protected final StringRendererConfig config;
    private Fragment lastVisited;
    private final QuotesApplier quotesApplier;

    /**
     * Create a new instance of an {@link AbstractFragmentRenderer}-based class.
     * 
     * @param config renderer configuration
     */
    public AbstractFragmentRenderer(final StringRendererConfig config) {
        this.config = config;
        this.lastVisited = null;
        this.quotesApplier = new QuotesApplier(config);
    }

    // [impl->dsn~rendering.sql.configurable-case~1]
    protected void appendKeyWord(final String keyword) {
        append(this.config.useLowerCase() ? keyword.toLowerCase() : keyword);
    }

    protected void appendListOfColumnReferences(final List<ColumnReference> columnReferences) {
        if ((columnReferences != null) && !columnReferences.isEmpty()) {
            for (int i = 0; i < (columnReferences.size() - 1); i++) {
                appendColumnReference(columnReferences.get(i), false);
            }
            appendColumnReference(columnReferences.get(columnReferences.size() - 1), true);
        }
    }

    protected void appendColumnReference(final ColumnReference columnReference, final boolean last) {
        final ValueExpressionRenderer valueExpressionRenderer = new ValueExpressionRenderer(this.config);
        columnReference.accept(valueExpressionRenderer);
        this.builder.append(valueExpressionRenderer.render());
        if (!last) {
            append(", ");
        }
    }

    protected void appendStringList(final List<String> strings) {
        for (int i = 0; i < (strings.size() - 1); i++) {
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

    protected void appendAutoQuoted(final String identifier) {
        final String autoQuotedIdentifier = this.quotesApplier.getAutoQuoted(identifier);
        append(autoQuotedIdentifier);
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