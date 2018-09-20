package com.exasol.sql.expression;

import static com.exasol.hamcrest.RenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class TestBooleanExpression {
    @Test
    void testUnaryNot() {
        final BooleanExpression expression = BooleanExpression.not("foo");
        assertThat(expression, rendersTo("NOT foo"));
    }
}
