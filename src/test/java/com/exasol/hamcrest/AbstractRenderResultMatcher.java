package com.exasol.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public abstract class AbstractRenderResultMatcher<T> extends TypeSafeMatcher<T> {
    protected final String expectedText;
    protected String renderedText = null;

    public AbstractRenderResultMatcher(final String expectedText) {
        this.expectedText = expectedText;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText(this.expectedText);
    }

}