package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.function.FunctionName;

/**
 * This class is a list of Aggregate Functions that the Exasol database supports.
 *
 * @deprecated Use enum {@link ExasolAnalyticAggregateFunctions}.
 */
@Deprecated(since = "4.5.0", forRemoval = true)
public enum ExasolAggregateFunction implements FunctionName {
    /** Faster, but only approximate count of distinct values  */
    APPROXIMATE_COUNT_DISTINCT,
    /** Average */
    AVG,
    /** Correlation coefficient */
    CORR,
    /** Precise count */
    COUNT,
    /** Population covariance */
    COVAR_POP,
    /** Sample covariance */
    COVAR_SAMP,
    /** First value in set */
    FIRST_VALUE,
    /** Group concatenation */
    GROUP_CONCAT,
    /** Last value in set */
    LAST_VALUE,
    /** Maximum value */
    MAX,
    /** Median */
    MEDIAN,
    /** Minimum Value */
    MIN,
    /** Slope of the regression line */
    REGR_SLOPE,
    /** y-Intercept of the regression line */
    REGR_INTERCEPT,
    /** Number of non-null number pairs */
    REGR_COUNT,
    /** Coefficient of determination (goodness of fit) */
    REGR_R2,
    /** Average of the independent values (x values) */
    REGR_AVGX,
    /** Average of the independent values (y values) */
    REGR_AVGY,
    /** Auxiliary function SXX */
    REGR_SXX,
    /** Auxiliary function SXY */
    REGR_SXY,
    /** Auxiliary function SYY */
    REGR_SYY,
    /** Standard deviation */
    STDDEV,
    /** Standard deviation of expr within a window or group of rows */
    STDDEV_POP,
    /** Standard deviation of expr within a window or group of rows */
    STDDEV_SAMP,
    /** Sum */
    SUM,
    /** Variance of expr within a window or group of rows */
    VAR_POP,
    /** Variance of expr within a window or group of rows */
    VAR_SAMP,
    /** Variance */
    VARIANCE;
}
