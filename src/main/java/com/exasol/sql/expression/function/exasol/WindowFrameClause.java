package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.function.exasol.WindowFrameClause.WindowFrameUnitClause.UnitType;

public class WindowFrameClause {

    public enum WindowFrameType {
        ROWS, RANGE, GROUPS
    }

    private WindowFrameClause.WindowFrameType type;
    private WindowFrameUnitClause unit1;
    private WindowFrameUnitClause unit2;
    private WindowFrameExclusionType exclusion;

    WindowFrameClause() {
    }

    public WindowFrameClause type(final WindowFrameType type) {
        this.type = type;
        return this;
    }

    public WindowFrameClause unit(final UnitType unitType) {
        return unit(null, unitType);
    }

    public WindowFrameClause unit(final ValueExpression expression, final UnitType unitType) {
        this.unit1 = new WindowFrameUnitClause(unitType, expression);
        return this;
    }

    public WindowFrameClause unitBetween(final UnitType unitType1, final UnitType unitType2) {
        return this.unitBetween(null, unitType1, null, unitType2);
    }

    public WindowFrameClause unitBetween(final ValueExpression expression1, final UnitType unitType1,
            final ValueExpression expression2, final UnitType unitType2) {
        this.unit1 = new WindowFrameUnitClause(unitType1, expression1);
        this.unit2 = new WindowFrameUnitClause(unitType2, expression2);
        return this;
    }

    public WindowFrameClause exclude(final WindowFrameExclusionType exclusion) {
        this.exclusion = exclusion;
        return this;
    }

    public WindowFrameClause.WindowFrameType getType() {
        return this.type;
    }

    public WindowFrameUnitClause getUnit1() {
        return this.unit1;
    }

    public WindowFrameUnitClause getUnit2() {
        return this.unit2;
    }

    public WindowFrameExclusionType getExclusion() {
        return this.exclusion;
    }

    public static class WindowFrameUnitClause {

        public enum UnitType {

            UNBOUNDED_PRECEEDING("UNBOUNDED PRECEEDING"), UNBOUNDED_FOLLOWING("UNBOUNDED FOLLOWING"),
            PRECEEDING("PRECEEDING"), FOLLOWING("FOLLOWING"), CURRENT_ROW("CURRENT ROW");

            private final String keyword;

            private UnitType(final String keyword) {
                this.keyword = keyword;
            }

            public String getKeyword() {
                return this.keyword;
            }
        }

        private final UnitType type;
        private final ValueExpression expression;

        WindowFrameUnitClause(final UnitType type, final ValueExpression expression) {
            this.type = type;
            this.expression = expression;
        }

        public UnitType getType() {
            return this.type;
        }

        public ValueExpression getExpression() {
            return this.expression;
        }
    }

    public enum WindowFrameExclusionType {
        CURRENT_ROW("CURRENT ROW"), TIES("TIES"), GROUP("GROUP"), NO_OTHERS("NO OTHERS");

        private final String keyword;

        private WindowFrameExclusionType(final String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }
    }

}