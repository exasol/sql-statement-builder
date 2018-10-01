package com.exasol.dql.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;

class TestLimit {
    @Test
    void testLimitCountAfterFrom() {
        assertThat(StatementFactory.getInstance().select().all().from("t").limit(1),
                rendersTo("SELECT * FROM t LIMIT 1"));
    }

    @Test
    void testLimitOffsetCountAfterFrom() {
        assertThat(StatementFactory.getInstance().select().all().from("t").limit(2, 3),
                rendersTo("SELECT * FROM t LIMIT 2, 3"));
    }
}