package com.exasol.sql;

import com.exasol.sql.ddl.create.CreateTableVisitor;
import com.exasol.sql.ddl.drop.DropTableVisitor;
import com.exasol.sql.dml.insert.InsertVisitor;
import com.exasol.sql.dml.merge.MergeVisitor;
import com.exasol.sql.dql.select.SelectVisitor;

/**
 * Represents a {@link Table} in an SQL Statement.
 */
public class Table extends AbstractFragment {
    private final String name;
    private final String alias;

    /**
     * Create a new {@link Table} with a name and an alias.
     *
     * @param root SQL statement this table belongs to
     * @param name table name
     */
    public Table(final Fragment root, final String name) {
        super(root);
        this.name = name;
        this.alias = null;
    }

    /**
     * Create a new {@link Table} with a name and an alias.
     *
     * @param root SQL statement this table belongs to
     * @param name table name
     * @param alias table alias
     */
    public Table(final Fragment root, final String name, final String alias) {
        super(root);
        this.name = name;
        this.alias = alias;
    }

    /**
     * Get the name of the table
     *
     * @return table name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the correlation name (i.e. an alias) of the table.
     *
     * @return correlation name
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * Check if a correlation name (i.a. an alias) is present.
     *
     * @return {@code true} if a correlation name is present
     */
    public boolean hasAlias() {
        return this.alias != null;
    }

    /**
     * Accept a {@link CreateTableVisitor}.
     *
     * @param visitor visitor to accept
     */
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Accept a {@link DropTableVisitor}.
     *
     * @param visitor visitor to accept
     */
    public void accept(final DropTableVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Accept a {@link MergeVisitor}.
     *
     * @param visitor visitor to accept
     */
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Accept an {@link InsertVisitor}.
     *
     * @param visitor visitor to accpet
     */
    public void accept(final InsertVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Accept a {@link SelectVisitor}.
     *
     * @param visitor visitor to accept
     */
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
    }
}