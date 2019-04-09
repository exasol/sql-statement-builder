package com.exasol.sql.ddl.create.rendering;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ddl.create.CreateSchema;
import com.exasol.sql.rendering.StringRendererConfig;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;

class TestCreateSchemaRenderer {
    @Test
    void testCreateWithDefaultConfig() {
        assertThat(CreateSchemaRenderer.create(), instanceOf(CreateSchemaRenderer.class));
    }

    @Test
    void testCreateWithConfig() {
        final StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
        final CreateSchemaRenderer renderer = CreateSchemaRenderer.create(config);
        final CreateSchema createSchema = StatementFactory.getInstance().createSchema("test name");
        createSchema.accept(renderer);
        assertThat(renderer.render(), startsWith("create schema"));
    }
}
