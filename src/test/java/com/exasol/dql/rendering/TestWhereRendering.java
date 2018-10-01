package com.exasol.dql.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.BooleanTerm.eq;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;

class TestWhereRendering {
    @Test
    public void testWhere() {
        assertThat(StatementFactory.getInstance().select().all().from("person").where(eq("firstname", "Jane")),
                rendersTo("SELECT * FROM person WHERE firstname = Jane"));
    }
}