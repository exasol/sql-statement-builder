package com.exasol.sql.ddl.drop;

import com.exasol.sql.StatementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class TestDropSchema {
    private static final String TEST_SCHEMA_NAME = "test schema name";
    private DropSchema dropSchema;

    @BeforeEach
    void setUp() {
        dropSchema = StatementFactory.getInstance().dropSchema(TEST_SCHEMA_NAME);
    }

    @Test
    void getTableName() {
        assertThat(this.dropSchema.getSchemaName(), equalTo(TEST_SCHEMA_NAME));
    }

    @Test
    void ifExists() {
        assertFalse(this.dropSchema.getIfExists());
        this.dropSchema.ifExists();
        assertTrue(this.dropSchema.getIfExists());
    }

    @Test
    void setCascade() {
        assertNull(this.dropSchema.getCascade());
        this.dropSchema.cascade();
        assertNotNull(this.dropSchema.getCascade());
    }

    @Test
    void setRestrict() {
        assertNull(this.dropSchema.getRestrict());
        this.dropSchema.restrict();
        assertNotNull(this.dropSchema.getRestrict());
    }
}