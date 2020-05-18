package com.exasol.sql.expression.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.ExpressionTerm.column;
import static com.exasol.sql.expression.ExpressionTerm.integerLiteral;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.ColumnsDefinition;
import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;

class ExasolUdfFunctionRenderingTest {
    @Test
    void testUdfFunction() {
        final Select select = StatementFactory.getInstance().select().udfFunction("my_average", column("x"));
        select.from().table("t");
        assertThat(select, rendersTo("SELECT my_average(x) FROM t"));
    }

    @Test
    void testUdfFunctionWithEmits() {
        final ColumnsDefinition columnsDefinition = ColumnsDefinition.builder().decimalColumn("id", 18, 0)
                .varcharColumn("user_name", 100).decimalColumn("PAGE_VISITS", 18, 0).build();
        final Select select = StatementFactory.getInstance().select().udfFunction("sample_simple", columnsDefinition,
                column("id"), column("user_name"), column("page_visits"), integerLiteral(20));
        select.from().table("people");
        assertThat(select, rendersTo("SELECT sample_simple(id, user_name, page_visits, 20)"
                + " EMITS (id DECIMAL(18,0), user_name VARCHAR(100), PAGE_VISITS DECIMAL(18,0)) FROM people"));
    }
}