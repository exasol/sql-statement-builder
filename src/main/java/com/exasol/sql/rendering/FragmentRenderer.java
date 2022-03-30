package com.exasol.sql.rendering;

/**
 * Common interface for SQL statement fragment renderers.
 */
public interface FragmentRenderer {
    /**
     * Render an SQL statement to a string.
     *
     * @return rendered string
     */
    public String render();
}