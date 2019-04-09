package com.exasol.sql.ddl.create.rendering;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ddl.create.CreateSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

class TestCreateSchemaRendering {
    private static final String SCHEMA_NAME = "testName";
    private CreateSchema createSchema;

    @BeforeEach
    void beforeEach() {
        this.createSchema = StatementFactory.getInstance().createSchema(SCHEMA_NAME);
    }

    @Test
    void testCreateSchema() {
        assertThat(this.createSchema, rendersTo("CREATE SCHEMA testName"));
    }
}
