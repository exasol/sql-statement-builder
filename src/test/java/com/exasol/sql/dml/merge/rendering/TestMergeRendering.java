package com.exasol.sql.dml.merge.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.BooleanTerm.eq;
import static com.exasol.sql.expression.ColumnReference.column;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dml.merge.Merge;

class TestMergeRendering {
    private Merge merge;

    @BeforeEach()
    void beforeEach() {
        this.merge = StatementFactory.getInstance().mergeInto("dst");
    }

    @Test
    void testMerge() {
        assertThat(this.merge, rendersTo("MERGE INTO "));
    }

    @Test
    void testMergeIntoUsingOn() {
        assertThat(this.merge //
                .using("src") //
                .on(eq(column("c1", "src"), column("c1", "dst"))),
                rendersTo("MERGE INTO dst USING src ON src.c1 = dst.c1"));
    }

    @Test
    void testMergeWhenMatchedUpdate() {
        this.merge //
                .using("src") //
                .on(eq(column("c1", "src"), column("c1", "dst"))) //
                .whenMatched() //
                .thenUpdate() //
                .setToDefault("c2") //
                .set("c3", "foo") //
                .set("c4", 42);
        assertThat(this.merge, rendersTo("MERGE INTO dst USING src ON src.c1 = dst.c1" //
                + " WHEN MATCHED THEN UPDATE SET c2 = DEFAULT, c3 = 'foo', c4 = 42"));

    }

    @Test
    void testMergeWhenMatchedDelete() {
        this.merge //
                .using("src") //
                .on(eq(column("c1", "src"), column("c1", "dst"))) //
                .whenMatched() //
                .thenDelete(); //
        assertThat(this.merge, rendersTo("MERGE INTO dst USING src ON src.c1 = dst.c1" //
                + " WHEN MATCHED THEN DELETE"));

    }

    @Test
    void testMergeWhenNotMatched() {
        this.merge //
                .using("src") //
                .on(eq(column("c1", "src"), column("c1", "dst"))) //
                .whenNotMatchedThenInsert();
        fail("incomplete test");
    }
}