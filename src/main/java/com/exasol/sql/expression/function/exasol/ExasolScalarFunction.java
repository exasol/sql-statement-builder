package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.function.FunctionName;

/**
 * This class is a list of Scalar Function that Exasol database supports.
 * 
 * Currently unsupported functions: SPACE
 */
public enum ExasolScalarFunction implements FunctionName {
    // Numeric functions
    ABS, ACOS, ASIN, ATAN, ATAN2, CEIL, COS, COSH, COT, DEGREES, DIV, EXP, FLOOR, LN, LOG, LOG10, LOG2, MOD, PI, POWER,
    RADIANS, RANDOM, ROUND, SIGN, SIN, SINH, SQRT, TAN, TANH, TO_CHAR, TO_NUMBER, TRUNC,
    // String functions
    ASCII, BIT_LENGTH, CHARACTER_LENGTH, CHAR, COLOGNE_PHONETIC, CONCAT, DUMP, EDIT_DISTANCE, INITCAP, INSERT, INSTR,
    LCASE, LEFT, LENGTH, LOCATE, LOWER, LPAD, LTRIM, MID, OCTET_LENGTH, POSITION, REGEXP_INSTR, REGEXP_REPLACE,
    REGEXP_SUBSTR, REPEAT, REPLACE, REVERSE, RIGHT, RPAD, RTRIM, SOUNDEX, SUBSTR, TRANSLATE, TRIM, UCASE, UNICODE,
    UNICODECHR, UPPER
}
