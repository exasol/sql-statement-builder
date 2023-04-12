package com.exasol.sql.expression.predicate;

/**
 * An interface for {@link Predicate} visitor.
 */
public interface PredicateVisitor {
    /**
     * Visit an is-null predicate.
     *
     * @param isNullPredicate predicate to visit
     */
    public void visit(final IsNullPredicate isNullPredicate);

    /**
     * Visit a value-in-constant-list predicate.
     *
     * @param inPredicate predicate to visit
     */
    public void visit(final InPredicate inPredicate);

    /**
     * Visit a value-exists predicate.
     *
     * @param existsPredicate predicate to visit
     */
    public void visit(final ExistsPredicate existsPredicate);

    /**
     * Visit a value-between predicate.
     *
     * @param betweenPredicate predicate to visit
     */
    public void visit(final BetweenPredicate betweenPredicate);
}