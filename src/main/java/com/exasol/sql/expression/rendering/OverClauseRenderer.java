package com.exasol.sql.expression.rendering;

import com.exasol.sql.dql.select.OrderByClause;
import com.exasol.sql.expression.function.exasol.OverClause;
import com.exasol.sql.expression.function.exasol.OverClause.PartitionClause;
import com.exasol.sql.expression.function.exasol.OverClause.WindowFrameClause;
import com.exasol.sql.rendering.StringRendererConfig;

class OverClauseRenderer extends AbstractExpressionRenderer {

    OverClauseRenderer(final StringRendererConfig config) {
        super(config);
    }

    public void visit(final OverClause overClause) {
        append(" ");
        appendKeyword("OVER");
        append("(");
        if (overClause.getWindowName() != null) {
            append(overClause.getWindowName());
        }
        if (overClause.getPartitionClause() != null) {
            appendPartition(overClause.getPartitionClause());
        }
        if (overClause.getOrderByClause() != null) {
            appendOrderBy(overClause.getOrderByClause());
        }
        if (overClause.getWindowFrameClause() != null) {
            appendWindowFrame(overClause.getWindowFrameClause());
        }
        append(")");
    }

    private void appendPartition(final PartitionClause partitionClause) {
        // TODO Auto-generated method stub

    }

    private void appendOrderBy(final OrderByClause orderByClause) {
        // TODO Auto-generated method stub

    }

    private void appendWindowFrame(final WindowFrameClause windowFrameClause) {
        // TODO Auto-generated method stub

    }
}
