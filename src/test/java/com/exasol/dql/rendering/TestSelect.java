package com.exasol.dql.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersWithConfigTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.dql.StatementFactory;
import com.exasol.sql.rendering.StringRendererConfig;

class TestSelect {
    @Test
    void testGetParentReturnsNull() {
        assertThat(StatementFactory.getInstance().select().getParent(), nullValue());
    }

    @Test
    void testEmptySelect() {
        assertThat(StatementFactory.getInstance().select(), rendersTo("SELECT"));
    }

    @Test
    void testEmptySelectLowerCase() {
        final StringRendererConfig config = new StringRendererConfig.Builder().lowerCase(true).build();
        assertThat(StatementFactory.getInstance().select(), rendersWithConfigTo(config, "select"));
    }

    @Test
    void testSelectAll() {
        assertThat(StatementFactory.getInstance().select().all(), rendersTo("SELECT *"));
    }

    @Test
    void testSelectFieldNames() {
        assertThat(StatementFactory.getInstance().select().field("a", "b"), rendersTo("SELECT a, b"));
    }

    @Test
    void testSelectChainOfFieldNames() {
        assertThat(StatementFactory.getInstance().select().field("a", "b").field("c"), rendersTo("SELECT a, b, c"));
    }

    @Test
    void testSelectFromTable() {
        assertThat(StatementFactory.getInstance().select().all().from("table"), rendersTo("SELECT * FROM table"));
    }

    @Test
    void testSelectFromMultipleTable() {
        assertThat(StatementFactory.getInstance().select().all().from("table1").from("table2"),
                rendersTo("SELECT * FROM table1, table2"));
    }

    @Test
    void testSelectFromTableAs() {
        assertThat(StatementFactory.getInstance().select().all().fromTableAs("table", "t"),
                rendersTo("SELECT * FROM table AS t"));
    }

    @Test
    void testSelectFromMultipleTableAs() {
        assertThat(
                StatementFactory.getInstance().select().all().fromTableAs("table1", "t1").fromTableAs("table2", "t2"),
                rendersTo("SELECT * FROM table1 AS t1, table2 AS t2"));
    }
}