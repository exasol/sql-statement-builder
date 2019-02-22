package com.exasol.sql.ddl.rendering;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ddl.CreateTable;
import com.exasol.sql.rendering.StringRendererConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersWithConfigTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreateTableRendering {
    private static final String TABLE_NAME = "testName";

    private CreateTable createTable;

    @BeforeEach
    void beforeEach() {
        this.createTable = StatementFactory.getInstance().createTable(TABLE_NAME);
    }

    // [utest->dsn~rendering.sql.insert~1]
    @Test
    void testCreateTable() {
        assertThat(this.createTable, rendersTo("CREATE TABLE testName"));
    }

    @Test
    void testCreateTableRendersToWithConfig() {
        assertThat(this.createTable,
              rendersWithConfigTo(StringRendererConfig.builder().lowerCase(true).build(), "create table testName"));
    }

    @Test
    void testCreateTableWithCharColumn() {
        assertThat(this.createTable.charColumn("a", 10), rendersTo("CREATE TABLE testName (a CHAR(10))"));
    }
}
