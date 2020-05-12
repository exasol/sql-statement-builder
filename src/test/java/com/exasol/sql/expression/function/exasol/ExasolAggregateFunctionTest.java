package com.exasol.sql.expression.function.exasol;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.BooleanTerm;

public class ExasolAggregateFunctionTest {
    @Test
    void testAggregateFunctionCoalesce() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolAggregateFunction.APPROXIMATE_COUNT_DISTINCT, "COUNT_APPR", column("customer_id"));
        select.from().table("orders");
        select.where(BooleanTerm.gt(column("price"), integerLiteral(1000)));
        assertThat(select,
                rendersTo("SELECT APPROXIMATE_COUNT_DISTINCT(customer_id) COUNT_APPR FROM orders WHERE price > 1000"));
    }

    @Test
    void testAggregateFunctionCount() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolAggregateFunction.COUNT, keyWord("DISTINCT"), column("customer_id"));
        select.from().table("orders");
        assertThat(select, rendersTo("SELECT COUNT( DISTINCT customer_id) FROM orders"));
    }

    @Test
    void testAggregateGroupConcat() {
        final Select select = StatementFactory.getInstance().select() //
                .field("department").function(ExasolAggregateFunction.GROUP_CONCAT, "GROUP_CONCAT_RESULT", column("id"),
                        keyWord("ORDER BY"), column("hire_date"), keyWord("SEPARATOR"), stringLiteral(", "));
        select.from().table("employee_table");
        select.groupBy(column("department"));
        assertThat(select,
                rendersTo("SELECT department, GROUP_CONCAT(id ORDER BY hire_date SEPARATOR ', ') GROUP_CONCAT_RESULT "
                        + "FROM employee_table GROUP BY department"));
    }
}