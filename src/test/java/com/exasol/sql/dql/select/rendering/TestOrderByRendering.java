package com.exasol.sql.dql.select.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.ExpressionTerm.column;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;

class TestOrderByRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
        this.select.all().from().table("t");
    }

    @Test
    void testOrderByClause() {
        assertThat(this.select.orderBy(column("t", "city"), column("t", "price")),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price"));
    }

    @Test
    void testOrderByClauseDesc() {
        assertThat(this.select.orderBy(column("t", "city"), column("t", "price")).desc(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price DESC"));
    }

    @Test
    void testOrderByClauseAsc() {
        assertThat(this.select.orderBy(column("t", "city"), column("t", "price")).asc(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price ASC"));
    }

    @Test
    void testOrderByClauseAscAndDesc() {
        assertThat(this.select.orderBy(column("t", "city"), column("t", "price")).asc().desc(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price DESC"));
    }

    @Test
    void testOrderByClauseDescAndAsc() {
        assertThat(this.select.orderBy(column("t", "city"), column("t", "price")).desc().asc(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price ASC"));
    }

    @Test
    void testOrderByClauseNullsFirst() {
        assertThat(this.select.orderBy(column("t", "city"), column("t", "price")).nullsFirst(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price NULLS FIRST"));
    }

    @Test
    void testOrderByClauseNullsLast() {
        assertThat(this.select.orderBy(column("t", "city"), column("t", "price")).nullsLast(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price NULLS LAST"));
    }

    @Test
    void testOrderByClauseNullsFirstAndLast() {
        assertThat(this.select.orderBy(column("t", "city"), column("t", "price")).nullsFirst().nullsLast(),
                rendersTo("SELECT * FROM t ORDER BY t.city, t.price NULLS LAST"));
    }
}
