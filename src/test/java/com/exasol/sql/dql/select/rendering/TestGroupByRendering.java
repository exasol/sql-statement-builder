package com.exasol.sql.dql.select.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.BooleanTerm.*;
import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;

class TestGroupByRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
        this.select.all().from().table("t");
    }

    @Test
    void testGroupByClause() {
        assertThat(this.select.groupBy(column("city")), rendersTo("SELECT * FROM t GROUP BY city"));
    }

    @Test
    void testGroupByClause2() {
        assertThat(this.select.groupBy(column("city", "t")), rendersTo("SELECT * FROM t GROUP BY t.city"));
    }

    @Test
    void testGroupByClauseMultipleColumns() {
        assertThat(this.select.groupBy(column("city", "t"), column("order", "t"), column("price", "t")),
                rendersTo("SELECT * FROM t GROUP BY t.city, t.order, t.price"));
    }

    @Test
    void testGroupByClauseMultipleColumnsWithHaving() {
        assertThat(
                this.select.groupBy(column("city", "t"), column("order", "t"), column("price", "t"))
                        .having(lt(column("price", "t"), integerLiteral(10))),
                rendersTo("SELECT * FROM t GROUP BY t.city, t.order, t.price HAVING t.price < 10"));
    }

    @Test
    void testGroupByClauseMultipleColumnsWithMultipleHaving() {
        assertThat(
                this.select.groupBy(column("city"), column("order"), column("price")).having(
                        and(le(column("price", "t"), integerLiteral(10)), ne(column("price", "t"), integerLiteral(5)))),
                rendersTo("SELECT * FROM t GROUP BY city, order, price HAVING (t.price <= 10) AND (t.price <> 5)"));
    }

    @Test
    void testGroupByClauseMultipleColumnsWithMultipleHaving2() {
        assertThat(
                this.select.groupBy(column("city"), column("order"), column("price"))
                        .having(or(eq(column("city", "t"), stringLiteral("NEW YORK")),
                                eq(column("city", "t"), stringLiteral("MOSCOW")))),
                rendersTo(
                        "SELECT * FROM t GROUP BY city, order, price HAVING (t.city = 'NEW YORK') OR (t.city = 'MOSCOW')"));
    }
}