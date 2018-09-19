package com.exasol.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.exasol.sql.Fragment;
import com.exasol.sql.dql.StringRendererConfig;
import com.exasol.sql.rendering.StringRenderer;

/**
 * This class implements a matcher for multi-line text that helps finding the
 * differences more quickly.
 */
public class RenderResultMatcher extends TypeSafeMatcher<Fragment> {
    private final String expectedText;
    private final StringRenderer renderer;
    private String renderedText = null;

    private RenderResultMatcher(final String expectedText) {
        this.expectedText = expectedText;
        this.renderer = new StringRenderer();
    }

    private RenderResultMatcher(final StringRendererConfig config, final String expectedText) {
        this.expectedText = expectedText;
        this.renderer = new StringRenderer(config);
    }

    /**
     * Match the rendered result against original text.
     *
     * @param text the text to be matched against the original text.
     */
    @Override
    public boolean matchesSafely(final Fragment fragment) {
        fragment.getRoot().accept(this.renderer);
        this.renderedText = this.renderer.render();
        return this.renderedText.equals(this.expectedText);
    }

    @Override
    protected void describeMismatchSafely(final Fragment fragment, final Description mismatchDescription) {
        mismatchDescription.appendText(this.renderedText);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText(this.expectedText);
    }

    /**
     * Factory method for {@link RenderResultMatcher}
     *
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static RenderResultMatcher rendersTo(final String expectedText) {
        return new RenderResultMatcher(expectedText);
    }

    /**
     * Factory method for {@link RenderResultMatcher}
     *
     * @param config       configuration settings for the {@link StringRenderer}
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static RenderResultMatcher rendersWithConfigTo(final StringRendererConfig config,
            final String expectedText) {
        return new RenderResultMatcher(config, expectedText);
    }
}