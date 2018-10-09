package com.exasol.sql.dml.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dml.Insert;

class TestInsertRendering {
    private static final String PERSON = "person";
    private Insert insert;

    @BeforeEach
    void beforeEach() {
        this.insert = StatementFactory.getInstance().insertInto(PERSON);
    }

    // [dsn~rendering.sql.insert~1]
    @Test
    void testInsert() {
        assertThat(this.insert, rendersTo("INSERT INTO person"));
    }

    // [dsn~rendering.sql.insert~1]
    @Test
    void testInsertFields() {
        assertThat(this.insert.field("a", "b"), rendersTo("INSERT INTO person (a, b)"));
    }
}