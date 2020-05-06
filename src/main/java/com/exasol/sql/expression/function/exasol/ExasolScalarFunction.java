package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.function.FunctionName;

/**
 * This class is a list of Scalar Function that Exasol database supports.
 */
public enum ExasolScalarFunction implements FunctionName {
    // Numeric functions
    ABS, ACOS, ASIN, ATAN, ATAN2, CEIL, COS, COSH, COT, DEGREES, DIV, EXP, FLOOR, LN, LOG, LOG10, LOG2, MOD, PI, POWER,
    RADIANS, RANDOM, ROUND, SIGN, SIN, SINH, SQRT, TAN, TANH, TO_CHAR, TO_NUMBER, TRUNC,
    // String functions
    LENGTH
}
