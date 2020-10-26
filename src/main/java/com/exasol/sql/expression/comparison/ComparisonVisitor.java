package com.exasol.sql.expression.comparison;

/**
 * Visitor for {@link Comparison}.
 */
public interface ComparisonVisitor {
    public void visit(SimpleComparison simpleComparison);

    public void visit(LikeComparison like);
}
