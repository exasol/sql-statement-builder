package com.exasol.dql;

import static com.exasol.hamcrest.RenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.dql.StatementFactory;

class TestJoin {
    @Test
    public void testJoin() {
        assertThat(
                StatementFactory.getInstance().select().all().from("left_table").join("right_table",
                        "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }
}