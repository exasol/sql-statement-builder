package com.exasol.dql.rendering;

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
        this.leftTable.join("right_table", "left_table.foo_id = right_table.foo_id");
        assertRendersTo("SELECT * FROM left_table JOIN right_table ON left_table.foo_id = right_table.foo_id");
    }

    private void assertRendersTo(final String expectedText) {
        assertThat(this.select, rendersTo(expectedText));
    }

    @Test
    void testInnerJoin() {
        this.leftTable.innerJoin("right_table", "left_table.foo_id = right_table.foo_id");
        assertRendersTo("SELECT * FROM left_table INNER JOIN right_table ON left_table.foo_id = right_table.foo_id");
    }

    @Test
    void testLeftJoin() {
        this.leftTable.leftJoin("right_table", "left_table.foo_id = right_table.foo_id");
        assertRendersTo("SELECT * FROM left_table LEFT JOIN right_table ON left_table.foo_id = right_table.foo_id");
    }

    @Test
    void testRightJoin() {
        this.leftTable.rightJoin("right_table", "left_table.foo_id = right_table.foo_id");
        assertRendersTo("SELECT * FROM left_table RIGHT JOIN right_table ON left_table.foo_id = right_table.foo_id");
    }

    @Test
    void testFullJoin() {
        this.leftTable.fullJoin("right_table", "left_table.foo_id = right_table.foo_id");
        assertRendersTo("SELECT * FROM left_table FULL JOIN right_table ON left_table.foo_id = right_table.foo_id");
    }

    @Test
    void testLeftOuterJoin() {
        this.leftTable.leftOuterJoin("right_table", "left_table.foo_id = right_table.foo_id");
        assertRendersTo(
                "SELECT * FROM left_table LEFT OUTER JOIN right_table ON left_table.foo_id = right_table.foo_id");
    }

    @Test
    void testRightOuterJoin() {
        this.leftTable.rightOuterJoin("right_table", "left_table.foo_id = right_table.foo_id");
        assertRendersTo(
                "SELECT * FROM left_table RIGHT OUTER JOIN right_table ON left_table.foo_id = right_table.foo_id");
    }

    @Test
    void testFullOuterJoin() {
        this.leftTable.fullOuterJoin("right_table", "left_table.foo_id = right_table.foo_id");
        assertRendersTo(
                "SELECT * FROM left_table FULL OUTER JOIN right_table ON left_table.foo_id = right_table.foo_id");
    }
}