package com.exasol.sql.dql;

import com.exasol.sql.*;

/**
 * This class implements the {@link Join} part of a WHERE clause.
 */
public class Join extends AbstractFragment implements Fragment {
    private final JoinType type;
    private final String name;
    private final String specification;

    /**
     * Create a new {@link Join} instance
     *
     * @param parent        parent {@link Fragment}
     * @param type          type of join (e.g. INNER, LEFT or RIGHT OUTER)
     * @param name          name of the table to be joined
     * @param specification join specification (e.g. ON or USING)
     */
    public Join(final Fragment parent, final JoinType type, final String name, final String specification) {
        super(parent);
        this.type = type;
        this.name = name;
        this.specification = specification;
    }

    /**
     * Get the type of the join
     * 
     * @return join type (e.g. INNER or LEFT)
     */
    public JoinType getType() {
        return this.type;
    }

    /**
     * Get the name of the joined table
     *
     * @return name of the joined table
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the join specification
     *
     * @return join specification
     */
    public String getSpecification() {
        return this.specification;
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}
