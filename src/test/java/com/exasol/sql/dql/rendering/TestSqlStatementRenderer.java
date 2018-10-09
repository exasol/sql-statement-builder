package com.exasol.sql.dql.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.Select;

class TestSqlStatementRenderer {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
    }

    @Test
    void testCreateAndRender() {
        this.select.all().from().table("foo");
        assertThat(this.select, rendersTo("SELECT * FROM foo"));
    }
}