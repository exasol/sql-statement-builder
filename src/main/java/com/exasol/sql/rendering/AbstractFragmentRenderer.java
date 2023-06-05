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
    /** Configuration that controls string rendering options. */
    protected final StringRendererConfig config;
    private final StringBuilder builder = new StringBuilder();
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

    /**
     * Append an unquoted SQL keyword.
     *
     * @param keyword SQL keyword
     */
    // [impl->dsn~rendering.sql.configurable-case~1]
    protected void appendKeyWord(final String keyword) {
        append(this.config.useLowerCase() ? keyword.toLowerCase() : keyword);
    }

    /**
     * Start a parenthesis.
     */
    protected void startParenthesis() {
        this.builder.append("(");
    }

    /**
     * End a parenthesis.
     */
    protected void endParenthesis() {
        this.builder.append(")");
    }

    /**
     * Append a value expression that has already been rendered.
     *
     * @param expression pre-rendered expression
     */
    protected void appendRenderedValueExpression(final ValueExpression expression) {
        final ValueExpressionRenderer renderer = new ValueExpressionRenderer(this.config);
        expression.accept(renderer);
        append(renderer.render());
    }

    /**
     * Append a list of value expressions.
     *
     * @param valueExpressions value expressions
     */
    protected void appendListOfValueExpressions(final List<? extends ValueExpression> valueExpressions) {
        if ((valueExpressions != null) && !valueExpressions.isEmpty()) {
            final ValueExpressionRenderer valueExpressionRenderer = new ValueExpressionRenderer(this.config);
            valueExpressionRenderer.visit(valueExpressions.toArray(ValueExpression[]::new));
            this.builder.append(valueExpressionRenderer.render());
        }
    }

    /**
     * Append a string.
     *
     * @param string string to append
     *
     * @return string builder
     */
    protected StringBuilder append(final String string) {
        return this.builder.append(string);
    }

    /**
     * Set the last statement fragment that was visited.
     *
     * @param fragment last visited fragment
     */
    protected void setLastVisited(final Fragment fragment) {
        this.lastVisited = fragment;
    }

    /**
     * Append a white space.
     */
    protected void appendSpace() {
        append(" ");
    }

    /**
     * Append a comma where necessary.
     *
     * @param fragment fragment that might need a comma to separate it from the previous one
     */
    protected void appendCommaWhenNeeded(final Fragment fragment) {
        if ((this.lastVisited != null) && this.lastVisited.getClass().equals(fragment.getClass())) {
            append(", ");
        }
    }

    /**
     * Append on integer number.
     *
     * @param number number to append
     */
    protected void append(final int number) {
        this.builder.append(number);
    }

    /**
     * Append an auto-quoted SQL identifier.
     *
     * @param identifier SQL identifier to append
     */
    protected void appendAutoQuoted(final String identifier) {
        final String autoQuotedIdentifier = this.quotesApplier.getAutoQuoted(identifier);
        append(autoQuotedIdentifier);
    }

    /**
     * Append a row of a value table ({@code SELECT ... FROM VALUES}).
     *
     * @param valueTableRow row of a value table
     */
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