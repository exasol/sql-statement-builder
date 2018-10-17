package com.exasol.sql.expression.rendering;

import static com.exasol.hamcrest.BooleanExpressionRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.BooleanExpressionRenderResultMatcher.rendersWithConfigTo;
import static com.exasol.sql.expression.BooleanTerm.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import com.exasol.sql.expression.BooleanExpression;
import com.exasol.sql.expression.ComparisonOperator;
import com.exasol.sql.rendering.StringRendererConfig;

class TestBooleanExpressionRenderer {
    // [utest->dsn~boolean-operators~1]
    @Test
    void testUnaryNotWithLiteral() {
        final BooleanExpression expression = not("a");
        assertThat(expression, rendersTo("NOT(a)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testUnaryNotWithExpression() {
        final BooleanExpression expression = not(not("a"));
        assertThat(expression, rendersTo("NOT(NOT(a))"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndWithLiterals() {
        final BooleanExpression expression = and("a", "b", "c");
        assertThat(expression, rendersTo("a AND b AND c"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndNestedComparisons() {
        final BooleanExpression expression = and(compare("a", ComparisonOperator.EQUAL, "b"),
                compare("c", ComparisonOperator.NOT_EQUAL, "d"));
        assertThat(expression, rendersTo("(a = b) AND (c <> d)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndWithLeftLiteralAndRightExpression() {
        final BooleanExpression expression = and("a", not("b"));
        assertThat(expression, rendersTo("a AND NOT(b)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndWithLeftExpressionAndRightLiteral() {
        final BooleanExpression expression = and(not("a"), "b");
        assertThat(expression, rendersTo("NOT(a) AND b"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOrWithLiterals() {
        final BooleanExpression expression = or("a", "b", "c");
        assertThat(expression, rendersTo("a OR b OR c"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testoRWithLeftLiteralAndRightExpression() {
        final BooleanExpression expression = or("a", not("b"));
        assertThat(expression, rendersTo("a OR NOT(b)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOrWithLeftExpressionAndRightLiteral() {
        final BooleanExpression expression = or(not("a"), "b");
        assertThat(expression, rendersTo("NOT(a) OR b"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOrWhitNestedAnd() {
        final BooleanExpression expression = or(and(not("a"), "b"), and("c", "d"));
        assertThat(expression, rendersTo("(NOT(a) AND b) OR (c AND d)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndWhitNestedOr() {
        final BooleanExpression expression = and(or(not("a"), "b"), or("c", "d"));
        assertThat(expression, rendersTo("(NOT(a) OR b) AND (c OR d)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndWhitNestedOrInLowercase() {
        final BooleanExpression expression = and(or(not("a"), "b"), or("c", "d"));
        final StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
        assertThat(expression, rendersWithConfigTo(config, "(not(a) or b) and (c or d)"));
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonFromSymbol() {
        final BooleanExpression expression = compare("a", ">=", "b");
        assertThat(expression, rendersTo("a >= b"));
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonOperators() {
        assertAll( //
                () -> assertThat("equal", eq("a", "b"), rendersTo("a = b")), //
                () -> assertThat("not equal", ne("a", "b"), rendersTo("a <> b")), //
                () -> assertThat("not equal", lt("a", "b"), rendersTo("a < b")), //
                () -> assertThat("not equal", gt("a", "b"), rendersTo("a > b")), //
                () -> assertThat("not equal", le("a", "b"), rendersTo("a <= b")), //
                () -> assertThat("not equal", ge("a", "b"), rendersTo("a >= b")) //
        );
    }
}