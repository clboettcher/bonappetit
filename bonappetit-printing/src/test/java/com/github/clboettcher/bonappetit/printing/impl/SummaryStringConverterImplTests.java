/*
 * Copyright (c) 2016 Claudius Boettcher (pos.bonappetit@gmail.com)
 *
 * This file is part of BonAppetit. BonAppetit is an Android based
 * Point-of-Sale client-server application for small restaurants.
 *
 * BonAppetit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BonAppetit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BonAppetit.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.clboettcher.bonappetit.printing.impl;

import com.github.clboettcher.bonappetit.common.BonAppetitResourceUtils;
import com.github.clboettcher.bonappetit.printing.config.ConfigProvider;
import com.github.clboettcher.bonappetit.printing.util.DateFormatter;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderSummaryDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderSummaryDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.SummaryDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.SummaryEntryDto;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link SummaryStringConverterImpl}.
 */
public class SummaryStringConverterImplTests {

    private SummaryStringConverterImpl converter;

    @Before
    public void setUp() throws Exception {
        ConfigProvider configProvider = Mockito.mock(ConfigProvider.class);
        Mockito.when(configProvider.getDateFormatter()).thenReturn(new DateFormatter(DateTimeZone.UTC));

        this.converter = new SummaryStringConverterImpl(
                new ControlCharProviderConsoleTestImpl(),
                new SpecialCharEncoderCITIZENCT310IIGermanImpl(),
                configProvider);
    }

    @Test
    public void testMinimalSummary() throws Exception {
        SummaryDto input = SummaryDto.builder()
                .totalPrice(BigDecimal.ZERO)
                .orderSummaries(new ArrayList<>())
                .orderCount(0L)
                .build();

        String actual = converter.toString(input);
        String expected = BonAppetitResourceUtils.readFileContentAsString("testdata/summary/summary-minimal.txt");
        assertThat(actual, is(expected));
    }

    @Test
    public void testSingleOrderWithoutOptions() throws Exception {
        SummaryDto input = SummaryDto.builder()
                .totalPrice(new BigDecimal("2.5"))
                .orderSummaries(Lists.newArrayList(
                        SummaryEntryDto.builder()
                                .count(1L)
                                .orderSummary(ItemOrderSummaryDto.builder()
                                        .itemTitle("Pommes")
                                        .totalPrice(new BigDecimal("2.5"))
                                        .build())
                                .build()
                ))
                .orderCount(1L)
                .oldestOrderTime(DateTime.parse("2016-12-15T09:38:00.000Z"))
                .newestOrderTime(DateTime.parse("2016-12-16T01:45:00.000Z"))
                .build();

        String actual = converter.toString(input);
        String expected = BonAppetitResourceUtils.readFileContentAsString("testdata/summary/summary-single-order.txt");
        assertThat(actual, is(expected));
    }

    @Test
    public void testMultipleOrdersWithOptions() throws Exception {
        SummaryDto input = SummaryDto.builder()
                .totalPrice(new BigDecimal("39.00"))
                .orderSummaries(Lists.newArrayList(
                        SummaryEntryDto.builder()
                                .count(2L)
                                .orderSummary(ItemOrderSummaryDto.builder()
                                        .itemTitle("Pommes")
                                        .totalPrice(new BigDecimal("2.5"))
                                        .optionOrders(Lists.newArrayList(
                                                OptionOrderSummaryDto.builder().optionTitle("Gross").build(),
                                                OptionOrderSummaryDto.builder().optionTitle("Ketchup").build(),
                                                OptionOrderSummaryDto.builder().optionTitle("Mayonnaise").build()
                                        ))
                                        .build())
                                .build(),
                        SummaryEntryDto.builder()
                                .count(10L)
                                .orderSummary(ItemOrderSummaryDto.builder()
                                        .itemTitle("Cola")
                                        .totalPrice(new BigDecimal("2.2"))
                                        .optionOrders(Lists.newArrayList(
                                                OptionOrderSummaryDto.builder().optionTitle("Gross").build()
                                        ))
                                        .build())
                                .build(),
                        SummaryEntryDto.builder()
                                .count(8L)
                                .orderSummary(ItemOrderSummaryDto.builder()
                                        .itemTitle("Mineralwasser")
                                        .totalPrice(new BigDecimal("1.5"))
                                        .build())
                                .build()
                ))
                .orderCount(20L)
                .oldestOrderTime(DateTime.parse("2016-12-15T09:38:00.000Z"))
                .newestOrderTime(DateTime.parse("2016-12-16T01:45:00.000Z"))
                .build();

        String actual = converter.toString(input);
        String expected = BonAppetitResourceUtils.readFileContentAsString("testdata/summary/summary-multiple-orders.txt");
        assertThat(actual, is(expected));
    }
}
