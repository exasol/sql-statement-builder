package com.exasol.sql.ddl;

import com.exasol.datatype.Boolean;
import com.exasol.sql.Column;
import com.exasol.sql.StatementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

class TestCreateTable {
    private static final String TEST_TABLE_NAME = "test table name";
    public static final String BOOLEAN_FIELD_NAME = "booleanField";
    private CreateTable createTable;

    @BeforeEach
    void beforeEach() {
        this.createTable = StatementFactory.getInstance().create().table(TEST_TABLE_NAME);
    }

    @Test
    void getTableName() {
        assertThat(this.createTable.getTableName(), equalTo(TEST_TABLE_NAME));
    }

    @Test
    void testCreateTableWith() {
        final Column column = this.createTable.booleanColumn(BOOLEAN_FIELD_NAME).getColumns().get(0);
        assertThat(column.getColumnName(), equalTo(BOOLEAN_FIELD_NAME));
        assertThat(column.getDataType(), instanceOf(Boolean.class));
    }
}