package com.exasol.hamcrest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringJoiner;

import org.hamcrest.Description;

import com.exasol.sql.Fragment;
import com.exasol.sql.dql.select.rendering.SelectRenderer;
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
     * Factory method for {@link SqlFragmentRenderResultMatcher}
     *
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static SqlFragmentRenderResultMatcher rendersTo(final String expectedText) {
        return new SqlFragmentRenderResultMatcher(expectedText);
    }

    /**
     * Factory method for {@link SqlFragmentRenderResultMatcher}.
     *
     * @param config configuration settings for the {@link SelectRenderer}
     * @param expectedText text that represents the expected rendering result
     * @return the matcher
     */
    public static SqlFragmentRenderResultMatcher rendersWithConfigTo(final StringRendererConfig config,
            final String expectedText) {
        return new SqlFragmentRenderResultMatcher(config, expectedText);
    }

    /**
     * Match the rendered result against original text.
     *
     * @param fragment fragment to be matched against the original text.
     */
    @Override
    public boolean matchesSafely(final Fragment fragment) {
        final Fragment root = fragment.getRoot();
        final String rootClassName = root.getClass().getName();
        final String rendererClassPath = getRendererClassPath(rootClassName) + "Renderer";
        try {
            final Class<?> fragmentClass = Class.forName(rootClassName + "Fragment");
            final Class<?> visitorClass = Class.forName(rootClassName + "Visitor");
            final Method acceptMethod = fragmentClass.getMethod("accept", visitorClass);
            final Class<?> rendererClass = Class.forName(rendererClassPath);
            final Object rendererObject = rendererClass.getConstructor(StringRendererConfig.class)
                    .newInstance(this.config);
            acceptMethod.invoke(root, rendererObject);
            final Method renderMethod = rendererClass.getMethod("render");
            this.renderedText = (String) renderMethod.invoke(rendererObject);
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException cause) {
            throw new UnsupportedOperationException(
                    "Don't know how to render fragment of type\"" + rootClassName + "\".", cause);
        }
        return this.renderedText.equals(this.expectedText);
    }

    private String getRendererClassPath(final String oldClassPath) {
        final String[] pathElements = oldClassPath.split("\\.");
        final String[] result = new String[pathElements.length + 1];
        System.arraycopy(pathElements, 0, result, 0, pathElements.length);
        result[pathElements.length] = result[pathElements.length - 1];
        result[pathElements.length - 1] = "rendering";
        final StringJoiner joiner = new StringJoiner(".");
        for (final String string : result) {
            joiner.add(string);
        }
        return joiner.toString();
    }

    @Override
    protected void describeMismatchSafely(final Fragment fragment, final Description mismatchDescription) {
        mismatchDescription.appendText(this.renderedText);
    }
}
