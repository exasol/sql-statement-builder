package com.exasol.sql.dql.select.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.*;
import static com.exasol.sql.expression.BooleanTerm.*;
import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.*;

import com.exasol.sql.*;
import com.exasol.sql.dql.select.*;

class TestGroupByRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
        this.select.all().from().table("t");
    }

    @Test
    void testGroupByClause() {
        assertThat(this.select.groupBy(columnReference("city")), rendersTo("SELECT * FROM t GROUP BY city"));
    }

    @Test
    void testGroupByClause2() {
        assertThat(this.select.groupBy(columnReference("city", "t")), rendersTo("SELECT * FROM t GROUP BY t.city"));
    }

    @Test
    void testGroupByClauseMultipleColumns() {
        assertThat(this.select.groupBy(columnReference("city", "t"), columnReference("order", "t"),
                columnReference("price", "t")), rendersTo("SELECT * FROM t GROUP BY t.city, t.order, t.price"));
    }

    @Test
    void testGroupByClauseMultipleColumnsWithHaving() {
        assertThat(
                this.select
                        .groupBy(columnReference("city", "t"), columnReference("order", "t"),
                                columnReference("price", "t"))
                        .having(lt(columnReference("price", "t"), integerLiteral(10))),
                rendersTo("SELECT * FROM t GROUP BY t.city, t.order, t.price HAVING t.price < 10"));
    }

    @Test
    void testGroupByClauseMultipleColumnsWithMultipleHaving() {
        assertThat(
                this.select.groupBy(columnReference("city"), columnReference("order"), columnReference("price"))
                        .having(and(le(columnReference("price", "t"), integerLiteral(10)),
                                ne(columnReference("price", "t"), integerLiteral(5)))),
                rendersTo("SELECT * FROM t GROUP BY city, order, price HAVING (t.price <= 10) AND (t.price <> 5)"));
    }

    @Test
    void testGroupByClauseMultipleColumnsWithMultipleHaving2() {
        assertThat(
                this.select.groupBy(columnReference("city"), columnReference("order"), columnReference("price"))
                        .having(or(eq(columnReference("city", "t"), stringLiteral("NEW YORK")),
                                eq(columnReference("city", "t"), stringLiteral("MOSCOW")))),
                rendersTo(
                        "SELECT * FROM t GROUP BY city, order, price HAVING (t.city = 'NEW YORK') OR (t.city = 'MOSCOW')"));
    }
}