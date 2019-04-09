package com.exasol.sql.dql.select;

/**
 * This class represents the {@link Join} types supported by SQL.
 *
 * <pre>
 * DEFAULT = INNER     : ( (*) )
 * LEFT = LEFT_OUTER   : (*(*) )
 * RIGHT = RIGHT_OUTER : ( (*)*)
 * FULL = FULL_OUTER   : (*(*)*)
 * </pre>
 */
public enum JoinType {
    DEFAULT(""), INNER("INNER"), LEFT("LEFT"), RIGHT("RIGHT"), FULL("FULL"), LEFT_OUTER("LEFT OUTER"),
    RIGHT_OUTER("RIGHT OUTER"), FULL_OUTER("FULL OUTER");

    private final String text;

    private JoinType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}