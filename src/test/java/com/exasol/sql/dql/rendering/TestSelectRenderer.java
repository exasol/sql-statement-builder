package com.exasol.sql.dql.rendering;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.Select;
import com.exasol.sql.rendering.StringRendererConfig;

class TestSelectRenderer {
    @Test
    void testCreateWithDefaultConfig() {
        assertThat(SelectRenderer.create(), instanceOf(SelectRenderer.class));
    }

    @Test
    void testCreateWithConfig() {
        final StringRendererConfig config = new StringRendererConfig.Builder().lowerCase(true).build();
        final SelectRenderer renderer = SelectRenderer.create(config);
        final Select select = StatementFactory.getInstance().select();
        select.accept(renderer);
        assertThat(renderer.render(), startsWith("select"));
    }
}