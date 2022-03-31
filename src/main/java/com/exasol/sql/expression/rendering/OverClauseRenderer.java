package com.exasol.sql.expression.rendering;

import java.util.List;
import java.util.function.Consumer;

import com.exasol.sql.dql.select.OrderByClause;
import com.exasol.sql.dql.select.rendering.SelectRenderer;
import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.function.exasol.OverClause;
import com.exasol.sql.expression.function.exasol.WindowFrameClause;
import com.exasol.sql.expression.function.exasol.WindowFrameClause.*;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * A renderer for {@link OverClause}.
 */
class OverClauseRenderer extends AbstractExpressionRenderer {

    OverClauseRenderer(final StringRendererConfig config) {
        super(config);
    }

    void visit(final OverClause overClause) {
        append(" ");
        appendKeyword("OVER");
        append("(");
        if (overClause.getWindowName() != null) {
            append(overClause.getWindowName());
        }
        appendPartition(overClause.getPartitionByColumns());
        if (overClause.getOrderByClause() != null) {
            appendOrderBy(overClause.getOrderByClause());
        }
        if (overClause.getWindowFrameClause() != null) {
            appendWindowFrame(overClause.getWindowFrameClause());
        }
        append(")");
    }

    private void appendPartition(final List<ValueExpression> columns) {
        if ((columns == null) || columns.isEmpty()) {
            return;
        }
        appendKeyword(" PARTITION BY ");
        render(renderer -> renderer.visit(columns));
    }

    private void render(final Consumer<ValueExpressionRenderer> action) {
        final ValueExpressionRenderer valueExpressionRenderer = new ValueExpressionRenderer(this.config);
        action.accept(valueExpressionRenderer);
        append(valueExpressionRenderer.render());
    }

    private void appendOrderBy(final OrderByClause orderByClause) {
        final SelectRenderer selectRenderer = new SelectRenderer(this.config);
        orderByClause.accept(selectRenderer);
        append(selectRenderer.render());
    }

    private void appendWindowFrame(final WindowFrameClause windowFrameClause) {
        append(" ");
        final WindowFrameType type = windowFrameClause.getType();
        if (type == null) {
            throw new IllegalStateException("Type not defined. Set type the window frame.");
        }
        appendKeyword(type.name());
        if (windowFrameClause.getUnit1() == null) {
            throw new IllegalStateException("First unit not defined."
                    + " At least one unit is required for a window frame.");
        }
        append(" ");
        if (windowFrameClause.getUnit2() == null) {
            renderUnit(windowFrameClause.getUnit1());
        } else {
            appendKeyword("BETWEEN ");
            renderUnit(windowFrameClause.getUnit1());
            appendKeyword(" AND ");
            renderUnit(windowFrameClause.getUnit2());
        }
        if (windowFrameClause.getExclusion() != null) {
            appendKeyword(" EXCLUDE ");
            appendKeyword(windowFrameClause.getExclusion().getSqlKeyword());
        }
    }

    private void renderUnit(final WindowFrameUnitClause unit) {
        if ((unit.getType() == UnitType.PRECEDING) || (unit.getType() == UnitType.FOLLOWING)) {
            if (unit.getExpression() == null) {
                throw new IllegalStateException("Expression is required for window frame units PRECEDING and FOLLOWING."
                        + " Add expression for unit types PRECEDING and FOLLOWING.");
            }
            render(renderer -> renderer.visit(unit.getExpression()));
            append(" ");
        }
        appendKeyword(unit.getType().getSqlKeyword());
    }
}
