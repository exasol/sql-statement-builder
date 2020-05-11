package com.exasol.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.rendering.StringRendererConfig;

class QuotesApplierTest {
    @Test
    void testGetAutoQuotedWithQuotes() {
        StringRendererConfig config = StringRendererConfig.builder().quoteIdentifiers(true).build();
        QuotesApplier quotesApplier = new QuotesApplier(config);
        assertThat(quotesApplier.getAutoQuoted("col.tab"), equalTo("\"col\".\"tab\""));
    }

    @Test
    void testGetAutoQuotedWithoutQuotes() {
        StringRendererConfig config = StringRendererConfig.builder().quoteIdentifiers(false).build();
        QuotesApplier quotesApplier = new QuotesApplier(config);
        assertThat(quotesApplier.getAutoQuoted("col.tab"), equalTo("col.tab"));
    }
}