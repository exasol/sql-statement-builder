package com.exasol.sql.dql.select.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersWithConfigTo;
import static com.exasol.sql.expression.BooleanTerm.*;
import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import com.exasol.datatype.type.Varchar;
import com.exasol.sql.StatementFactory;
import com.exasol.sql.ValueTable;
import com.exasol.sql.dql.select.OrderByClause;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.*;
import com.exasol.sql.expression.function.exasol.*;
import com.exasol.sql.expression.function.exasol.AnalyticFunction.Keyword;
import com.exasol.sql.expression.function.exasol.WindowFrameClause.*;
import com.exasol.sql.expression.literal.NullLiteral;
import com.exasol.sql.rendering.StringRendererConfig;

class TestSelectRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
    }

    @Test
    // Not a requirement, just to see what happens
    void testSelectWithoutFields() {
        assertThat(this.select, rendersTo("SELECT "));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectAll() {
        assertThat(this.select.all(), rendersTo("SELECT *"));
    }

    // [utest->dsn~rendering.sql.configurable-case~1]
    @Test
    void testSelectAllLowerCase() {
        assertThat(this.select.all(),
                rendersWithConfigTo(StringRendererConfig.builder().lowerCase(true).build(), "select *"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFieldNames() {
        assertThat(this.select.field("a", "b"), rendersTo("SELECT a, b"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectChainOfFieldNames() {
        assertThat(this.select.field("a", "b").field("c"), rendersTo("SELECT a, b, c"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFromTable() {
        assertThat(this.select.all().from().table("persons"), rendersTo("SELECT * FROM persons"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFromMultipleTable() {
        assertThat(this.select.all().from().table("table1").table("table2"), rendersTo("SELECT * FROM table1, table2"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFromTableAs() {
        assertThat(this.select.all().from().tableAs("table", "t"), rendersTo("SELECT * FROM table AS t"));
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFromMultipleTableAs() {
        assertThat(this.select.all().from().tableAs("table1", "t1").tableAs("table2", "t2"),
                rendersTo("SELECT * FROM table1 AS t1, table2 AS t2"));
    }

    // [utest->dsn~select-statement.out-of-order-clauses~1]
    @Test
    void testAddClausesInRandomOrder() {
        assertThat(this.select.limit(1).all().where(not(true)).from().join("A", "A.aa = B.bb").table("B"),
                rendersTo("SELECT * FROM B JOIN A ON A.aa = B.bb WHERE NOT(TRUE) LIMIT 1"));
    }

    // [utest->dsn~rendering.add-double-quotes-for-schema-table-and-column-identifiers~1]
    @Test
    void testSelectWithQuotedIdentifiers() {
        final StringRendererConfig config = StringRendererConfig.builder().quoteIdentifiers(true).build();
        assertThat(this.select.field("fieldA", "tableA.fieldB", "tableB.*").from().table("schemaA.tableA"),
                rendersWithConfigTo(config,
                        "SELECT \"fieldA\", \"tableA\".\"fieldB\", \"tableB\".* FROM \"schemaA\".\"tableA\""));
    }

    @Test
    void testSelectWithQuotedIdentifiersDoesNotAddExtraQuotes() {
        final StringRendererConfig config = StringRendererConfig.builder().quoteIdentifiers(true).build();
        assertThat(this.select.field("\"fieldA\"", "\"tableA\".fieldB"),
                rendersWithConfigTo(config, "SELECT \"fieldA\", \"tableA\".\"fieldB\""));
    }

    @Test
    void testQuotedIdentifiers() {
        final StringRendererConfig config = StringRendererConfig.builder().quoteIdentifiers(true).build();
        final Select select = this.select.all();
        select.from().table("person");
        select.where(eq(stringLiteral("foo"), ColumnReference.of("test")));
        assertThat(select, rendersWithConfigTo(config, "SELECT * FROM \"person\" WHERE 'foo' = \"test\""));
    }

    @Test
    void testSelectFromSubSelect() {
        final Select innerSelect = StatementFactory.getInstance().select();
        innerSelect.all().from().table("t");
        this.select.all().from().select(innerSelect);
        assertThat(this.select, rendersTo("SELECT * FROM (SELECT * FROM t)"));
    }

    @Test
    void testSelectFromSubSelectInvalid() {
        final Select innerSelect = StatementFactory.getInstance().select();
        innerSelect.all().from().table("t");
        final ValueTable values = new ValueTable(this.select);
        this.select.all().from().select(innerSelect).valueTable(values);
        final SelectRenderer renderer = SelectRenderer.create();
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> this.select.accept(renderer));
        assertThat(exception.getMessage(),
                containsString("SELECT statement cannot combine sub-select and value table"));
    }

    @Test
    // [utest->dsn~like-predicate~1]
    void testSelectWithLikePredicate() {
        final BooleanExpression like1 = notLike(stringLiteral("abcd"), stringLiteral("a_d"));
        final BooleanExpression like2 = like(stringLiteral("%bcd"), stringLiteral("\\%%d"));
        this.select.valueExpression(like1, "res1").valueExpression(like2, "res2");
        assertThat(this.select, rendersTo("SELECT 'abcd' NOT LIKE 'a_d' res1, '%bcd' LIKE '\\%%d' res2"));
    }

    @Test
    void testSelectCastFunction() {
        final Select select = StatementFactory.getInstance().select()
                .function(CastExasolFunction.of(NullLiteral.nullLiteral(), new Varchar(254)), "TEST");
        assertThat(select, rendersTo("SELECT CAST(NULL AS  VARCHAR(254)) TEST"));
    }

    @Test
    void testSelectAggregateFunctionCoalesce() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolAnalyticAggregateFunctions.APPROXIMATE_COUNT_DISTINCT, "COUNT_APPR",
                        column("customer_id"));
        select.from().table("orders");
        select.where(BooleanTerm.gt(column("price"), integerLiteral(1000)));
        assertThat(select,
                rendersTo("SELECT APPROXIMATE_COUNT_DISTINCT(customer_id) COUNT_APPR FROM orders WHERE price > 1000"));
    }

    @Test
    void testSelectAggregateFunctionCountStar() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolAnalyticAggregateFunctions.COUNT, "COUNT", column("*"));
        select.from().table("orders");
        assertThat(select, rendersTo("SELECT COUNT(*) COUNT FROM orders"));
    }

    @Test
    void testSelectAnalyticFunctionWithoutArgument() {
        final Select select = StatementFactory.getInstance().select() //
                .field("department") //
                .function(ExasolAnalyticAggregateFunctions.ANY, " ANY_ ");
        select.from().table("employee_table");
        select.groupBy(column("department"));
        assertThat(select, rendersTo("SELECT department, ANY() ANY_ FROM employee_table GROUP BY department"));
    }

    @Test
    void testSelectAnalyticFunction() {
        final Select select = StatementFactory.getInstance().select() //
                .field("department") //
                .function(ExasolAnalyticAggregateFunctions.ANY, " ANY_ ",
                        BooleanTerm.lt(column("age"), integerLiteral(30)));
        select.from().table("employee_table");
        select.groupBy(column("department"));
        assertThat(select,
                rendersTo("SELECT department, ANY((age < 30)) ANY_ FROM employee_table GROUP BY department"));
    }

    @Test
    void testSelectAnalyticFunctionWithMultipleArgs() {
        final Select select = StatementFactory.getInstance().select() //
                .field("department") //
                .function(ExasolAnalyticAggregateFunctions.ANY, " ANY_ ", //
                        BooleanTerm.lt(column("age"), integerLiteral(30)),
                        BooleanTerm.gt(column("age"), integerLiteral(40)));
        select.from().table("employee_table");
        select.groupBy(column("department"));
        assertThat(select, rendersTo(
                "SELECT department, ANY((age < 30), (age > 40)) ANY_ FROM employee_table GROUP BY department"));
    }

    @ParameterizedTest
    @CsvSource(nullValues = "NULL", value = { //
            "NULL, ''", //
            "DISTINCT, DISTINCT", //
            "ALL, ALL" //
    })
    void testSelectAnalyticFunctionWithKeyword(
            final com.exasol.sql.expression.function.exasol.AnalyticFunction.Keyword keyword,
            final String expectedKeyword) {
        final AnalyticFunction function = AnalyticFunction.of(ExasolAnalyticAggregateFunctions.ANY,
                BooleanTerm.lt(column("age"), integerLiteral(30)));
        if (keyword == Keyword.DISTINCT) {
            function.keywordDistinct();
        }
        if (keyword == Keyword.ALL) {
            function.keywordAll();
        }
        final Select select = StatementFactory.getInstance().select() //
                .field("department") //
                .function(function, "ANY_");
        select.from().table("employee_table");
        select.groupBy(column("department"));
        assertThat(select, rendersTo("SELECT department, ANY(" + expectedKeyword
                + "(age < 30)) ANY_ FROM employee_table GROUP BY department"));
    }

    static Stream<Arguments> overClauseArguments() {
        return Stream.of(arguments(null, ""), //
                arguments(OverClause.of("window1"), " OVER(window1)"),
                arguments(OverClause.of("window1").orderBy(new OrderByClause(null, column("dep"))),
                        " OVER(window1 ORDER BY dep)"),
                arguments(OverClause.of(null).orderBy(new OrderByClause(null, column("dep"))), " OVER( ORDER BY dep)"),
                arguments(OverClause.of("window1").orderBy(new OrderByClause(null, column("dep")).asc().nullsFirst()),
                        " OVER(window1 ORDER BY dep ASC NULLS FIRST)"),
                arguments(OverClause.of("window1").partitionBy(), " OVER(window1)"),
                arguments(OverClause.of("window1").partitionBy(column("col")), " OVER(window1 PARTITION BY col)"),
                arguments(OverClause.of("window1").partitionBy(column("col1"), column("col2")),
                        " OVER(window1 PARTITION BY col1, col2)"),
                arguments(
                        OverClause.of("window1")
                                .windowFrame(frame -> frame.type(WindowFrameType.ROWS).unit(UnitType.CURRENT_ROW)),
                        " OVER(window1 ROWS CURRENT ROW)"),
                arguments(OverClause.of("window1").windowFrame(
                        frame -> frame.type(WindowFrameType.ROWS).unit(integerLiteral(42), UnitType.PRECEEDING)),
                        " OVER(window1 ROWS 42 PRECEEDING)"),
                arguments(
                        OverClause.of("window1")
                                .windowFrame(frame -> frame.type(WindowFrameType.ROWS)
                                        .unitBetween(UnitType.UNBOUNDED_PRECEEDING, UnitType.UNBOUNDED_FOLLOWING)),
                        " OVER(window1 ROWS BETWEEN UNBOUNDED PRECEEDING AND UNBOUNDED FOLLOWING)"),
                arguments(
                        OverClause.of("window1")
                                .windowFrame(frame -> frame.type(WindowFrameType.ROWS).unitBetween(column("col1"),
                                        UnitType.PRECEEDING, column("col2"), UnitType.FOLLOWING)),
                        " OVER(window1 ROWS BETWEEN col1 PRECEEDING AND col2 FOLLOWING)"),

                arguments(
                        OverClause.of("window1")
                                .windowFrame(frame -> frame.type(WindowFrameType.ROWS).unit(UnitType.CURRENT_ROW)
                                        .exclude(WindowFrameExclusionType.NO_OTHERS)),
                        " OVER(window1 ROWS CURRENT ROW EXCLUDE NO OTHERS)"),
                arguments(
                        OverClause.of("window1")
                                .windowFrame(frame -> frame.type(WindowFrameType.ROWS)
                                        .unitBetween(column("col1"), UnitType.CURRENT_ROW, column("col2"),
                                                UnitType.CURRENT_ROW)
                                        .exclude(WindowFrameExclusionType.CURRENT_ROW)),
                        " OVER(window1 ROWS BETWEEN CURRENT ROW AND CURRENT ROW EXCLUDE CURRENT ROW)"));
    }

    @ParameterizedTest
    @MethodSource("overClauseArguments")
    void testSelectAnalyticFunctionWithOverClause(final OverClause overClause, final String expectedOverClause) {
        final AnalyticFunction function = AnalyticFunction.of(ExasolAnalyticAggregateFunctions.AVG, column("age")) //
                .over(overClause);
        final Select select = StatementFactory.getInstance().select() //
                .field("department") //
                .function(function, "_AGE");
        select.from().table("employee_table");
        assertThat(select, rendersTo("SELECT department, AVG(age)" + expectedOverClause + " _AGE FROM employee_table"));
    }

    @Test
    void testSelectAnalyticFunctionOverClauseConfigurator() {
        final AnalyticFunction function = AnalyticFunction.of(ExasolAnalyticAggregateFunctions.AVG, column("age")) //
                .over(clause -> clause.windowName("window").partitionBy(column("col1"))
                        .orderBy(new OrderByClause(null, column("col2")).asc())
                        .windowFrame(frame -> frame.type(WindowFrameType.ROWS).unit(UnitType.CURRENT_ROW)
                                .exclude(WindowFrameExclusionType.CURRENT_ROW)));
        final Select select = StatementFactory.getInstance().select() //
                .field("department") //
                .function(function, "_AGE");
        select.from().table("employee_table");
        assertThat(select, rendersTo(
                "SELECT department, AVG(age) OVER(window PARTITION BY col1 ORDER BY col2 ASC ROWS CURRENT ROW EXCLUDE CURRENT ROW) _AGE FROM employee_table"));
    }

    @Test
    void testSelectTwoScalarFunctions() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.ADD_YEARS, "AY1", stringLiteral("2000-02-29"), integerLiteral(1)) //
                .function(ExasolScalarFunction.ADD_YEARS, "AY2", stringLiteral("2005-01-31 12:00:00"),
                        integerLiteral(-1));
        assertThat(select,
                rendersTo("SELECT ADD_YEARS('2000-02-29', 1) AY1, ADD_YEARS('2005-01-31 12:00:00', -1) AY2"));
    }
}