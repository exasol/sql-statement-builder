package com.exasol.dql;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.Fragment;
import com.exasol.sql.dql.StatementFactory;
import com.exasol.sql.dql.StringRendererConfig;
import com.exasol.sql.rendering.StringRenderer;

class TestSelect {
    private StringRenderer renderer;

    @BeforeEach
    void beforeEach() {
        this.renderer = new StringRenderer();
    }

    @Test
    void testGetParentReturnsNull() {
        assertThat(StatementFactory.getInstance().select().getParent(), nullValue());
    }

    @Test
    void testEmptySelect() {
        assertFragmentRenderedTo(StatementFactory.getInstance().select(), "SELECT");
    }

    private void assertFragmentRenderedTo(final Fragment fragment, final String expected) {
        fragment.getRoot().accept(this.renderer);
        assertThat(this.renderer.render(), equalTo(expected));
    }

    @Test
    void testEmptySelectLowerCase() {
        final StringRendererConfig.Builder builder = new StringRendererConfig.Builder();
        builder.lowerCase(true);
        this.renderer = new StringRenderer(builder.build());
        assertFragmentRenderedTo(StatementFactory.getInstance().select(), "select");
    }

    @Test
    void testSelectAll() {
        assertFragmentRenderedTo(StatementFactory.getInstance().select().all(), //
                "SELECT *");
    }

    @Test
    void testSelectFieldNames() {
        assertFragmentRenderedTo(StatementFactory.getInstance().select().field("a", "b"), //
                "SELECT a, b");
    }

    @Test
    void testSelectFromTable() {
        assertFragmentRenderedTo(StatementFactory.getInstance().select().all().from("table"), //
                "SELECT * FROM table");
    }
}