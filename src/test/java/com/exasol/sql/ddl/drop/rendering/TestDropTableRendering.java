package com.exasol.sql.ddl.drop.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ddl.drop.DropTable;

// [utest->dsn~rendering.sql.drop~1]
class TestDropTableRendering {
    private static final String TABLE_NAME = "testName";
    private DropTable dropTable;

    @BeforeEach
    void beforeEach() {
        this.dropTable = StatementFactory.getInstance().dropTable(TABLE_NAME);
    }

    @Test
    void testDropTable() {
        assertThat(this.dropTable, rendersTo("DROP TABLE testName"));
    }

    @Test
    void testDropTableIfExist() {
        assertThat(this.dropTable.ifExists(), rendersTo("DROP TABLE IF EXISTS testName"));
    }

    @Test
    void testDropTableCascadeConstraints() {
        assertThat(this.dropTable.cascadeConstraints(), rendersTo("DROP TABLE testName CASCADE CONSTRAINTS"));
    }

    @Test
    void testDropTableIfExistCascadeConstraints() {
        assertThat(this.dropTable.ifExists().cascadeConstraints(),
                rendersTo("DROP TABLE IF EXISTS testName CASCADE CONSTRAINTS"));
    }
}