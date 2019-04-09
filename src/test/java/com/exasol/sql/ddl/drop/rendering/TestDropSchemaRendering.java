package com.exasol.sql.ddl.drop.rendering;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ddl.drop.DropSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestDropSchemaRendering {
    private static final String SCHEMA_NAME = "testName";
    private DropSchema dropSchema;

    @BeforeEach
    void beforeEach() {
        this.dropSchema = StatementFactory.getInstance().dropSchema(SCHEMA_NAME);
    }

    @Test
    void testDropSchema() {
        assertThat(this.dropSchema, rendersTo("DROP SCHEMA testName"));
    }

    @Test
    void testDropSchemaIfExists() {
        assertThat(this.dropSchema.ifExists(), rendersTo("DROP SCHEMA IF EXISTS testName"));
    }

    @Test
    void testDropSchemaCascade() {
        this.dropSchema.cascade();
        assertThat(this.dropSchema, rendersTo("DROP SCHEMA testName CASCADE"));
    }

    @Test
    void testDropSchemaRestrict() {
        this.dropSchema.restrict();
        assertThat(this.dropSchema, rendersTo("DROP SCHEMA testName RESTRICT"));
    }

    @Test
    void testDropSchemaCascadeAndRestrictThrowsException() {
        final DropSchemaRenderer renderer = DropSchemaRenderer.create();
        this.dropSchema.restrict();
        this.dropSchema.cascade();
        assertThrows(IllegalArgumentException.class, () -> dropSchema.accept(renderer));
    }
}
