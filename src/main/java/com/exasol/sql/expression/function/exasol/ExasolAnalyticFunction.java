package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.function.FunctionName;

/**
 * This class is a list of Analytic Functions that the Exasol database supports.
 *
 * @deprecated Use enum {@link ExasolAnalyticAggregateFunctions}.
 */
@Deprecated(since = "4.5", forRemoval = true)
public enum ExasolAnalyticFunction implements FunctionName {
    /** Any expression true */
    ANY,
    /** Every expression true */
    EVERY,
    /** Concatenated string of values of expression from all rows in the window or group */
    LISTAGG
}
