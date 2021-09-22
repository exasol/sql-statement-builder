package com.exasol.sql.rendering;

import java.util.List;

import com.exasol.sql.Fragment;
import com.exasol.sql.ValueTableRow;
import com.exasol.sql.expression.ValueExpression;
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
    protected AbstractFragmentRenderer(final StringRendererConfig config) {
        this.config = config;
        this.lastVisited = null;
        this.quotesApplier = new QuotesApplier(config);
    }

    // [impl->dsn~rendering.sql.configurable-case~1]
    protected void appendKeyWord(final String keyword) {
        append(this.config.useLowerCase() ? keyword.toLowerCase() : keyword);
    }

    protected void startParenthesis() {
        this.builder.append("(");
    }

    protected void endParenthesis() {
        this.builder.append(")");
    }

    protected void appendRenderedValueExpression(final ValueExpression expression) {
        final ValueExpressionRenderer renderer = new ValueExpressionRenderer(this.config);
        expression.accept(renderer);
        append(renderer.render());
    }

    protected void appendListOfValueExpressions(final List<? extends ValueExpression> valueExpressions) {
        if ((valueExpressions != null) && !valueExpressions.isEmpty()) {
            final ValueExpressionRenderer valueExpressionRenderer = new ValueExpressionRenderer(this.config);
            valueExpressionRenderer.visit(valueExpressions.toArray(ValueExpression[]::new));
            this.builder.append(valueExpressionRenderer.render());
        }
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
        if ((this.lastVisited != null) && this.lastVisited.getClass().equals(fragment.getClass())) {
            append(", ");
        }
    }

    protected void append(final int number) {
        this.builder.append(number);
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