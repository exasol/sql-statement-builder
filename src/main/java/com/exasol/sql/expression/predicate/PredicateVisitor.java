package com.exasol.sql.expression.predicate;

/**
 * An interface for {@link Predicate} visitor.
 */
public interface PredicateVisitor {

    public void visit(IsNullPredicate isNullPredicate);

    public void visit(InPredicate inPredicate);

}
