package com.exasol.sql.expression.rendering;

import static com.exasol.hamcrest.BooleanExpressionRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.BooleanExpressionRenderResultMatcher.rendersWithConfigTo;
import static com.exasol.sql.expression.BooleanTerm.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.expression.BooleanExpression;
import com.exasol.sql.rendering.StringRendererConfig;

class TestBooleanExpressionRenderer {
    @Test
    void testUnaryNotWithLiteral() {
        final BooleanExpression expression = not("a");
        assertThat(expression, rendersTo("NOT(a)"));
    }

    @Test
    void testUnaryNotWithExpression() {
        final BooleanExpression expression = not(not("a"));
        assertThat(expression, rendersTo("NOT(NOT(a))"));
    }

    @Test
    void testAndWithLiterals() {
        final BooleanExpression expression = and("a", "b", "c");
        assertThat(expression, rendersTo("a AND b AND c"));
    }

    @Test
    void testAndWithLeftLiteralAndRightExpression() {
        final BooleanExpression expression = and("a", not("b"));
        assertThat(expression, rendersTo("a AND NOT(b)"));
    }

    @Test
    void testAndWithLeftExpressionAndRightLiteral() {
        final BooleanExpression expression = and(not("a"), "b");
        assertThat(expression, rendersTo("NOT(a) AND b"));
    }

    @Test
    void testOrWithLiterals() {
        final BooleanExpression expression = or("a", "b", "c");
        assertThat(expression, rendersTo("a OR b OR c"));
    }

    @Test
    void testoRWithLeftLiteralAndRightExpression() {
        final BooleanExpression expression = or("a", not("b"));
        assertThat(expression, rendersTo("a OR NOT(b)"));
    }

    @Test
    void testOrWithLeftExpressionAndRightLiteral() {
        final BooleanExpression expression = or(not("a"), "b");
        assertThat(expression, rendersTo("NOT(a) OR b"));
    }

    @Test
    void testOrWhitNestedAnd() {
        final BooleanExpression expression = or(and(not("a"), "b"), and("c", "d"));
        assertThat(expression, rendersTo("(NOT(a) AND b) OR (c AND d)"));
    }

    @Test
    void testAndWhitNestedOr() {
        final BooleanExpression expression = and(or(not("a"), "b"), or("c", "d"));
        assertThat(expression, rendersTo("(NOT(a) OR b) AND (c OR d)"));
    }

    @Test
    void testAndWhitNestedOrInLowercase() {
        final BooleanExpression expression = and(or(not("a"), "b"), or("c", "d"));
        final StringRendererConfig config = new StringRendererConfig.Builder().lowerCase(true).build();
        assertThat(expression, rendersWithConfigTo(config, "(not(a) or b) and (c or d)"));
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparison() {
        final BooleanExpression expression = eq("a", "b");
        assertThat(expression, rendersTo("a = b"));
    }
}