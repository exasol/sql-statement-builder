package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.function.FunctionName;

/**
 * This class is a list of Aggregate Functions that Exasol database supports.
 *
 * Currently unsupported functions: GROUPING, PERCENTILE_CONT, PERCENTILE_DISC, OVER clause for all aggregate functions,
 * functions' prefixes that goes after parenthesis.
 */
public enum ExasolAggregateFunction implements FunctionName {
    APPROXIMATE_COUNT_DISTINCT, AVG, CORR, COUNT, COVAR_POP, COVAR_SAMP, FIRST_VALUE, GROUP_CONCAT, LAST_VALUE, MAX,
    MEDIAN, MIN, REGR_SLOPE, REGR_INTERCEPT, REGR_COUNT, REGR_R2, REGR_AVGX, REGR_AVGY, REGR_SXX, REGR_SXY, REGR_SYY,
    STDDEV, STDDEV_POP, STDDEV_SAMP, SUM, VAR_POP, VAR_SAMP, VARIANCE;
}