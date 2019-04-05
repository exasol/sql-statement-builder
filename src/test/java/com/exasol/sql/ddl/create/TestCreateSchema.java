package com.exasol.sql.ddl.create;

import com.exasol.sql.StatementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TestCreateSchema {
    private static final String TEST_SCHEMA_NAME = "test schema name";
    private CreateSchema createSchema;

    @BeforeEach
    void setUp() {
        createSchema = StatementFactory.getInstance().createSchema(TEST_SCHEMA_NAME);
    }

    @Test
    void getTableName() {
        assertThat(this.createSchema.getSchemaName(), equalTo(TEST_SCHEMA_NAME));
    }
}