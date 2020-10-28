package com.exasol.sql.expression.rendering;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.comparison.Comparison;
import com.exasol.sql.expression.comparison.ComparisonVisitor;
import com.exasol.sql.expression.comparison.LikeComparison;
import com.exasol.sql.expression.comparison.SimpleComparison;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * Renderer for {@link Comparison}s;
 */
public class ComparisonRenderer extends AbstractExpressionRenderer implements ComparisonVisitor {

    /**
     * Create a new instance of {@link ComparisonRenderer}.
     * 
     * @param config render configuration
     */
    public ComparisonRenderer(final StringRendererConfig config) {
        super(config);
    }

    @Override
    public void visit(final SimpleComparison simpleComparison) {
        openComparison(simpleComparison);
        closeComparison(simpleComparison);
    }

    @Override
    public void visit(final LikeComparison like) {
        openComparison(like);
        if (like.hasEscape()) {
            this.builder.append(" ESCAPE ");
            this.builder.append("'");
            this.builder.append(like.getEscape());
            this.builder.append("'");
        }
        closeComparison(like);
    }

    private void openComparison(final Comparison comparison) {
        connect(comparison);
        if (!comparison.isRoot()) {
            startParenthesis();
        }
        appendOperand(comparison.getLeftOperand());
        this.builder.append(" ");
        this.builder.append(comparison.getOperator().toString());
        this.builder.append(" ");
        appendOperand(comparison.getRightOperand());
    }

    private void closeComparison(final Comparison comparison) {
        if (!comparison.isRoot()) {
            endParenthesis();
        }
    }

    private void appendOperand(final ValueExpression leftOperand) {
        final ValueExpressionRenderer leftExpressionRenderer = new ValueExpressionRenderer(this.config);
        leftOperand.accept(leftExpressionRenderer);
        append(leftExpressionRenderer.render());
    }
}
