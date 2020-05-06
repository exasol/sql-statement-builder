package com.exasol.sql.expression.function.exasol;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.BinaryArithmeticExpression;

class ExasolFunctionTest {
    @ParameterizedTest
    @CsvSource({ "ABS, ABS_COL, -123, SELECT ABS(-123) ABS_COL", //
            "LN, LN_COL, 100, SELECT LN(100) LN_COL", //
            "LOG10, LOG10_COL, 10000, SELECT LOG10(10000) LOG10_COL", //
            "LOG2, LOG2_COL, 1024, SELECT LOG2(1024) LOG2_COL", //
            "RADIANS, RADIANS, 180, SELECT RADIANS(180) RADIANS", //
            "SINH, SINH_COL, 0, SELECT SINH(0) SINH_COL", //
            "TANH, TANH_COL, 0, SELECT TANH(0) TANH_COL" //
    })
    void scalarFunctionWithIntegerArgumentAndColumnName(final String functionName, final String columnName,
            final int integerLiteral, final String expected) {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.valueOf(functionName), columnName, integerLiteral(integerLiteral));
        assertThat(select, rendersTo(expected));
    }

    @ParameterizedTest
    @CsvSource({ "ACOS, ACOS_COL, 0.5, SELECT ACOS(0.5) ACOS_COL", //
            "CEIL, CEIL_COL, 0.234, SELECT CEIL(0.234) CEIL_COL", //
            "FLOOR, FLOOR_COL, 4.567, SELECT FLOOR(4.567) FLOOR_COL" //
    })
    void scalarFunctionWithDoubleArgumentAndColumnName(final String functionName, final String columnName,
            final double doubleLiteral, final String expected) {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.valueOf(functionName), columnName, doubleLiteral(doubleLiteral));
        assertThat(select, rendersTo(expected));
    }

    @ParameterizedTest
    @CsvSource({ "ASIN, 1, SELECT ASIN(1)", //
            "ATAN, 1, SELECT ATAN(1)", //
            "COSH, 1, SELECT COSH(1)", //
            "COT, 1, SELECT COT(1)", //
            "EXP, 1, SELECT EXP(1)", //
            "SIGN, -123, SELECT SIGN(-123)", //
            "SIN, 1, SELECT SIN(1)", //
            "SQRT, 2, SELECT SQRT(2)" //
    })
    void scalarFunctionWithLiteralInteger(final String functionName, final int integerLiteral, final String expected) {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.valueOf(functionName), integerLiteral(integerLiteral));
        assertThat(select, rendersTo(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { "ATAN2; ATAN2_COL; 1; 1; SELECT ATAN2(1, 1) ATAN2_COL", //
            "DIV; DIV_COL; 1; 1; SELECT DIV(1, 1) DIV_COL", //
            "LOG; LOG_COL; 2; 1024; SELECT LOG(2, 1024) LOG_COL", //
            "MOD; MODULO; 15; 6; SELECT MOD(15, 6) MODULO", //
            "POWER; POWER; 2; 10; SELECT POWER(2, 10) POWER" //
    }, delimiter = ';')
    // This method has different delimiter because arguments contain commas.
    void scalarFunctionWithTwoLiteralIntegers(final String functionName, final String columnName, final int first,
            final int second, final String expected) {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.valueOf(functionName), columnName, integerLiteral(first),
                integerLiteral(second));
        assertThat(select, rendersTo(expected));
    }

    @ParameterizedTest
    @CsvSource({ "COS, 3, SELECT COS(PI()/3)", //
            "TAN, 4, SELECT TAN(PI()/4)" //
    })
    void scalarFunctionWithBinaryArithmetic(final String functionName, final int integerLiteral,
            final String expected) {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.valueOf(functionName),
                arithmeticExpression(BinaryArithmeticExpression.BinaryArithmeticOperator.DIVIDE,
                        exasolFunction(ExasolScalarFunction.PI), integerLiteral(integerLiteral)));
        assertThat(select, rendersTo(expected));
    }

    @Test
    void scalarFunctionDegrees() {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.DEGREES, exasolFunction(ExasolScalarFunction.PI));
        assertThat(select, rendersTo("SELECT DEGREES(PI())"));
    }

    @Test
    void scalarFunctionLength() {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.LENGTH, column("s")).from().table("t");
        assertThat(select, rendersTo("SELECT LENGTH(s) FROM t"));
    }

    @Test
    void scalarFunctionPi() {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.PI);
        assertThat(select, rendersTo("SELECT PI()"));
    }

    @Test
    void scalarFunctionRandom() {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.RANDOM, "RANDOM_1").function(ExasolScalarFunction.RANDOM, "RANDOM_2",
                integerLiteral(5), integerLiteral(20));
        assertThat(select, rendersTo("SELECT RANDOM() RANDOM_1, RANDOM(5, 20) RANDOM_2"));
    }

    @Test
    void scalarFunctionRound() {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.ROUND, "ROUND", doubleLiteral(123.456), integerLiteral(2));
        assertThat(select, rendersTo("SELECT ROUND(123.456, 2) ROUND"));
    }

    @Test
    void scalarFunctionToChar() {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.TO_CHAR, "TO_CHAR", doubleLiteral(12345.67890),
                stringLiteral("9999999.999999999"));
        assertThat(select, rendersTo("SELECT TO_CHAR(12345.6789, '9999999.999999999') TO_CHAR"));
    }

    @Test
    void scalarFunctionToNumber() {
        final Select select = StatementFactory.getInstance().select();
        select.function(ExasolScalarFunction.TO_NUMBER, "TO_NUMBER1", stringLiteral("+123"));
        select.function(ExasolScalarFunction.TO_NUMBER, "TO_NUMBER2", stringLiteral("-123.45"),
                stringLiteral("99999.999"));
        assertThat(select,
                rendersTo("SELECT TO_NUMBER('+123') TO_NUMBER1, TO_NUMBER('-123.45', '99999.999') TO_NUMBER2"));
    }
}