package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.function.FunctionName;

/**
 * This class is a list of Analytic Functions that the Exasol database supports.
 *
 * @deprecated since {@code 4.5}, use enum {@link ExasolAnalyticAggregateFunctions}.
 */
@Deprecated
public enum ExasolAnalyticFunction implements FunctionName {
    /** Any expression true */
    ANY,
    /** Every expression true */
    EVERY,
    /** Concatenated string of values of expression from all rows in the window or group */
    LISTAGG
}
