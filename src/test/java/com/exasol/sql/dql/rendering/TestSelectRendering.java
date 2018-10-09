package com.exasol.sql.dql.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.Select;

class TestSelectRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
    }

    // [dsn~rendering.sql.select~1]
    @Test
    void testSelectAll() {
        assertThat(this.select.all(), rendersTo("SELECT *"));
    }

    // [dsn~rendering.sql.select~1]
    @Test
    void testSelectFieldNames() {
        assertThat(this.select.field("a", "b"), rendersTo("SELECT a, b"));
    }

    // [dsn~rendering.sql.select~1]
    @Test
    void testSelectChainOfFieldNames() {
        assertThat(this.select.field("a", "b").field("c"), rendersTo("SELECT a, b, c"));
    }

    // [dsn~rendering.sql.select~1]
    @Test
    void testSelectFromTable() {
        assertThat(this.select.all().from().table("persons"), rendersTo("SELECT * FROM persons"));
    }

    // [dsn~rendering.sql.select~1]
    @Test
    void testSelectFromMultipleTable() {
        assertThat(this.select.all().from().table("table1").table("table2"), rendersTo("SELECT * FROM table1, table2"));
    }

    // [dsn~rendering.sql.select~1]
    @Test
    void testSelectFromTableAs() {
        assertThat(this.select.all().from().tableAs("table", "t"), rendersTo("SELECT * FROM table AS t"));
    }

    // [dsn~rendering.sql.select~1]
    @Test
    void testSelectFromMultipleTableAs() {
        assertThat(this.select.all().from().tableAs("table1", "t1").tableAs("table2", "t2"),
                rendersTo("SELECT * FROM table1 AS t1, table2 AS t2"));
    }
}