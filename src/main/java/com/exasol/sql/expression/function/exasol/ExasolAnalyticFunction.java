package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.function.FunctionName;

/**
 * This class is a list of Analytic Functions that the Exasol database supports.
 *
 * @deprecated Use enum {@link ExasolAnalyticAggregateFunctions}.
 */
@Deprecated(since = "4.5", forRemoval = true)
public enum ExasolAnalyticFunction implements FunctionName {
    ANY, EVERY, LISTAGG
}
