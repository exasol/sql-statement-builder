package com.exasol.sql.expression.rendering;

import static com.exasol.hamcrest.BooleanExpressionRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.BooleanTerm.and;
import static com.exasol.sql.expression.BooleanTerm.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.expression.BooleanExpression;

class TestBooleanExpressionRenderer {
    @Test
    void testUnaryNotWithLiteral() {
        final BooleanExpression expression = not("foo");
        assertThat(expression, rendersTo("NOT foo"));
    }

    @Test
    void testUnaryNotWithExpression() {
        final BooleanExpression expression = not(not("foo"));
        assertThat(expression, rendersTo("NOT(NOT foo)"));
    }

    @Test
    void testAndWithLiterals() {
        final BooleanExpression expression = and("a", "b", "c");
        assertThat(expression, rendersTo("a AND b AND c"));
    }
}