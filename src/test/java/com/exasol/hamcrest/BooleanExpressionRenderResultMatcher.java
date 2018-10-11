package com.exasol.hamcrest;

import org.hamcrest.Description;

import com.exasol.sql.dql.rendering.SelectRenderer;
import com.exasol.sql.expression.BooleanExpression;
import com.exasol.sql.expression.rendering.BooleanExpressionRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * This class implements a matcher for the results of rendering boolean
 * expressions to text.
 */
public class BooleanExpressionRenderResultMatcher extends AbstractRenderResultMatcher<BooleanExpression> {
    private final BooleanExpressionRenderer renderer;

    private BooleanExpressionRenderResultMatcher(final String expectedText) {
        super(expectedText);
        this.renderer = new BooleanExpressionRenderer();
    }

    private BooleanExpressionRenderResultMatcher(final StringRendererConfig config, final String expectedText) {
        super(expectedText);
        this.renderer = new BooleanExpressionRenderer(config);
    }

    /**
     * Match the rendered result against original text.
     *
     * @param text the text to be matched against the original text.
     */
    @Override
    public boolean matchesSafely(final BooleanExpression expression) {
        expression.accept(this.renderer);
        this.renderedText = this.renderer.render();
        return this.renderedText.equals(this.expectedText);
    }

    @Override
    protected void describeMismatchSafely(final BooleanExpression expression, final Description mismatchDescription) {
        mismatchDescription.appendText(this.renderedText);
    }

    /**
     * Factory method for {@link BooleanExpressionRenderResultMatcher}
     *
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static BooleanExpressionRenderResultMatcher rendersTo(final String expectedText) {
        return new BooleanExpressionRenderResultMatcher(expectedText);
    }

    /**
     * Factory method for {@link BooleanExpressionRenderResultMatcher}
     *
     * @param config       configuration settings for the
     *                     {@link SelectRenderer}
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static BooleanExpressionRenderResultMatcher rendersWithConfigTo(final StringRendererConfig config,
            final String expectedText) {
        return new BooleanExpressionRenderResultMatcher(config, expectedText);
    }
}