package com.exasol.sql.dql.select.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.*;
import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.*;

import com.exasol.sql.*;
import com.exasol.sql.dql.select.*;

class TestOrderByRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
        this.select.all().from().table("t");
    }

    @Test
    void testOrderByClause() {
        assertThat(this.select.orderBy(columnReference("city", "t"), columnReference("price", "t")),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price"));
    }

    @Test
    void testOrderByClauseDesc() {
        assertThat(this.select.orderBy(columnReference("city", "t"), columnReference("price", "t")).desc(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price DESC"));
    }

    @Test
    void testOrderByClauseAsc() {
        assertThat(this.select.orderBy(columnReference("city", "t"), columnReference("price", "t")).asc(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price ASC"));
    }

    @Test
    void testOrderByClauseAscAndDesc() {
        assertThat(this.select.orderBy(columnReference("city", "t"), columnReference("price", "t")).asc().desc(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price DESC"));
    }

    @Test
    void testOrderByClauseDescAndAsc() {
        assertThat(this.select.orderBy(columnReference("city", "t"), columnReference("price", "t")).desc().asc(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price ASC"));
    }

    @Test
    void testOrderByClauseNullsFirst() {
        assertThat(this.select.orderBy(columnReference("city", "t"), columnReference("price", "t")).nullsFirst(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price NULLS FIRST"));
    }

    @Test
    void testOrderByClauseNullsLast() {
        assertThat(this.select.orderBy(columnReference("city", "t"), columnReference("price", "t")).nullsLast(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price NULLS LAST"));
    }

    @Test
    void testOrderByClauseNullsFirstAndLast() {
        assertThat(this.select.orderBy(columnReference("city", "t"), columnReference("price", "t")).nullsFirst()
                .nullsLast(), rendersTo("SELECT * FROM t ORDER BY t.city, t.price NULLS LAST"));
    }
}
