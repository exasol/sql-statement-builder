package com.exasol.sql.dql.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersWithConfigTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.Select;
import com.exasol.sql.expression.BooleanTerm;
import com.exasol.sql.rendering.StringRendererConfig;

class TestSelectRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectAll() {
        assertThat(this.select.all(), rendersTo("SELECT *"));
    }

    // [utest->dsn~rendering.sql.configurable-case~1]
    @Test
    void testSelectAllLowerCase() {
        assertThat(this.select.all(),
                rendersWithConfigTo(new StringRendererConfig.Builder().lowerCase(true).build(), "select *"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFieldNames() {
        assertThat(this.select.field("a", "b"), rendersTo("SELECT a, b"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectChainOfFieldNames() {
        assertThat(this.select.field("a", "b").field("c"), rendersTo("SELECT a, b, c"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFromTable() {
        assertThat(this.select.all().from().table("persons"), rendersTo("SELECT * FROM persons"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFromMultipleTable() {
        assertThat(this.select.all().from().table("table1").table("table2"), rendersTo("SELECT * FROM table1, table2"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFromTableAs() {
        assertThat(this.select.all().from().tableAs("table", "t"), rendersTo("SELECT * FROM table AS t"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFromMultipleTableAs() {
        assertThat(this.select.all().from().tableAs("table1", "t1").tableAs("table2", "t2"),
                rendersTo("SELECT * FROM table1 AS t1, table2 AS t2"));
    }

    // [utest->dsn~select-statement.out-of-order-clauses~1]
    @Test
    void testAddClausesInRandomOrder() {
        assertThat(this.select.limit(1).all().where(BooleanTerm.not("foo")).from().join("A", "A.aa = B.bb").table("B"),
                rendersTo("SELECT * FROM B JOIN A ON A.aa = B.bb WHERE NOT(foo) LIMIT 1"));
    }
}