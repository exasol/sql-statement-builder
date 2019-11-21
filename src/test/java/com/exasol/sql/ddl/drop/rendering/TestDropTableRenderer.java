package com.exasol.sql.ddl.drop.rendering;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ddl.drop.DropTable;
import com.exasol.sql.rendering.StringRendererConfig;

class TestDropTableRenderer {
    @Test
    void testCreateWithDefaultConfig() {
        assertThat(DropTableRenderer.create(), instanceOf(DropTableRenderer.class));
    }

    @Test
    void testCreateWithConfig() {
        final StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
        final DropTableRenderer renderer = DropTableRenderer.create(config);
        final DropTable dropTable = StatementFactory.getInstance().dropTable("test name");
        dropTable.accept(renderer);
        assertThat(renderer.render(), startsWith("drop table"));
    }
}
