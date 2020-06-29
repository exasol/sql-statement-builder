package com.exasol.sql.ddl.drop;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;

// [utest->dsn~drop-statements~1]
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
        assertFalse(this.dropTable.hasIfExistsModifier());
        this.dropTable.ifExists();
        assertTrue(this.dropTable.hasIfExistsModifier());
    }

    @Test
    void cascadeConstraints() {
        assertNull(this.dropTable.getCascadeConstraints());
        this.dropTable.cascadeConstraints();
        assertNotNull(this.dropTable.getCascadeConstraints());
    }
}