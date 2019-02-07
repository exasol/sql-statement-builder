package com.exasol.sql.dql;

import java.util.*;

import javax.annotation.Generated;

import com.exasol.sql.*;
import com.exasol.sql.expression.StringLiteral;
import com.exasol.sql.expression.ValueExpression;

/**
 * This class represents a row in a {@link ValueTable}.
 */
// [impl->dsn~value-table~1]
public class ValueTableRow extends AbstractFragment {
    private final List<ValueExpression> expressions;

    /**
     * Create a value table row from a list of expressions
     *
     * @param root root node of the SQL statement
     *
     * @param literals value expressions
     */
    public ValueTableRow(final Fragment root, final ValueExpression... literals) {
        super(root);
        this.expressions = Arrays.asList(literals);
    }

    /**
     * Create a value table row from a list of value literals
     *
     * @param root root node of the SQL statement
     *
     * @param literals
     */
    public ValueTableRow(final Fragment root, final String... literals) {
        super(root);
        this.expressions = new ArrayList<ValueExpression>(literals.length);
        for (final String literal : literals) {
            this.expressions.add(StringLiteral.of(literal));
        }
    }

    /**
     * Get the list of expressions the row consists of
     *
     * @return list of expressions
     */
    public List<ValueExpression> getExpressions() {
        return this.expressions;
    }

    @Generated("org.eclipse.Eclipse")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.expressions == null) ? 0 : this.expressions.hashCode());
        return result;
    }

    @Generated("org.eclipse.Eclipse")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ValueTableRow other = (ValueTableRow) obj;
        if (this.expressions == null) {
            if (other.expressions != null) {
                return false;
            }
        } else if (!this.expressions.equals(other.expressions)) {
            return false;
        }
        return true;
    }

    public void accept(final FragmentVisitor visitor) {
        visitor.visit(this);
        visitor.leave(this);
    }
}