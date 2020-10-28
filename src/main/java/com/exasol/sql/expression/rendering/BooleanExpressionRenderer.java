package com.exasol.sql.expression.rendering;

import com.exasol.sql.expression.*;
import com.exasol.sql.expression.comparison.Comparison;
import com.exasol.sql.expression.comparison.ComparisonVisitor;
import com.exasol.sql.expression.comparison.LikeComparison;
import com.exasol.sql.expression.comparison.SimpleComparison;
import com.exasol.sql.expression.literal.BooleanLiteral;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * Renderer for {@link BooleanExpression}s.
 */
public class BooleanExpressionRenderer extends AbstractExpressionRenderer
        implements BooleanExpressionVisitor, ComparisonVisitor {
    public BooleanExpressionRenderer(final StringRendererConfig config) {
        super(config);
    }

    public BooleanExpressionRenderer() {
        this(StringRendererConfig.builder().build());
    }

    @Override
    public void visit(final Not not) {
        appendKeyword("NOT");
        startParenthesis();
        not.getNegated().accept(this);
        endParenthesis();
    }

    @Override
    public void visit(final And and) {
        if (!and.isRoot()) {
            startParenthesis();
        }
        append(" AND ", and.getChildren().toArray(BooleanExpression[]::new));
        if (!and.isRoot()) {
            endParenthesis();
        }
    }

    private void append(final String separator, final BooleanExpression... expressions) {
        boolean isFirst = true;
        for (final BooleanExpression expression : expressions) {
            if (!isFirst) {
                appendKeyword(separator);
            }
            isFirst = false;
            expression.accept(this);
        }
    }

    @Override
    public void visit(final Or or) {
        if (!or.isRoot()) {
            startParenthesis();
        }
        append(" OR ", or.getChildren().toArray(BooleanExpression[]::new));
        if (!or.isRoot()) {
            endParenthesis();
        }
    }

    @Override
    public void visit(final BooleanLiteral literal) {
        appendBooleanLiteral(literal);
    }

    @Override
    public void visit(final Comparison comparison) {
        comparison.accept((ComparisonVisitor) this);
    }

    /*
     * Comparison renderer
     */
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