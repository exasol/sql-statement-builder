package com.exasol.sql.expression.rendering;

import static com.exasol.errorreporting.ExaError.messageBuilder;

import java.util.List;
import java.util.function.Consumer;

import com.exasol.sql.dql.select.OrderByClause;
import com.exasol.sql.dql.select.rendering.SelectRenderer;
import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.function.exasol.OverClause;
import com.exasol.sql.expression.function.exasol.WindowFrameClause;
import com.exasol.sql.expression.function.exasol.WindowFrameClause.UnitType;
import com.exasol.sql.expression.function.exasol.WindowFrameClause.WindowFrameUnitClause;
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
        if (overClause.getPartitionByColumns() != null) {
            appendPartition(overClause.getPartitionByColumns());
        }
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
        appendKeyword(windowFrameClause.getType().name());
        append(" ");
        if (windowFrameClause.getUnit1() == null) {
            throw new IllegalStateException(messageBuilder("E-ESB-1")
                    .message("First unit not defined. At lease one unit is required for a window frame").toString());
        }
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
        if ((unit.getType() == UnitType.PRECEEDING) || (unit.getType() == UnitType.FOLLOWING)) {
            if (unit.getExpression() == null) {
                throw new IllegalStateException(messageBuilder("E-ESB-2").message(
                        "Expression is missing. An expression is required for unit types PRECEEDING and FOLLOWING")
                        .toString());
            }
            render(renderer -> renderer.visit(unit.getExpression()));
            append(" ");
        }
        appendKeyword(unit.getType().getSqlKeyword());
    }
}
