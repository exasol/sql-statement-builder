package com.exasol.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.function.Executable;

/**
 * Helper assertion methods for verifying exceptions.
 */
public class ExceptionAssertions {
    private ExceptionAssertions() {
        // not instantiable
    }

    /**
     * Verify that the given executable throws an exception with the expected message.
     *
     * @param expectedExceptionType expected type of the thrown exception
     * @param executable            executable that is expected to throw an exception
     * @param messageMatcher        hamcrest matcher for the exception message
     */
    public static void assertThrowsWithMessage(final Class<? extends Throwable> expectedExceptionType,
            final Executable executable, final Matcher<String> messageMatcher) {
        final Throwable exception = assertThrows(expectedExceptionType, executable);
        assertThat("wrong exception message", exception.getMessage(), messageMatcher);
    }

    /**
     * Verify that the given executable throws an exception with the expected message.
     *
     * @param expectedExceptionType expected type of the thrown exception
     * @param executable            executable that is expected to throw an exception
     * @param expectedMessage       exception message
     */
    public static void assertThrowsWithMessage(final Class<? extends Throwable> expectedExceptionType,
            final Executable executable, final String expectedMessage) {
        assertThrowsWithMessage(expectedExceptionType, executable, equalTo(expectedMessage));
    }
}
