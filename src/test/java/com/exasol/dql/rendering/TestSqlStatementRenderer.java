package com.exasol.dql.rendering;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.rendering.SqlStatementRenderer;

class TestSqlStatementRenderer {
    @Test
    void testCreateAndRender() {
        assertThat(SqlStatementRenderer.render(StatementFactory.getInstance().select().all().from("foo")),
                equalTo("SELECT * FROM foo"));
    }
}