package com.exasol.sql.dml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;

class TestInsert {
    private static final String TABLE_NAME = "person";
    private Insert insert;

    @BeforeEach
    void beforeEach() {
        this.insert = StatementFactory.getInstance().insertInto(TABLE_NAME);
    }

    // [utest->dsn~insert-statements~1]
    @Test
    void testInsert() {
        assertThat(this.insert, instanceOf(Insert.class));
    }

    // [utest->dsn~insert-statements~1]
    @Test
    void testInsertTableName() {
        assertThat(this.insert.getTableName(), equalTo(TABLE_NAME));
    }
}