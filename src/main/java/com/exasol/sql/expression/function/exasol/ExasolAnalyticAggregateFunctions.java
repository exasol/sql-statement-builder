package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.function.FunctionName;

/**
 * This enum is a list of <a href="https://docs.exasol.com/sql_references/functions/analyticfunctions.htm">Analytic</a>
 * and <a href="https://docs.exasol.com/sql_references/functions/aggregatefunctions.htm">Aggregate</a> Functions that
 * the Exasol database supports.
 */
public enum ExasolAnalyticAggregateFunctions implements FunctionName {
    /*
     * Analytic & aggregate functions
     */
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/any.htm">documentation</a>.
     */
    ANY,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/avg.htm">documentation</a>.
     */
    AVG,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/corr.htm">documentation</a>.
     */
    CORR,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/count.htm">documentation</a>.
     */
    COUNT,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/covar_pop.htm">documentation</a>.
     */
    COVAR_POP,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/covar_samp.htm">documentation</a>.
     */
    COVAR_SAMP,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/every.htm">documentation</a>.
     */
    EVERY,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/first_value.htm">documentation</a>.
     */
    FIRST_VALUE,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/group_concat.htm">documentation</a>.
     */
    GROUP_CONCAT,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/last_value.htm">documentation</a>.
     */
    LAST_VALUE,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/listagg.htm">documentation</a>.
     */
    LISTAGG,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/max.htm">documentation</a>.
     */
    MAX,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/median.htm">documentation</a>.
     */
    MEDIAN,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/min.htm">documentation</a>.
     */
    MIN,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/mul.htm">documentation</a>.
     */
    MUL,

    /*
     * REGR_FUNCTIONS
     */

    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/regr_function.htm">documentation</a>.
     */
    REGR_AVGX,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/regr_function.htm">documentation</a>.
     */
    REGR_AVGY,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/regr_function.htm">documentation</a>.
     */
    REGR_COUNT,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/regr_function.htm">documentation</a>.
     */
    REGR_INTERCEPT,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/regr_function.htm">documentation</a>.
     */
    REGR_R2,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/regr_function.htm">documentation</a>.
     */
    REGR_SLOPE,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/regr_function.htm">documentation</a>.
     */
    REGR_SXX,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/regr_function.htm">documentation</a>.
     */
    REGR_SXY,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/regr_function.htm">documentation</a>.
     */
    REGR_SYY,

    /**
     * This is an alias for {@link #ANY}. See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/some.htm">documentation</a>.
     */
    SOME,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/stddev.htm">documentation</a>.
     */
    STDDEV,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/stddev_pop.htm">documentation</a>.
     */
    STDDEV_POP,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/stddev_samp.htm">documentation</a>.
     */
    STDDEV_SAMP,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/sum.htm">documentation</a>.
     */
    SUM,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/var_pop.htm">documentation</a>.
     */
    VAR_POP,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/var_samp.htm">documentation</a>.
     */
    VAR_SAMP,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/variance.htm">documentation</a>.
     */
    VARIANCE,

    /*
     * Analytic functions
     */

    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/cume_dist.htm">documentation</a>.
     */
    CUME_DIST,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/dense_rank.htm">documentation</a>.
     */
    DENSE_RANK,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/lag.htm">documentation</a>.
     */
    LAG,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/lead.htm">documentation</a>.
     */
    LEAD,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/nth_value.htm">documentation</a>.
     */
    NTH_VALUE,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/ntile.htm">documentation</a>.
     */
    NTILE,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/percent_rank.htm">documentation</a>.
     */
    PERCENT_RANK,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/percentile_cont.htm">documentation</a>.
     */
    PERCENTILE_CONT,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/percentile_disc.htm">documentation</a>.
     */
    PERCENTILE_DISC,
    /**
     * See
     * <a href="https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/rank.htm">documentation</a>.
     */
    RANK,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/ratio_to_report.htm">documentation</a>.
     */
    RATIO_TO_REPORT,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/row_number.htm">documentation</a>.
     */
    ROW_NUMBER,

    /*
     * Aggregate functions
     */

    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/approximate_count_distinct.htm">documentation</a>.
     */
    APPROXIMATE_COUNT_DISTINCT,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/grouping%5B_id%5D.htm">documentation</a>.
     */
    GROUPING,
    /**
     * See <a href=
     * "https://docs.exasol.com/sql_references/functions/alphabeticallistfunctions/grouping%5B_id%5D.htm">documentation</a>.
     */
    GROUPING_ID
}
