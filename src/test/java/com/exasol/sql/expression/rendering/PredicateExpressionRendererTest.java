package com.exasol.sql.expression.rendering;

import static com.exasol.hamcrest.ValueExpressionRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.ValueExpressionRenderResultMatcher.rendersWithConfigTo;
import static com.exasol.sql.expression.BooleanTerm.*;
import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.BooleanExpression;
import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.literal.Literal;
import com.exasol.sql.expression.predicate.InPredicate;
import com.exasol.sql.rendering.StringRendererConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// [utest->dsn~predicate-operators~1]
class PredicateExpressionRendererTest {
    private Select select;

    @BeforeEach
    void beforeEach() {
        select = StatementFactory.getInstance().select();
        select.from().table("test");
    }

    @Test
    void testIsNullPredicate() {
        assertThat(isNull(stringLiteral("e")), rendersTo("'e' IS NULL"));
    }

    @Test
    void testIsNotNullPredicate() {
        assertThat(isNotNull(stringLiteral("e")), rendersTo("'e' IS NOT NULL"));
    }

    @Test
    void testColumnIsNullPredicate() {
        assertThat(isNull(column("c")), rendersTo("c IS NULL"));
    }

    @Test
    void testExpressionIsNullPredicate() {
        final ValueExpression expr = plus(integerLiteral(1), integerLiteral(1));
        assertThat(isNull(expr), rendersTo("(1+1) IS NULL"));
    }

    @Test
    void testNestedIsNullPredicate() {
        final BooleanExpression expr = and(isNull(stringLiteral("a")), isNotNull(stringLiteral("b")));
        assertThat(expr, rendersTo("('a' IS NULL) AND ('b' IS NOT NULL)"));
    }

    @Test
    void testIsNullPredicateWithConfig() {
        final StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
        assertThat(isNotNull(not(true)), rendersWithConfigTo(config, "not(true) IS NOT NULL"));
    }

    @Test
    void testInPredicate() {
        final BooleanExpression inPredicate = in(stringLiteral("e"), integerLiteral(1), integerLiteral(2));
        assertThat(inPredicate, rendersTo("'e' IN (1, 2)"));
    }

    @Test
    void testNotInPredicate() {
        final BooleanExpression inPredicate = notIn(stringLiteral("e"), integerLiteral(3));
        assertThat(inPredicate, rendersTo("'e' NOT IN (3)"));
    }

    @Test
    void testNestedInPredicate() {
        final BooleanExpression expr = or(in(stringLiteral("a"), booleanLiteral(true), booleanLiteral(false)),
                notIn(stringLiteral("b"), integerLiteral(13)));
        assertThat(expr, rendersTo("('a' IN (TRUE, FALSE)) OR ('b' NOT IN (13))"));
    }

    @Test
    void testInPredicateWithSelect() {
        final BooleanExpression inPredicate = in(stringLiteral("e"), select.all().limit(2));
        assertThat(inPredicate, rendersTo("'e' IN (SELECT * FROM test LIMIT 2)"));
    }

    @Test
    void testNotInPredicateWithSelect() {
        final BooleanExpression inPredicate = notIn(integerLiteral(5), select.field("id"));
        assertThat(inPredicate, rendersTo("5 NOT IN (SELECT id FROM test)"));
    }

    @Test
    void testInPredicateBothExpressionAndSelectException() {
        final InPredicate.Builder builder = InPredicate.builder().expression(integerLiteral(1))
                .operands(integerLiteral(2));
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> builder.selectQuery(select));
        assertThat(exception.getMessage(),
                containsString("The '[NOT] IN' predicate cannot have both select query and expressions"));
    }

    @Test
    void testInPredicateBothSelectAndExpressionException() {
        final InPredicate.Builder builder = InPredicate.builder().expression(integerLiteral(1)).selectQuery(select);
        final Literal operand = stringLiteral("a");
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> builder.operands(operand));
        assertThat(exception.getMessage(),
                containsString("The '[NOT] IN' predicate cannot have both select query and expressions"));
    }

    @Test
    void testExistsPredicate() {
        assertThat(exists(select.all()), rendersTo("EXISTS (SELECT * FROM test)"));
    }

    @Test
    void testNestedExistsPredicate() {
        final BooleanExpression expr = or(not(true), exists(select.field("id")));
        assertThat(expr, rendersTo("NOT(TRUE) OR (EXISTS (SELECT id FROM test))"));
    }

    @Test
    void testBetweenPredicate() {
        final BooleanExpression expr = between(integerLiteral(2), integerLiteral(1), integerLiteral(3));
        assertThat(expr, rendersTo("2 BETWEEN 1 AND 3"));
    }

    @Test
    void testNotBetweenPredicate() {
        final BooleanExpression expr = notBetween(stringLiteral("c"), stringLiteral("a"), stringLiteral("b"));
        assertThat(expr, rendersTo("'c' NOT BETWEEN 'a' AND 'b'"));
    }

    @Test
    void testNestedBetweenPredicate() {
        final BooleanExpression expr = and(between(integerLiteral(2), integerLiteral(1), integerLiteral(3)),
                notBetween(stringLiteral("c"), stringLiteral("a"), stringLiteral("b")));
        assertThat(expr, rendersTo("(2 BETWEEN 1 AND 3) AND ('c' NOT BETWEEN 'a' AND 'b')"));
    }

}
