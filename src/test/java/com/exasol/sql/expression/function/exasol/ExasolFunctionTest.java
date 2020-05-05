package com.exasol.sql.expression.function.exasol;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;

class ExasolFunctionTest {
    @ParameterizedTest
    @CsvSource({ "ASIN, 1, SELECT ASIN(1)", //
            "ATAN, 1, SELECT ATAN(1)", //
            "SIN, 1, SELECT SIN(1)" //
    })
    void scalarFunctionWithIntegerArgument(final String functionName, final int integerLiteral, final String expected) {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.valueOf(functionName), integerLiteral(integerLiteral));
        assertThat(select, rendersTo(expected));
    }

    @Test
    void scalarFunctionAtan2() {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.ATAN2, "ATAN2", integerLiteral(1), integerLiteral(1));
        assertThat(select, rendersTo("SELECT ATAN2(1, 1) ATAN2"));
    }

    @Test
    void scalarFunctionLength() {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.LENGTH, column("s")).from().table("t");
        assertThat(select, rendersTo("SELECT LENGTH(s) FROM t"));
    }

    @ParameterizedTest
    @CsvSource({ "ABS, -123, ABS_COL, SELECT ABS(-123) ABS_COL" //
    })
    void scalarFunctionWithIntegerArgumentAndColumnName(final String functionName, final int integerLiteral,
            final String columnName, final String expected) {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.valueOf(functionName), columnName, integerLiteral(integerLiteral));
        assertThat(select, rendersTo(expected));
    }

    @ParameterizedTest
    @CsvSource({ "ACOS, 0.5, ACOS_COL, SELECT ACOS(0.5) ACOS_COL" //
    })
    void scalarFunctionWithDoubleArgumentAndColumnName(final String functionName, final double doubleLiteral,
            final String columnName, final String expected) {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.valueOf(functionName), columnName, doubleLiteral(doubleLiteral));
        assertThat(select, rendersTo(expected));
    }
}