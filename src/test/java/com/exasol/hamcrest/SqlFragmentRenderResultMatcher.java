package com.exasol.hamcrest;

import com.exasol.sql.ddl.create.CreateTableFragment;
import com.exasol.sql.ddl.rendering.CreateTableRenderer;
import org.hamcrest.Description;

import com.exasol.sql.Fragment;
import com.exasol.sql.dml.InsertFragment;
import com.exasol.sql.dml.rendering.InsertRenderer;
import com.exasol.sql.dql.SelectFragment;
import com.exasol.sql.dql.rendering.SelectRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * This class implements a matcher for the results of rendering SQL statements to text.
 */
public class SqlFragmentRenderResultMatcher extends AbstractRenderResultMatcher<Fragment> {

    private final StringRendererConfig config;

    private SqlFragmentRenderResultMatcher(final String expectedText) {
        super(expectedText);
        this.config = StringRendererConfig.createDefault();
    }

    private SqlFragmentRenderResultMatcher(final StringRendererConfig config, final String expectedText) {
        super(expectedText);
        this.config = config;
    }

    /**
     * Match the rendered result against original text.
     *
     * @param fragment fragment to be matched against the original text.
     */
    @Override
    public boolean matchesSafely(final Fragment fragment) {
        final Fragment root = fragment.getRoot();
        if (root instanceof SelectFragment) {
            final SelectRenderer renderer = new SelectRenderer(this.config);
            ((SelectFragment) root).accept(renderer);
            this.renderedText = renderer.render();
        } else if (root instanceof InsertFragment) {
            final InsertRenderer renderer = new InsertRenderer(this.config);
            ((InsertFragment) root).accept(renderer);
            this.renderedText = renderer.render();
        } else if (root instanceof CreateTableFragment) {
            final CreateTableRenderer renderer = new CreateTableRenderer(this.config);
            ((CreateTableFragment) root).accept(renderer);
            this.renderedText = renderer.render();
        } else {
            throw new UnsupportedOperationException(
                    "Don't know how to render fragment of type\"" + root.getClass().getName() + "\".");
        }
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
     * @param config configuration settings for the {@link SelectRenderer}
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static SqlFragmentRenderResultMatcher rendersWithConfigTo(final StringRendererConfig config,
            final String expectedText) {
        return new SqlFragmentRenderResultMatcher(config, expectedText);
    }
}