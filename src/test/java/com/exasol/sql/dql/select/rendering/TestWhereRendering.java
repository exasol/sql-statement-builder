package com.exasol.sql.dql.select.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.*;
import static com.exasol.sql.expression.BooleanTerm.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.*;

import com.exasol.sql.*;
import com.exasol.sql.dql.select.*;
import com.exasol.sql.expression.*;

class TestWhereRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
        this.select.all().from().table("person");
    }

    @Test
    void testWhere() {
        assertThat(this.select.where(eq(ExpressionTerm.stringLiteral("foo"), ExpressionTerm.stringLiteral("bar"))),
                rendersTo("SELECT * FROM person WHERE 'foo' = 'bar'"));
    }
}