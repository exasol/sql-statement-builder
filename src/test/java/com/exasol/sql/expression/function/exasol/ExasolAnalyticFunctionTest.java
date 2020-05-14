package com.exasol.sql.expression.function.exasol;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.ExpressionTerm.column;
import static com.exasol.sql.expression.ExpressionTerm.integerLiteral;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.BooleanTerm;

public class ExasolAnalyticFunctionTest {
    @Test
    void testAggregateFunctionCoalesce() {
        final Select select = StatementFactory.getInstance().select() //
                .field("department") //
                .function(ExasolAnalyticFunctions.ANY, " ANY_ ", BooleanTerm.lt(column("age"), integerLiteral(30)));
        select.from().table("employee_table");
        select.groupBy(column("department"));
        assertThat(select,
                rendersTo("SELECT department, ANY((age < 30)) ANY_ FROM employee_table GROUP BY department"));
    }
}