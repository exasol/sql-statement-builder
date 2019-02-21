package com.exasol.sql.ddl;

import com.exasol.sql.StatementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class TestCreate {
    private static final String TABLE_NAME = "person";
    private Create create;

    @BeforeEach
    void beforeEach() {
        this.create = StatementFactory.getInstance().create();
    }

    @Test
    void testCreate() {
        assertThat(this.create, instanceOf(Create.class));
    }

    @Test
    void testCreateTable() {
        assertThat(this.create.table(TABLE_NAME).getTableName(), equalTo(TABLE_NAME));
    }
}
