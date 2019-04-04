package com.exasol.sql.ddl.drop;

import com.exasol.sql.StatementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class TestDropTable {
    private static final String TEST_TABLE_NAME = "test table name";
    private DropTable dropTable;

    @BeforeEach
    void beforeEach() {
        this.dropTable = StatementFactory.getInstance().dropTable(TEST_TABLE_NAME);
    }

    @Test
    void getTableName() {
        assertThat(this.dropTable.getTableName(), equalTo(TEST_TABLE_NAME));
    }

    @Test
    void ifExists() {
        assertFalse(this.dropTable.getIfExists());
        this.dropTable.ifExists();
        assertTrue(this.dropTable.getIfExists());
    }

    @Test
    void cascadeConstraints() {
        assertNull(this.dropTable.getCascadeConstraints());
        this.dropTable.cascadeConstraints();
        assertNotNull(this.dropTable.getCascadeConstraints());
    }
}