package com.exasol.sql.expression.function.exasol;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.ExpressionTerm.column;
import static com.exasol.sql.expression.ExpressionTerm.integerLiteral;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.BooleanTerm;

class ExasolAggregateFunctionTest {
    @Test
    void testAggregateFunctionCoalesce() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolAggregateFunction.APPROXIMATE_COUNT_DISTINCT, "COUNT_APPR", column("customer_id"));
        select.from().table("orders");
        select.where(BooleanTerm.gt(column("price"), integerLiteral(1000)));
        assertThat(select,
                rendersTo("SELECT APPROXIMATE_COUNT_DISTINCT(customer_id) COUNT_APPR FROM orders WHERE price > 1000"));
    }
}