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
        final BooleanExpression expression = not(true);
        assertThat(expression, rendersTo("NOT(TRUE)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testUnaryNotWithExpression() {
        final BooleanExpression expression = not(not(false));
        assertThat(expression, rendersTo("NOT(NOT(FALSE))"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndWithLiterals() {
        final BooleanExpression expression = and(true, false, true);
        assertThat(expression, rendersTo("TRUE AND FALSE AND TRUE"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndNestedComparisons() {
        final BooleanExpression expression = and(compare("a", ComparisonOperator.EQUAL, "b"),
                compare("c", ComparisonOperator.NOT_EQUAL, "d"));
        assertThat(expression, rendersTo("('a' = 'b') AND ('c' <> 'd')"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndWithLeftLiteralAndRightExpression() {
        final BooleanExpression expression = and(true, not(false));
        assertThat(expression, rendersTo("TRUE AND NOT(FALSE)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndWithLeftExpressionAndRightLiteral() {
        final BooleanExpression expression = and(not(true), false);
        assertThat(expression, rendersTo("NOT(TRUE) AND FALSE"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOrWithLiterals() {
        final BooleanExpression expression = or(false, true, false);
        assertThat(expression, rendersTo("FALSE OR TRUE OR FALSE"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testoRWithLeftLiteralAndRightExpression() {
        final BooleanExpression expression = or(false, not(true));
        assertThat(expression, rendersTo("FALSE OR NOT(TRUE)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOrWithLeftExpressionAndRightLiteral() {
        final BooleanExpression expression = or(not(true), false);
        assertThat(expression, rendersTo("NOT(TRUE) OR FALSE"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOrWhitNestedAnd() {
        final BooleanExpression expression = or(and(not(true), false), and(false, true));
        assertThat(expression, rendersTo("(NOT(TRUE) AND FALSE) OR (FALSE AND TRUE)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndWhitNestedOr() {
        final BooleanExpression expression = and(or(not(true), false), or(false, true));
        assertThat(expression, rendersTo("(NOT(TRUE) OR FALSE) AND (FALSE OR TRUE)"));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testAndWhitNestedOrInLowercase() {
        final BooleanExpression expression = and(or(not(false), true), or(true, false));
        final StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
        assertThat(expression, rendersWithConfigTo(config, "(not(false) or true) and (true or false)"));
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonFromSymbol() {
        final BooleanExpression expression = compare("a", ">=", "b");
        assertThat(expression, rendersTo("'a' >= 'b'"));
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonOperators() {
        assertAll( //
                () -> assertThat("equal", eq("a", "b"), rendersTo("'a' = 'b'")), //
                () -> assertThat("not equal", ne("a", "b"), rendersTo("'a' <> 'b'")), //
                () -> assertThat("not equal", lt("a", "b"), rendersTo("'a' < 'b'")), //
                () -> assertThat("not equal", gt("a", "b"), rendersTo("'a' > 'b'")), //
                () -> assertThat("not equal", le("a", "b"), rendersTo("'a' <= 'b'")), //
                () -> assertThat("not equal", ge("a", "b"), rendersTo("'a' >= 'b'")) //
        );
    }
}