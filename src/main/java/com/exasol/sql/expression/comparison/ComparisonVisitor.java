package com.exasol.sql.expression.comparison;

/**
 * Visitor for {@link Comparison}.
 */
public interface ComparisonVisitor {
    /**
     * Visit a simple comparison (e.g. equality).
     *
     * @param simpleComparison comparison to visit
     */
    public void visit(SimpleComparison simpleComparison);

    /**
     * Visit a {@code LIKE} comparison.
     *
     * @param like comparison to visit
     */
    public void visit(LikeComparison like);
}
