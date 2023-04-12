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
    /** Default join */
    DEFAULT(""),
    /** Inner join ( (x) ) */
    INNER("INNER"),
    /** Left join (x(x) )*/
    LEFT("LEFT"),
    /** Right join ( (x)x) */
    RIGHT("RIGHT"),
    /** Full join (x(x)x) */
    FULL("FULL"),
    /** Left outer join (x( ) ) */
    LEFT_OUTER("LEFT OUTER"),
    /** Right outer join ( ( )x) */
    RIGHT_OUTER("RIGHT OUTER"),
    /** Full outer jon (x( )x) */
    FULL_OUTER("FULL OUTER");

    private final String text;

    private JoinType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}