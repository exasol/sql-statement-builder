package com.exasol.hamcrest;

import org.hamcrest.Description;

import com.exasol.sql.Fragment;
import com.exasol.sql.rendering.SqlStatementRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * This class implements a matcher for the results of rendering SQL statements
 * to text.
 */
public class SqlFragmentRenderResultMatcher extends AbstractRenderResultMatcher<Fragment> {
    private final SqlStatementRenderer renderer;

    private SqlFragmentRenderResultMatcher(final String expectedText) {
        super(expectedText);
        this.renderer = new SqlStatementRenderer();
    }

    private SqlFragmentRenderResultMatcher(final StringRendererConfig config, final String expectedText) {
        super(expectedText);
        this.renderer = new SqlStatementRenderer(config);
    }

    /**
     * Match the rendered result against original text.
     *
     * @param text the text to be matched against the original text.
     */
    @Override
    public boolean matchesSafely(final Fragment fragment) {
        ((Fragment) fragment.getRoot()).accept(this.renderer);
        this.renderedText = this.renderer.render();
        return this.renderedText.equals(this.expectedText);
    }

    @Override
    protected void describeMismatchSafely(final Fragment fragment, final Description mismatchDescription) {
        mismatchDescription.appendText(this.renderedText);
    }

    /**
     * Factory method for {@link SqlFragmentRenderResultMatcher}
     *
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static SqlFragmentRenderResultMatcher rendersTo(final String expectedText) {
        return new SqlFragmentRenderResultMatcher(expectedText);
    }

    /**
     * Factory method for {@link SqlFragmentRenderResultMatcher}
     *
     * @param config       configuration settings for the
     *                     {@link SqlStatementRenderer}
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static SqlFragmentRenderResultMatcher rendersWithConfigTo(final StringRendererConfig config,
            final String expectedText) {
        return new SqlFragmentRenderResultMatcher(config, expectedText);
    }
}