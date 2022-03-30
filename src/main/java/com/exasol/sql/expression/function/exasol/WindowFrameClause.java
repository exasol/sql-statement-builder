package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.ValueExpression;

/**
 * This represents a window frame clause of an analytic function in Exasol. See the
 * <a href="https://docs.exasol.com/sql_references/functions/analyticfunctions.htm">documentation</a> for details.
 */
public class WindowFrameClause {
    /**
     * Window frame clause
     */
    public enum WindowFrameType {
        /** Rows frame */
        ROWS,
        /** Range frame */
        RANGE,
        /** Groups frame */
        GROUPS
    }

    private WindowFrameClause.WindowFrameType type;
    private WindowFrameUnitClause unit1;
    private WindowFrameUnitClause unit2;
    private WindowFrameExclusionType exclusion;

    WindowFrameClause() {
        // package private constructor
    }

    /**
     * Set the type of this {@link WindowFrameClause}.
     *
     * @param type type of this {@link WindowFrameClause}
     * @return this {@link WindowFrameClause} for fluent programming
     */
    public WindowFrameClause type(final WindowFrameType type) {
        this.type = type;
        return this;
    }

    /**
     * Set the unit type of this {@link WindowFrameClause}.
     *
     * @param unitType unit type of this {@link WindowFrameClause}
     * @return this {@link WindowFrameClause} for fluent programming
     */
    public WindowFrameClause unit(final UnitType unitType) {
        return unit(null, unitType);
    }

    /**
     * Set the unit type of this {@link WindowFrameClause}.
     *
     * @param expression expression for the unit. Only required for unit types {@link UnitType#PRECEDING} and
     *                   {@link UnitType#FOLLOWING}
     * @param unitType   unit type of this {@link WindowFrameClause}
     * @return this {@link WindowFrameClause} for fluent programming
     */
    public WindowFrameClause unit(final ValueExpression expression, final UnitType unitType) {
        this.unit1 = new WindowFrameUnitClause(unitType, expression);
        return this;
    }

    /**
     * Set the unit type of this {@link WindowFrameClause} to {@code BETWEEN ... AND ...}.
     *
     * @param unitType1 {@code BETWEEN} unit
     * @param unitType2 {@code AND} unit
     * @return this {@link WindowFrameClause} for fluent programming
     */
    public WindowFrameClause unitBetween(final UnitType unitType1, final UnitType unitType2) {
        return this.unitBetween(null, unitType1, null, unitType2);
    }

    /**
     * Set the unit type of this {@link WindowFrameClause} to {@code BETWEEN ... AND ...}.
     *
     * @param unitType1   {@code BETWEEN} unit
     * @param expression1 {@code BETWEEN} expression. Only required for unit types {@link UnitType#PRECEDING} and
     *                    {@link UnitType#FOLLOWING}
     * @param unitType2   {@code AND} unit
     * @param expression2 {@code AND} expression. Only required for unit types {@link UnitType#PRECEDING} and
     *                    {@link UnitType#FOLLOWING}
     * @return this {@link WindowFrameClause} for fluent programming
     */
    public WindowFrameClause unitBetween(final ValueExpression expression1, final UnitType unitType1,
            final ValueExpression expression2, final UnitType unitType2) {
        this.unit1 = new WindowFrameUnitClause(unitType1, expression1);
        this.unit2 = new WindowFrameUnitClause(unitType2, expression2);
        return this;
    }

    /**
     * Set the exclusion type of this {@link WindowFrameClause}.
     *
     * @param exclusion exclusion type.
     * @return this {@link WindowFrameClause} for fluent programming
     */
    public WindowFrameClause exclude(final WindowFrameExclusionType exclusion) {
        this.exclusion = exclusion;
        return this;
    }

    /**
     * Get the window frame type.
     *
     * @return window frame type.
     */
    public WindowFrameClause.WindowFrameType getType() {
        return this.type;
    }

    /**
     * Get the {@code BETWEEN} unit.
     *
     * @return {@code BETWEEN} unit
     */
    public WindowFrameUnitClause getUnit1() {
        return this.unit1;
    }

    /**
     * Get the {@code AND} unit.
     *
     * @return {@code AND} unit
     */
    public WindowFrameUnitClause getUnit2() {
        return this.unit2;
    }

    /**
     * Get the exclusion type.
     *
     * @return exclusion type
     */
    public WindowFrameExclusionType getExclusion() {
        return this.exclusion;
    }

    /**
     * Represents a unit type.
     */
    public enum UnitType {
        /** Unbounded preceding */
        UNBOUNDED_PRECEDING("UNBOUNDED PRECEDING"),
        /** Unbounded following */
        UNBOUNDED_FOLLOWING("UNBOUNDED FOLLOWING"),
        /** Preceding */
        PRECEDING("PRECEDING"),
        /** Following */
        FOLLOWING("FOLLOWING"),
        /** Current row */
        CURRENT_ROW("CURRENT ROW");

        private final String sqlKeyword;

        private UnitType(final String sqlKeyword) {
            this.sqlKeyword = sqlKeyword;
        }

        /**
         * Get the keyword for rendering to SQL.
         *
         * @return keyword for rendering to SQL.
         */
        public String getSqlKeyword() {
            return this.sqlKeyword;
        }
    }

    /**
     * Represents a window frame unit.
     */
    public static class WindowFrameUnitClause {
        private final UnitType type;
        private final ValueExpression expression;

        private WindowFrameUnitClause(final UnitType type, final ValueExpression expression) {
            this.type = type;
            this.expression = expression;
        }

        /**
         * Get the type.
         *
         * @return type
         */
        public UnitType getType() {
            return this.type;
        }

        /**
         * Get the expression.
         *
         * @return expression
         */
        public ValueExpression getExpression() {
            return this.expression;
        }
    }

    /**
     * Represents the type of a window frame exclusion.
     */
    public enum WindowFrameExclusionType {
        /** Current row */
        CURRENT_ROW("CURRENT ROW"),
        /** Ties */
        TIES("TIES"),
        /** Group */
        GROUP("GROUP"),
        /** No others */
        NO_OTHERS("NO OTHERS");

        private final String sqlKeyword;

        private WindowFrameExclusionType(final String sqlKeyword) {
            this.sqlKeyword = sqlKeyword;
        }

        /**
         * Get the keyword for rendering to SQL.
         *
         * @return keyword for rendering to SQL.
         */
        public String getSqlKeyword() {
            return this.sqlKeyword;
        }
    }
}