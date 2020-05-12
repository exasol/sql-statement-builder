package com.exasol.sql.dql.select;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.rendering.SelectRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

class TestSelect {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
    }

    @Test
    void testLimitTwiceThrowsException() {
        this.select.limit(1);
        assertThrows(IllegalStateException.class, () -> this.select.limit(2));
    }

    @Test
    void testLimitWithOffsetTwiceThrowsException() {
        this.select.limit(1, 2);
        assertThrows(IllegalStateException.class, () -> this.select.limit(2, 3));
    }
}