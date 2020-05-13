package com.exasol.sql.expression.function.exasol;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;

class ExasolScalarFunctionTest {
    @ParameterizedTest
    @CsvSource({ //
            "ABS, ABS_COL, -123, SELECT ABS(-123) ABS_COL", //
            "LN, LN_COL, 100, SELECT LN(100) LN_COL", //
            "LOG10, LOG10_COL, 10000, SELECT LOG10(10000) LOG10_COL", //
            "LOG2, LOG2_COL, 1024, SELECT LOG2(1024) LOG2_COL", //
            "RADIANS, RADIANS, 180, SELECT RADIANS(180) RADIANS", //
            "SINH, SINH_COL, 0, SELECT SINH(0) SINH_COL", //
            "TANH, TANH_COL, 0, SELECT TANH(0) TANH_COL", //
            "CHAR, CHAR, 88, SELECT CHAR(88) CHAR", //
            "UNICODECHR, UNICODECHR, 252, SELECT UNICODECHR(252) UNICODECHR" //
    })
    void testScalarFunctionWithIntegerAndColumnName(final String functionName, final String columnName,
            final int integerLiteral, final String expected) {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.valueOf(functionName), columnName, integerLiteral(integerLiteral));
        assertThat(select, rendersTo(expected));
    }

    @ParameterizedTest
    @CsvSource({ //
            "ACOS, ACOS_COL, 0.5, SELECT ACOS(0.5) ACOS_COL", //
            "CEIL, CEIL_COL, 0.234, SELECT CEIL(0.234) CEIL_COL", //
            "FLOOR, FLOOR_COL, 4.567, SELECT FLOOR(4.567) FLOOR_COL" //
    })
    void testScalarFunctionWithDoubleAndColumnName(final String functionName, final String columnName,
            final double doubleLiteral, final String expected) {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.valueOf(functionName), columnName, doubleLiteral(doubleLiteral));
        assertThat(select, rendersTo(expected));
    }

    @ParameterizedTest
    @CsvSource({ //
            "ASIN, 1, SELECT ASIN(1)", //
            "ATAN, 1, SELECT ATAN(1)", //
            "COSH, 1, SELECT COSH(1)", //
            "COT, 1, SELECT COT(1)", //
            "EXP, 1, SELECT EXP(1)", //
            "SIGN, -123, SELECT SIGN(-123)", //
            "SIN, 1, SELECT SIN(1)", //
            "SQRT, 2, SELECT SQRT(2)" //
    })
    void testScalarFunctionWithInteger(final String functionName, final int integerLiteral, final String expected) {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.valueOf(functionName), integerLiteral(integerLiteral));
        assertThat(select, rendersTo(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "ATAN2; ATAN2_COL; 1; 1; SELECT ATAN2(1, 1) ATAN2_COL", //
            "DIV; DIV_COL; 1; 1; SELECT DIV(1, 1) DIV_COL", //
            "LOG; LOG_COL; 2; 1024; SELECT LOG(2, 1024) LOG_COL", //
            "MOD; MODULO; 15; 6; SELECT MOD(15, 6) MODULO", //
            "POWER; POWER; 2; 10; SELECT POWER(2, 10) POWER" //
    }, delimiter = ';')
    // This method has different delimiter because arguments contain commas.
    void testScalarFunctionWithTwoIntegers(final String functionName, final String columnName, final int first,
            final int second, final String expected) {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.valueOf(functionName), columnName, integerLiteral(first),
                        integerLiteral(second));
        assertThat(select, rendersTo(expected));
    }

    @ParameterizedTest
    @CsvSource({ "COS, 3, SELECT COS((PI()/3))", //
            "TAN, 4, SELECT TAN((PI()/4))" //
    })
    void testScalarFunctionWithBinaryArithmetic(final String functionName, final int integerLiteral,
            final String expected) {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.valueOf(functionName),
                        divide(function(ExasolScalarFunction.PI), integerLiteral(integerLiteral)));
        assertThat(select, rendersTo(expected));
    }

    @Test
    void testScalarFunctionDegrees() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.DEGREES, function(ExasolScalarFunction.PI));
        assertThat(select, rendersTo("SELECT DEGREES(PI())"));
    }

    @Test
    void testScalarFunctionPi() {
        final Select select = StatementFactory.getInstance().select().function(ExasolScalarFunction.PI);
        assertThat(select, rendersTo("SELECT PI()"));
    }

    @Test
    void testScalarFunctionRandom() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.RANDOM, "RANDOM_1") //
                .function(ExasolScalarFunction.RANDOM, "RANDOM_2", integerLiteral(5), integerLiteral(20));
        assertThat(select, rendersTo("SELECT RANDOM() RANDOM_1, RANDOM(5, 20) RANDOM_2"));
    }

    @Test
    void testScalarFunctionRound() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.ROUND, "ROUND", doubleLiteral(123.456), integerLiteral(2));
        assertThat(select, rendersTo("SELECT ROUND(123.456, 2) ROUND"));
    }

    @Test
    void testScalarFunctionToChar() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.TO_CHAR, "TO_CHAR", doubleLiteral(12345.67890),
                        stringLiteral("9999999.999999999"));
        assertThat(select, rendersTo("SELECT TO_CHAR(12345.6789, '9999999.999999999') TO_CHAR"));
    }

    @Test
    void testScalarFunctionToNumber() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.TO_NUMBER, "TO_NUMBER1", stringLiteral("+123")) //
                .function(ExasolScalarFunction.TO_NUMBER, "TO_NUMBER2", stringLiteral("-123.45"),
                        stringLiteral("99999.999"));
        assertThat(select,
                rendersTo("SELECT TO_NUMBER('+123') TO_NUMBER1, TO_NUMBER('-123.45', '99999.999') TO_NUMBER2"));
    }

    @ParameterizedTest
    @CsvSource({ //
            "ASCII, , X, SELECT ASCII('X')", //
            "BIT_LENGTH, BIT_LENGTH, äöü, SELECT BIT_LENGTH('äöü') BIT_LENGTH", //
            "CHARACTER_LENGTH, C_LENGTH, aeiouäöü, SELECT CHARACTER_LENGTH('aeiouäöü') C_LENGTH", //
            "COLOGNE_PHONETIC, , schmitt, SELECT COLOGNE_PHONETIC('schmitt')",
            "DUMP, DUMP, 123abc, SELECT DUMP('123abc') DUMP", //
            "INITCAP, INITCAP, ExAsOl is great, SELECT INITCAP('ExAsOl is great') INITCAP", //
            "LCASE, LCASE, AbCdEf, SELECT LCASE('AbCdEf') LCASE", //
            "LOWER, , AbCdEf, SELECT LOWER('AbCdEf')", //
            "OCTET_LENGTH, OCT_LENGTH, abcd, SELECT OCTET_LENGTH('abcd') OCT_LENGTH", //
            "REVERSE, REVERSE, abcde, SELECT REVERSE('abcde') REVERSE", //
            "SOUNDEX, , smythe, SELECT SOUNDEX('smythe')", //
            "UCASE, UCASE, AbCdEf, SELECT UCASE('AbCdEf') UCASE", //
            "UNICODE, UNICODE, ä, SELECT UNICODE('ä') UNICODE", //
            "UPPER, UPPER, AbCdEf, SELECT UPPER('AbCdEf') UPPER" //
    })
    void testScalarFunctionWithString(final String functionName, final String columnName, final String literalString,
            final String expected) {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.valueOf(functionName), columnName, stringLiteral(literalString));
        assertThat(select, rendersTo(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "CONCAT; abc; def; SELECT CONCAT('abc', 'def')", //
            "EDIT_DISTANCE; schmitt; Schmidt; SELECT EDIT_DISTANCE('schmitt', 'Schmidt')", //
            "INSTR; abcabcabc; cab; SELECT INSTR('abcabcabc', 'cab')", //
            "LOCATE; cab; abcabcabc; SELECT LOCATE('cab', 'abcabcabc')", //
            "LTRIM; ab cdef; ab; SELECT LTRIM('ab cdef', 'ab')", //
            "REGEXP_INSTR; Phone: +497003927877678; \\+?\\d+; SELECT REGEXP_INSTR('Phone: +497003927877678', '\\+?\\d+')", //
            "REGEXP_REPLACE; my_mail@yahoo.com; [a-z0-9._%+-]; SELECT REGEXP_REPLACE('my_mail@yahoo.com', '[a-z0-9._%+-]')", //
            "REGEXP_SUBSTR; my_mail@yahoo.com; [a-z0-9._%+-]; SELECT REGEXP_SUBSTR('my_mail@yahoo.com', '[a-z0-9._%+-]')", //
            "RTRIM; abcdef; afe; SELECT RTRIM('abcdef', 'afe')" //
    }, delimiter = ';')
    void testScalarFunctionWithTwoStrings(final String functionName, final String first, final String second,
            final String expected) {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.valueOf(functionName), stringLiteral(first), stringLiteral(second));
        assertThat(select, rendersTo(expected));
    }

    @Test
    void testScalarFunctionInsert() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.INSERT, stringLiteral("abc"), integerLiteral(2), integerLiteral(2),
                        stringLiteral("xxx")) //
                .function(ExasolScalarFunction.INSERT, stringLiteral("abcdef"), integerLiteral(3), integerLiteral(2),
                        stringLiteral("CD"));
        assertThat(select, rendersTo("SELECT INSERT('abc', 2, 2, 'xxx'), INSERT('abcdef', 3, 2, 'CD')"));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "LEFT; abcdef; 3; SELECT LEFT('abcdef', 3)", //
            "REPEAT; abc; 3; SELECT REPEAT('abc', 3)", //
            "RIGHT; abcdef; 3; SELECT RIGHT('abcdef', 3)" //
    }, delimiter = ';')
    void testScalarFunctionStringAndInteger(final String functionName, final String stringLiteral,
            final int integerLiteral, final String expected) {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.valueOf(functionName), stringLiteral(stringLiteral),
                        integerLiteral(integerLiteral));
        assertThat(select, rendersTo(expected));
    }

    @Test
    void testScalarFunctionLength() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.LENGTH, column("s"));
        select.from().table("t");
        assertThat(select, rendersTo("SELECT LENGTH(s) FROM t"));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "LPAD; abc; 5; X; SELECT LPAD('abc', 5, 'X')", //
            "RPAD; abc; 5; X; SELECT RPAD('abc', 5, 'X')" //
    }, delimiter = ';')
    void testScalarFunctionPad(final String functionName, final String stringfirstLiteral, final int integerLiteral,
            final String stringSecondLiteral, final String expected) {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.valueOf(functionName), stringLiteral(stringfirstLiteral),
                        integerLiteral(integerLiteral), stringLiteral(stringSecondLiteral));
        assertThat(select, rendersTo(expected));
    }

    @Test
    void testScalarFunctionMid() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.MID, "MID", stringLiteral("abcdef"), integerLiteral(2),
                        integerLiteral(3));
        assertThat(select, rendersTo("SELECT MID('abcdef', 2, 3) MID"));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "REPLACE; Apple juice is great; Apple; Orange; SELECT REPLACE('Apple juice is great', 'Apple', 'Orange')", //
            "TRANSLATE; abcd; abc; xy; SELECT TRANSLATE('abcd', 'abc', 'xy')" //
    }, delimiter = ';')
    void testScalarFunctionThreeStrings(final String functionName, final String first, final String second,
            final String third, final String expected) {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.valueOf(functionName), stringLiteral(first), stringLiteral(second),
                        stringLiteral(third));
        assertThat(select, rendersTo(expected));
    }

    @Test
    void testScalarFunctionSubstr() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.SUBSTR, "S1", stringLiteral("abcdef"), integerLiteral(2),
                        integerLiteral(3));
        assertThat(select, rendersTo("SELECT SUBSTR('abcdef', 2, 3) S1"));
    }

    @Test
    void testScalarFunctionDateToChar() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.TO_CHAR, "TO_CHAR", stringLiteral("1999-12-31"));
        assertThat(select, rendersTo("SELECT TO_CHAR('1999-12-31') TO_CHAR"));
    }

    @Test
    void testScalarFunctionTrim() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.TRIM, stringLiteral("abcdef"), stringLiteral("acf"));
        assertThat(select, rendersTo("SELECT TRIM('abcdef', 'acf')"));
    }

    @Test
    void testScalarFunctionAddYears() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.ADD_YEARS, "AY1", stringLiteral("2000-02-29"), integerLiteral(1)) //
                .function(ExasolScalarFunction.ADD_YEARS, "AY2", stringLiteral("2005-01-31 12:00:00"),
                        integerLiteral(-1));
        assertThat(select,
                rendersTo("SELECT ADD_YEARS('2000-02-29', 1) AY1, ADD_YEARS('2005-01-31 12:00:00', -1) AY2"));
    }

    @Test
    void testScalarFunctionWithoutBrackets() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.SYSDATE);
        assertThat(select, rendersTo("SELECT SYSDATE"));
    }

    @Test
    void testScalarFunctionCoalesce() {
        final Select select = StatementFactory.getInstance().select() //
                .function(ExasolScalarFunction.COALESCE, "COALES", nullLiteral(), stringLiteral("abc"), nullLiteral(),
                        stringLiteral("xyz"));
        assertThat(select, rendersTo("SELECT COALESCE(NULL, 'abc', NULL, 'xyz') COALES"));
    }
}