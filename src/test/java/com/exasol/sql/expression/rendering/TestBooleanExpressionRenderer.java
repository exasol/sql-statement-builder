package com.exasol.sql.expression.rendering;

import static com.exasol.hamcrest.BooleanExpressionRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.BooleanExpressionRenderResultMatcher.rendersWithConfigTo;
import static com.exasol.sql.expression.BooleanTerm.*;
import static com.exasol.sql.expression.ExpressionTerm.*;
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
        final BooleanExpression expression = and(
                compare(stringLiteral("a"), ComparisonOperator.EQUAL, stringLiteral("b")),
                compare(stringLiteral("c"), ComparisonOperator.NOT_EQUAL, stringLiteral("d")));
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
        final BooleanExpression expression = compare(stringLiteral("a"), ">=", stringLiteral("b"));
        assertThat(expression, rendersTo("'a' >= 'b'"));
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonOperatorsWithStringLiteral() {
        assertAll( //
                () -> assertThat("equal", eq(stringLiteral("a"), stringLiteral("b")), rendersTo("'a' = 'b'")), //
                () -> assertThat("not equal", ne(stringLiteral("a"), stringLiteral("b")), rendersTo("'a' <> 'b'")), //
                () -> assertThat("not equal", lt(stringLiteral("a"), stringLiteral("b")), rendersTo("'a' < 'b'")), //
                () -> assertThat("not equal", gt(stringLiteral("a"), stringLiteral("b")), rendersTo("'a' > 'b'")), //
                () -> assertThat("not equal", le(stringLiteral("a"), stringLiteral("b")), rendersTo("'a' <= 'b'")), //
                () -> assertThat("not equal", ge(stringLiteral("a"), stringLiteral("b")), rendersTo("'a' >= 'b'")) //
        );
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonOperatorsWithIntegerLiteral() {
        assertAll( //
                () -> assertThat("equal", eq(integerLiteral(1), integerLiteral(1)), rendersTo("1 = 1")), //
                () -> assertThat("not equal", ne(integerLiteral(1), integerLiteral(2)), rendersTo("1 <> 2")), //
                () -> assertThat("not equal", lt(integerLiteral(1), integerLiteral(2)), rendersTo("1 < 2")), //
                () -> assertThat("not equal", gt(integerLiteral(2), integerLiteral(1)), rendersTo("2 > 1")), //
                () -> assertThat("not equal", le(integerLiteral(1), integerLiteral(2)), rendersTo("1 <= 2")), //
                () -> assertThat("not equal", ge(integerLiteral(2), integerLiteral(1)), rendersTo("2 >= 1")) //
        );
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonOperatorsWithLongLiteral() {
        assertAll( //
                () -> assertThat("equal", eq(longLiteral(1L), longLiteral(1L)), rendersTo("1 = 1")), //
                () -> assertThat("not equal", ne(longLiteral(1L), longLiteral(2L)), rendersTo("1 <> 2")), //
                () -> assertThat("not equal", lt(longLiteral(1L), longLiteral(2L)), rendersTo("1 < 2")), //
                () -> assertThat("not equal", gt(longLiteral(2L), longLiteral(1L)), rendersTo("2 > 1")), //
                () -> assertThat("not equal", le(longLiteral(1L), longLiteral(2L)), rendersTo("1 <= 2")), //
                () -> assertThat("not equal", ge(longLiteral(2L), longLiteral(1L)), rendersTo("2 >= 1")) //
        );
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonOperatorsWithDoubleLiteral() {
        assertAll( //
                () -> assertThat("equal", eq(doubleLiteral(1.1), doubleLiteral(1.1)), rendersTo("1.1 = 1.1")), //
                () -> assertThat("not equal", ne(doubleLiteral(1.1), doubleLiteral(1.2)), rendersTo("1.1 <> 1.2")), //
                () -> assertThat("not equal", lt(doubleLiteral(1.1), doubleLiteral(1.2)), rendersTo("1.1 < 1.2")), //
                () -> assertThat("not equal", gt(doubleLiteral(1.2), doubleLiteral(1.1)), rendersTo("1.2 > 1.1")), //
                () -> assertThat("not equal", le(doubleLiteral(1.1), doubleLiteral(1.2)), rendersTo("1.1 <= 1.2")), //
                () -> assertThat("not equal", ge(doubleLiteral(1.2), doubleLiteral(1.1)), rendersTo("1.2 >= 1.1")) //
        );
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonOperatorsWithDoubleLiteralCreatedFromFloat() {
        assertAll( //
                () -> assertThat("equal", eq(floatLiteral(1.1f), floatLiteral(1.1f)), rendersTo("1.1 = 1.1")), //
                () -> assertThat("not equal", ne(floatLiteral(1.1f), floatLiteral(1.2f)), rendersTo("1.1 <> 1.2")), //
                () -> assertThat("not equal", lt(floatLiteral(1.1f), floatLiteral(1.2f)), rendersTo("1.1 < 1.2")), //
                () -> assertThat("not equal", gt(floatLiteral(1.2f), floatLiteral(1.1f)), rendersTo("1.2 > 1.1")), //
                () -> assertThat("not equal", le(floatLiteral(1.1f), floatLiteral(1.2f)), rendersTo("1.1 <= 1.2")), //
                () -> assertThat("not equal", ge(floatLiteral(1.2f), floatLiteral(1.1f)), rendersTo("1.2 >= 1.1")) //
        );
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonOperatorsWithBooleanLiteral() {
        assertAll( //
                () -> assertThat("equal", eq(booleanLiteral(true), booleanLiteral(true)), rendersTo("TRUE = TRUE")), //
                () -> assertThat("not equal", ne(booleanLiteral(true), booleanLiteral(false)),
                        rendersTo("TRUE <> FALSE")));
    }

    // [utest->dsn~comparison-operations~1]
    @Test
    void testComparisonOperatorsWithColumnReference() {
        assertAll( //
                () -> assertThat("equal", eq(column("city"), integerLiteral(1)), rendersTo("city = 1")), //
                () -> assertThat("not equal", ne(column("city"), integerLiteral(2)), rendersTo("city <> 2")), //
                () -> assertThat("not equal", lt(column("city"), stringLiteral("Moscow")),
                        rendersTo("city < 'Moscow'")), //
                () -> assertThat("not equal", gt(column("t", "city"), stringLiteral("Moscow")),
                        rendersTo("t.city > 'Moscow'")), //
                () -> assertThat("not equal", le(column("t", "city"), column("machi")), rendersTo("t.city <= machi")), //
                () -> assertThat("not equal", ge(column("t", "city"), column("t", "machi")),
                        rendersTo("t.city >= t.machi")) //
        );
    }
}