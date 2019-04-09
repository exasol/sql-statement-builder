package com.exasol.sql.ddl.create.rendering;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ddl.create.CreateTable;
import com.exasol.sql.rendering.StringRendererConfig;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;

class TestCreateTableRenderer {
    @Test
    void testCreateWithDefaultConfig() {
        assertThat(CreateTableRenderer.create(), instanceOf(CreateTableRenderer.class));
    }

    @Test
    void testCreateWithConfig() {
        final StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
        final CreateTableRenderer renderer = CreateTableRenderer.create(config);
        final CreateTable createTable = StatementFactory.getInstance().createTable("test name");
        createTable.accept(renderer);
        assertThat(renderer.render(), startsWith("create table"));
    }
}
