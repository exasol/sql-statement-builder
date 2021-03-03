package com.exasol.sql.expression.predicate;

import com.exasol.sql.dql.select.Select;

/**
 * A class that represents a {@code EXISTS} predicate.
 */
// [impl->dsn~predicate-operators~1]
public class ExistsPredicate extends AbstractPredicate {
    private final Select selectQuery;

    /**
     * Creates a new instance of {@link ExistsPredicate}.
     *
     * @param selectQuery sub select query
     */
    public ExistsPredicate(final Select selectQuery) {
        super(ExistsPredicateOperator.EXISTS);
        this.selectQuery = selectQuery;
    }

    /**
     * Returns the sub select query in the {@code EXISTS} predicate.
     *
     * @return sub select query
     */
    public Select getSelectQuery() {
        return selectQuery;
    }

    /**
     * An operator for {@link ExistsPredicate} class.
     */
    public enum ExistsPredicateOperator implements PredicateOperator {
        EXISTS;

        @Override
        public String toString() {
            return super.toString().replace("_", " ");
        }
    }

    @Override
    public void accept(final PredicateVisitor visitor) {
        visitor.visit(this);
    }

}
