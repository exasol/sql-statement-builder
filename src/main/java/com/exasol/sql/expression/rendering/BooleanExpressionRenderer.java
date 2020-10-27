package com.exasol.sql.expression.rendering;

import com.exasol.sql.expression.*;
import com.exasol.sql.expression.comparison.Comparison;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * Renderer for {@link BooleanExpression}s.
 */
public class BooleanExpressionRenderer extends AbstractExpressionRenderer implements BooleanExpressionVisitor {
    public BooleanExpressionRenderer(final StringRendererConfig config) {
        super(config);
    }

    public BooleanExpressionRenderer() {
        this(StringRendererConfig.builder().build());
    }

    @Override
    public void visit(final Not not) {
        connect(not);
        appendKeyword("NOT");
        startParenthesis();
    }

    @Override
    public void leave(final Not not) {
        endParenthesis();
    }

    @Override
    public void visit(final And and) {
        connect(and);
        this.connectorDeque.push(" AND ");
        if (!and.isRoot()) {
            startParenthesis();
        }
    }

    @Override
    public void leave(final And and) {
        if (!and.isRoot()) {
            endParenthesis();
        }
        this.connectorDeque.pop();
    }

    @Override
    public void visit(final Or or) {
        connect(or);
        this.connectorDeque.push(" OR ");
        if (!or.isRoot()) {
            startParenthesis();
        }
    }

    @Override
    public void leave(final Or or) {
        if (!or.isRoot()) {
            endParenthesis();
        }
        this.connectorDeque.pop();
    }

    @Override
    public void visit(final BooleanLiteral literal) {
        connect(literal);
        appendBooleanLiteral(literal);
    }

    @Override
    public void leave(final BooleanLiteral literal) {
        // intentionally empty
    }

    @Override
    public void visit(final Comparison comparison) {
        connect(comparison);
        final ComparisonRenderer comparisonRenderer = new ComparisonRenderer(this.config);
        comparison.accept(comparisonRenderer);
        append(comparisonRenderer.render());
    }

    @Override
    public void leave(final Comparison comparison) {
        // intentionally empty
    }
}