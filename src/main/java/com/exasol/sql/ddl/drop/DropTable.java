package com.exasol.sql.ddl.drop;

import com.exasol.sql.*;

public class DropTable extends AbstractFragment implements SqlStatement, DropTableFragment  {
    /**
     * Create an instance of an SQL fragment
     *
     * @param root root SQL statement this fragment belongs to.
     */
    public DropTable(final Fragment root) {
        super(root);
    }

    @Override
    public void accept(final DropTableVisitor visitor) {

    }
}
