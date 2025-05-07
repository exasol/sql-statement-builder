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
        final Select select = StatementFactory.getInstance().select().udf("my_average", column("x"));
        select.from().table("t");
        assertThat(select, rendersTo("SELECT my_average(x) FROM t"));
    }

    @Test
    void testUdfFunctionWithEmits() {
        final ColumnsDefinition columnsDefinition = ColumnsDefinition.builder()
                .decimalColumn("id", 18, 0)
                .varcharColumn("user_name", 100)
                .decimalColumn("PAGE_VISITS", 18, 0)
                .timestampColumn("TS_DEFAULT")
                .timestampColumn("TS_NANOS", 9)
                .timestampWithLocalTimeZoneColumn("TS_TZ_DEFAULT")
                .timestampWithLocalTimeZoneColumn("TS_TZ_NANOS", 9)
                .build();
        final Select select = StatementFactory.getInstance().select().udf("sample_simple", columnsDefinition,
                column("id"), column("user_name"), column("page_visits"), column("ts_default"),
                column("ts_nanos"), column("ts_tz_default"), column("ts_tz_nanos"), integerLiteral(20));
        select.from().table("people");
        assertThat(select, rendersTo("SELECT sample_simple(id, user_name, page_visits, ts_default, ts_nanos,"
                + " ts_tz_default, ts_tz_nanos, 20)"
                + " EMITS (id DECIMAL(18,0), user_name VARCHAR(100), PAGE_VISITS DECIMAL(18,0), TS_DEFAULT TIMESTAMP,"
                + " TS_NANOS TIMESTAMP(9), TS_TZ_DEFAULT TIMESTAMP WITH LOCAL TIME ZONE,"
                + " TS_TZ_NANOS TIMESTAMP(9) WITH LOCAL TIME ZONE) FROM people"));
    }
}