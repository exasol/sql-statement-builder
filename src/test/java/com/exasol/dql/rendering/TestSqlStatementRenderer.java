package com.exasol.dql.rendering;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.Select;
import com.exasol.sql.rendering.SqlStatementRenderer;

class TestSqlStatementRenderer {
    private Select select;

    @BeforeEach
    void beforeEach() {
        select = StatementFactory.getInstance().select();
    }

    @Test
    void testCreateAndRender() {
        this.select.all().from().table("foo");
        assertThat(SqlStatementRenderer.render(this.select), equalTo("SELECT * FROM foo"));
    }
}