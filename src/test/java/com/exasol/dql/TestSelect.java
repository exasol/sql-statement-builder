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
        final Fragment fragment = StatementFactory.getInstance().select();
        assertFragmentRenderedTo(fragment, "SELECT");
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
        final Fragment fragment = StatementFactory.getInstance().select();
        assertFragmentRenderedTo(fragment, "select");
    }

    @Test
    void testSelectAll() {
        final Fragment fragment = StatementFactory.getInstance().select().all();
        assertFragmentRenderedTo(fragment, "SELECT *");
    }

    @Test
    void testSelectFieldNames() {
        final Fragment fragment = StatementFactory.getInstance().select().field("a", "b");
        assertFragmentRenderedTo(fragment, "SELECT a, b");
    }
}