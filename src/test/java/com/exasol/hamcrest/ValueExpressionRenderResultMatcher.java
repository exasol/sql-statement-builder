package com.exasol.hamcrest;

import org.hamcrest.Description;

import com.exasol.sql.dql.select.rendering.SelectRenderer;
import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.rendering.ValueExpressionRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * This class implements a matcher for the results of rendering boolean expressions to text.
 */
public class ValueExpressionRenderResultMatcher extends AbstractRenderResultMatcher<ValueExpression> {
    private final ValueExpressionRenderer renderer;

    private ValueExpressionRenderResultMatcher(final String expectedText) {
        super(expectedText);
        this.renderer = new ValueExpressionRenderer(StringRendererConfig.createDefault());
    }

    private ValueExpressionRenderResultMatcher(final StringRendererConfig config, final String expectedText) {
        super(expectedText);
        this.renderer = new ValueExpressionRenderer(config);
    }

    /**
     * Factory method for {@link ValueExpressionRenderResultMatcher}
     *
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static ValueExpressionRenderResultMatcher rendersTo(final String expectedText) {
        return new ValueExpressionRenderResultMatcher(expectedText);
    }

    /**
     * Factory method for {@link ValueExpressionRenderResultMatcher}
     *
     * @param config       configuration settings for the {@link SelectRenderer}
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static ValueExpressionRenderResultMatcher rendersWithConfigTo(final StringRendererConfig config,
            final String expectedText) {
        return new ValueExpressionRenderResultMatcher(config, expectedText);
    }

    /**
     * Match the rendered result against original text.
     *
     * @param expression expression to be matched against the original text.
     */
    @Override
    public boolean matchesSafely(final ValueExpression expression) {
        expression.accept(this.renderer);
        this.renderedText = this.renderer.render();
        return this.renderedText.equals(this.expectedText);
    }

    @Override
    protected void describeMismatchSafely(final ValueExpression expression, final Description mismatchDescription) {
        mismatchDescription.appendText(this.renderedText);
    }
}