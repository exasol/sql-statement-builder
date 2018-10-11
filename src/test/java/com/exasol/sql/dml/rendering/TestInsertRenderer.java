package com.exasol.sql.dml.rendering;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dml.Insert;
import com.exasol.sql.rendering.StringRendererConfig;

class TestInsertRenderer {
    @Test
    void testCreateWithDefaultConfig() {
        assertThat(InsertRenderer.create(), instanceOf(InsertRenderer.class));
    }

    @Test
    void testCreateWithConfig() {
        final StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
        final InsertRenderer renderer = InsertRenderer.create(config);
        final Insert insert = StatementFactory.getInstance().insertInto("city");
        insert.accept(renderer);
        assertThat(renderer.render(), startsWith("insert"));
    }
}