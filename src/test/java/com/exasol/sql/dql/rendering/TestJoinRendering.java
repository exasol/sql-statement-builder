package com.exasol.sql.dql.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.FromClause;
import com.exasol.sql.dql.Select;

class TestJoinRendering {
    private Select select;
    private FromClause leftTable;

    @BeforeEach()
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
        this.leftTable = this.select.all().from().table("left_table");
    }

    @Test
    void testJoin() {
        assertThat(this.leftTable.join("right_table", "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    void testInnerJoin() {
        assertThat(this.leftTable.innerJoin("right_table", "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table INNER JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    void testLeftJoin() {
        assertThat(this.leftTable.leftJoin("right_table", "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table LEFT JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    void testRightJoin() {
        assertThat(this.leftTable.rightJoin("right_table", "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table RIGHT JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    void testFullJoin() {
        assertThat(this.leftTable.fullJoin("right_table", "left_table.foo_id = right_table.foo_id"),
                rendersTo("SELECT * FROM left_table FULL JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    void testLeftOuterJoin() {
        assertThat(this.leftTable.leftOuterJoin("right_table", "left_table.foo_id = right_table.foo_id"), rendersTo(
                "SELECT * FROM left_table LEFT OUTER JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    void testRightOuterJoin() {
        assertThat(this.leftTable.rightOuterJoin("right_table", "left_table.foo_id = right_table.foo_id"), rendersTo(
                "SELECT * FROM left_table RIGHT OUTER JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }

    @Test
    void testFullOuterJoin() {
        assertThat(this.leftTable.fullOuterJoin("right_table", "left_table.foo_id = right_table.foo_id"), rendersTo(
                "SELECT * FROM left_table FULL OUTER JOIN right_table ON left_table.foo_id = right_table.foo_id"));
    }
}