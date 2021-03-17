package com.exasol.sql.expression.function.exasol;

import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.exasol.sql.expression.BooleanTerm;

class ExasolFunctionTest extends AbstractFunctionTest {
    @Test
    void testAggregateFunction() {
        final ExasolFunction function = ExasolFunction.of(ExasolAggregateFunction.APPROXIMATE_COUNT_DISTINCT,
                column("customer_id"));
        assertThat(renderFunction(function), equalTo("APPROXIMATE_COUNT_DISTINCT(customer_id)"));
    }

    @Test
    void testAnalyticFunction() {
        final ExasolFunction function = ExasolFunction.of(ExasolAnalyticFunction.ANY,
                BooleanTerm.lt(column("age"), integerLiteral(30)));
        assertThat(renderFunction(function), equalTo("ANY((age < 30))"));
    }

    @ParameterizedTest
    @CsvSource({ //
            "ABS, -123, ABS(-123)", //
            "LN, 100, LN(100)", //
            "LOG10, 10000, LOG10(10000)", //
            "LOG2, 1024, LOG2(1024)", //
            "RADIANS, 180, RADIANS(180)", //
            "SINH, 0, SINH(0)", //
            "TANH, 0, TANH(0)", //
            "CHAR, 88, CHAR(88)", //
            "UNICODECHR, 252, UNICODECHR(252)", //
            "ASIN, 1, ASIN(1)", //
            "ATAN, 1, ATAN(1)", //
            "COSH, 1, COSH(1)", //
            "COT, 1, COT(1)", //
            "EXP, 1, EXP(1)", //
            "SIGN, -123, SIGN(-123)", //
            "SIN, 1, SIN(1)", //
            "SQRT, 2, SQRT(2)" //
    })
    void testScalarFunctionWithInteger(final String functionName, final int integerLiteral, final String expected) {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.valueOf(functionName),
                integerLiteral(integerLiteral));
        assertThat(renderFunction(function), equalTo(expected));
    }

    @ParameterizedTest
    @CsvSource({ //
            "ACOS, 0.5, ACOS(0.5)", //
            "CEIL, 0.234, CEIL(0.234)", //
            "FLOOR, 4.567, FLOOR(4.567)" //
    })
    void testScalarFunctionWithDouble(final String functionName, final double doubleLiteral, final String expected) {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.valueOf(functionName),
                doubleLiteral(doubleLiteral));
        assertThat(renderFunction(function), equalTo(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "ATAN2; 1; 1; ATAN2(1, 1)", //
            "DIV; 1; 1; DIV(1, 1)", //
            "LOG; 2; 1024; LOG(2, 1024)", //
            "MOD; 15; 6; MOD(15, 6)", //
            "POWER; 2; 10; POWER(2, 10)", //
            "RANDOM; 1; 10; RANDOM(1, 10)" //
    }, delimiter = ';')
    // This method has different delimiter because arguments contain commas.
    void testScalarFunctionWithTwoIntegers(final String functionName, final int first, final int second,
            final String expected) {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.valueOf(functionName),
                integerLiteral(first), integerLiteral(second));
        assertThat(renderFunction(function), equalTo(expected));
    }

    @ParameterizedTest
    @CsvSource({ "COS, 3, COS((PI()/3))", //
            "TAN, 4, TAN((PI()/4))" //
    })
    void testScalarFunctionWithBinaryArithmetic(final String functionName, final int integerLiteral,
            final String expected) {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.valueOf(functionName),
                divide(function(ExasolScalarFunction.PI), integerLiteral(integerLiteral)));
        assertThat(renderFunction(function), equalTo(expected));
    }

    @ParameterizedTest
    @CsvSource({ //
            "ASCII, X, ASCII('X')", //
            "BIT_LENGTH, äöü, BIT_LENGTH('äöü')", //
            "CHARACTER_LENGTH, aeiouäöü, CHARACTER_LENGTH('aeiouäöü')", //
            "COLOGNE_PHONETIC, schmitt, COLOGNE_PHONETIC('schmitt')", //
            "DUMP, 123abc, DUMP('123abc')", //
            "INITCAP, ExAsOl is great, INITCAP('ExAsOl is great')", //
            "LCASE, AbCdEf, LCASE('AbCdEf')", //
            "LOWER, AbCdEf, LOWER('AbCdEf')", //
            "OCTET_LENGTH, abcd, OCTET_LENGTH('abcd')", //
            "REVERSE, abcde, REVERSE('abcde')", //
            "SOUNDEX, smythe, SOUNDEX('smythe')", //
            "UCASE, AbCdEf, UCASE('AbCdEf')", //
            "UNICODE, ä, UNICODE('ä')", //
            "UPPER, AbCdEf, UPPER('AbCdEf')", //
            "TO_NUMBER, +123, TO_NUMBER('+123')", //
            "TO_CHAR, 1999-12-31, TO_CHAR('1999-12-31')" //
    })
    void testScalarFunctionWithString(final String functionName, final String literalString, final String expected) {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.valueOf(functionName),
                stringLiteral(literalString));
        assertThat(renderFunction(function), equalTo(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "CONCAT; abc; def; CONCAT('abc', 'def')", //
            "EDIT_DISTANCE; schmitt; Schmidt; EDIT_DISTANCE('schmitt', 'Schmidt')", //
            "INSTR; abcabcabc; cab; INSTR('abcabcabc', 'cab')", //
            "LOCATE; cab; abcabcabc; LOCATE('cab', 'abcabcabc')", //
            "LTRIM; ab cdef; ab; LTRIM('ab cdef', 'ab')", //
            "REGEXP_INSTR; Phone: +497003927877678; \\+?\\d+; REGEXP_INSTR('Phone: +497003927877678', '\\+?\\d+')", //
            "REGEXP_REPLACE; my_mail@yahoo.com; [a-z0-9._%+-]; REGEXP_REPLACE('my_mail@yahoo.com', '[a-z0-9._%+-]')", //
            "REGEXP_SUBSTR; my_mail@yahoo.com; [a-z0-9._%+-]; REGEXP_SUBSTR('my_mail@yahoo.com', '[a-z0-9._%+-]')", //
            "RTRIM; abcdef; afe; RTRIM('abcdef', 'afe')", //
            "TO_NUMBER; -123.45; 99999.999; TO_NUMBER('-123.45', '99999.999')", //
            "TRIM; abcdef; acf; TRIM('abcdef', 'acf')" //
    }, delimiter = ';')
    void testScalarFunctionWithTwoStrings(final String functionName, final String first, final String second,
            final String expected) {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.valueOf(functionName),
                stringLiteral(first), stringLiteral(second));
        assertThat(renderFunction(function), equalTo(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "LEFT; abcdef; 3; LEFT('abcdef', 3)", //
            "REPEAT; abc; 3; REPEAT('abc', 3)", //
            "RIGHT; abcdef; 3; RIGHT('abcdef', 3)", //
            "ADD_YEARS; 2000-02-29; 1; ADD_YEARS('2000-02-29', 1)" //
    }, delimiter = ';')
    void testScalarFunctionStringAndInteger(final String functionName, final String stringLiteral,
            final int integerLiteral, final String expected) {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.valueOf(functionName),
                stringLiteral(stringLiteral), integerLiteral(integerLiteral));
        assertThat(renderFunction(function), equalTo(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "REPLACE; Apple juice is great; Apple; Orange; REPLACE('Apple juice is great', 'Apple', 'Orange')", //
            "TRANSLATE; abcd; abc; xy; TRANSLATE('abcd', 'abc', 'xy')" //
    }, delimiter = ';')
    void testScalarFunctionThreeStrings(final String functionName, final String first, final String second,
            final String third, final String expected) {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.valueOf(functionName),
                stringLiteral(first), stringLiteral(second), stringLiteral(third));
        assertThat(renderFunction(function), equalTo(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "MID; abcdef; 2; 3; MID('abcdef', 2, 3)", //
            "SUBSTR; abcdef; 2; 3; SUBSTR('abcdef', 2, 3)" //
    }, delimiter = ';')
    void testScalarFunctionWithStringAndTwoIntegers(final String functionName, final String literalString,
            final Integer literalIntegerFirst, final Integer literalIntegerSecond, final String expected) {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.valueOf(functionName),
                stringLiteral(literalString), integerLiteral(literalIntegerFirst),
                integerLiteral(literalIntegerSecond));
        assertThat(renderFunction(function), equalTo(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "LPAD; abc; 5; X; LPAD('abc', 5, 'X')", //
            "RPAD; abc; 5; X; RPAD('abc', 5, 'X')" //
    }, delimiter = ';')
    void testScalarFunctionPad(final String functionName, final String stringfirstLiteral, final int integerLiteral,
            final String stringSecondLiteral, final String expected) {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.valueOf(functionName),
                stringLiteral(stringfirstLiteral), integerLiteral(integerLiteral), stringLiteral(stringSecondLiteral));
        assertThat(renderFunction(function), equalTo(expected));
    }

    @Test
    void testScalarFunctionDegreesWithFunctionArgument() {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.DEGREES,
                function(ExasolScalarFunction.PI));
        assertThat(renderFunction(function), equalTo("DEGREES(PI())"));
    }

    @Test
    void testScalarFunctionToChar() {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.TO_CHAR, doubleLiteral(12345.67890),
                stringLiteral("9999999.999999999"));
        assertThat(renderFunction(function), equalTo("TO_CHAR(12345.6789, '9999999.999999999')"));
    }

    @Test
    void testScalarFunctionRound() {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.ROUND, doubleLiteral(123.456),
                integerLiteral(2));
        assertThat(renderFunction(function), equalTo("ROUND(123.456, 2)"));
    }

    @Test
    void testScalarFunctionInsert() {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.INSERT, stringLiteral("abc"),
                integerLiteral(2), integerLiteral(2), stringLiteral("xxx"));
        assertThat(renderFunction(function), equalTo("INSERT('abc', 2, 2, 'xxx')"));
    }

    @Test
    void testScalarFunctionLength() {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.LENGTH, column("s"));
        assertThat(renderFunction(function), equalTo("LENGTH(s)"));
    }

    @Test
    void testScalarFunctionWithoutBrackets() {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.SYSDATE);
        assertThat(renderFunction(function), equalTo("SYSDATE"));
    }

    @Test
    void testScalarFunctionCoalesce() {
        final ExasolFunction function = ExasolFunction.of(ExasolScalarFunction.COALESCE, nullLiteral(),
                stringLiteral("abc"), nullLiteral(), stringLiteral("xyz"));
        assertThat(renderFunction(function), equalTo("COALESCE(NULL, 'abc', NULL, 'xyz')"));
    }
}