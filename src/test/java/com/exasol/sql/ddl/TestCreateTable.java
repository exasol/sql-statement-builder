package com.exasol.sql.ddl;

import com.exasol.datatype.Boolean;
import com.exasol.datatype.Char;
import com.exasol.datatype.Date;
import com.exasol.sql.Column;
import com.exasol.sql.StatementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

class TestCreateTable {
    private static final int CHAR_SIZE = 20;
    private static final String TEST_TABLE_NAME = "test table name";
    private static final String BOOLEAN_COLUMN_NAME = "booleanColumn";
    private static final String CHAR_COLUMN_NAME = "charColumn";
    private static final String DATE_COLUMN_NAME = "booleanColumn";
    private CreateTable createTable;

    @BeforeEach
    void beforeEach() {
        this.createTable = StatementFactory.getInstance().createTable(TEST_TABLE_NAME);
    }

    @Test
    void getTableName() {
        assertThat(this.createTable.getTableName(), equalTo(TEST_TABLE_NAME));
    }

    @Test
    void testCreateTableWithBooleanColumn() {
        final Column column =
              this.createTable.booleanColumn(BOOLEAN_COLUMN_NAME).getCreateTableColumns()
                    .getColumns().get(0);
        assertThat(column.getColumnName(), equalTo(BOOLEAN_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(Boolean.class));
    }

    @Test
    void testCreateTableWithCharColumn() {
        final Column column =
              this.createTable.charColumn(CHAR_COLUMN_NAME, CHAR_SIZE).getCreateTableColumns()
                    .getColumns().get(0);
        assertThat(column.getColumnName(), equalTo(CHAR_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(Char.class));
    }

    @Test
    void testCreateTableWithDateColumn() {
        final Column column =
              this.createTable.dateColumn(DATE_COLUMN_NAME).getCreateTableColumns().getColumns()
                    .get(0);
        assertThat(column.getColumnName(), equalTo(DATE_COLUMN_NAME));
        assertThat(column.getDataType(), instanceOf(Date.class));
    }
}