package com.exasol.dql.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.Select;

class TestLimitRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
        this.select.all().from().table("t");
    }

    @Test
    void testLimitCountAfterFrom() {
        assertThat(this.select.limit(1), rendersTo("SELECT * FROM t LIMIT 1"));
    }

    @Test
    void testLimitOffsetCountAfterFrom() {
        assertThat(this.select.limit(2, 3), rendersTo("SELECT * FROM t LIMIT 2, 3"));
    }
}