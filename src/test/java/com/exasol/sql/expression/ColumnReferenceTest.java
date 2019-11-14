package com.exasol.sql.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class ColumnReferenceTest {

    @Test
    void getColumnName() {
        assertThat(ColumnReference.of("column", "table").getColumnName(), equalTo("column"));
    }

    @Test
    void getTableName() {
        assertThat(ColumnReference.of("column", "table").getTableName(), equalTo("table"));
    }
}