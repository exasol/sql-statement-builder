package com.exasol.sql.dql.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.BooleanTerm.eq;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.Select;

class TestWhereRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
        this.select.all().from().table("person");
    }

    @Test
    void testWhere() {
        assertThat(this.select.where(eq("foo", "bar")), rendersTo("SELECT * FROM person WHERE 'foo' = 'bar'"));
    }
}