package com.exasol.sql.ddl.drop.rendering;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ddl.drop.DropSchema;
import com.exasol.sql.rendering.StringRendererConfig;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.StringStartsWith.startsWith;

class TestDropSchemaRenderer {
    @Test
    void testCreateWithDefaultConfig() {
        assertThat(DropSchemaRenderer.create(), instanceOf(DropSchemaRenderer.class));
    }

    @Test
    void testCreateWithConfig() {
        final StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
        final DropSchemaRenderer renderer = DropSchemaRenderer.create(config);
        final DropSchema dropSchema = StatementFactory.getInstance().dropSchema("test name");
        dropSchema.accept(renderer);
        assertThat(renderer.render(), startsWith("drop schema"));
    }
}
