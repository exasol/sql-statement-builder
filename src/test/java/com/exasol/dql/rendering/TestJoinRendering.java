package com.exasol.dql.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;

class TestJoinRendering {
    @Test
    public void testJoin() {
        assertThat(
                StatementFactory.getInstance().select().all().from("left_table").join("right_table",
                        "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    public void testInnerJoin() {
        assertThat(
                StatementFactory.getInstance().select().all().from("left_table").innerJoin("right_table",
                        "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table INNER JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    public void testLeftJoin() {
        assertThat(
                StatementFactory.getInstance().select().all().from("left_table").leftJoin("right_table",
                        "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table LEFT JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    public void testRightJoin() {
        assertThat(
                StatementFactory.getInstance().select().all().from("left_table").rightJoin("right_table",
                        "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table RIGHT JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    public void testFullJoin() {
        assertThat(
                StatementFactory.getInstance().select().all().from("left_table").fullJoin("right_table",
                        "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table FULL JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    public void testLeftOuterJoin() {
        assertThat(
                StatementFactory.getInstance().select().all().from("left_table").leftOuterJoin("right_table",
                        "left_table.foo_id = right_table.foo_id"),
                rendersTo(
                        "SELECT * FROM left_table LEFT OUTER JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    public void testRightOuterJoin() {
        assertThat(
                StatementFactory.getInstance().select().all().from("left_table").rightOuterJoin("right_table",
                        "left_table.foo_id = right_table.foo_id"),
                rendersTo(
                        "SELECT * FROM left_table RIGHT OUTER JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    public void testFullOuterJoin() {
        assertThat(
                StatementFactory.getInstance().select().all().from("left_table").fullOuterJoin("right_table",
                        "left_table.foo_id = right_table.foo_id"),
                rendersTo(
                        "SELECT * FROM left_table FULL OUTER JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }
}