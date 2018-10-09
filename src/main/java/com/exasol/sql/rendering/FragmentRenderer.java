package com.exasol.sql.rendering;

public interface FragmentRenderer {
    /**
     * Render an SQL statement to a string.
     *
     * @return rendered string
     */
    public String render();
}