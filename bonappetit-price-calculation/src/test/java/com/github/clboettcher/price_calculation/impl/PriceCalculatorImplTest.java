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
package com.github.clboettcher.price_calculation.impl;

import com.gihub.clboettcher.price_calculation.api.entity.*;
import com.gihub.clboettcher.price_calculation.impl.PriceCalculatorImpl;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link PriceCalculatorImpl}.
 */
public class PriceCalculatorImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private PriceCalculatorImpl priceCalculator;

    @Before
    public void setUp() throws Exception {
        this.priceCalculator = new PriceCalculatorImpl();
    }

    @Test
    public void testItemWithoutOptions() throws Exception {
        ItemOrderPrices order = ItemOrderPrices.builder()
                .price(new BigDecimal("3.125"))
                .build();
        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("3.13")));
    }

    @Test
    public void testValueOptionWithValueZeroHasNoEffect() throws Exception {
        ItemOrderPrices order = ItemOrderPrices.builder()
                .price(new BigDecimal("3.00"))
                .optionOrderPrices(Lists.<OptionOrderPrices>newArrayList(ValueOptionOrderPrices.builder()
                        .value(0)
                        .priceDiff(BigDecimal.ONE)
                        .build()
                ))
                .build();

        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("3.00")));
    }

    @Test
    public void testUncheckedCheckboxHasNoEffect() throws Exception {
        ItemOrderPrices order = ItemOrderPrices.builder()
                .price(new BigDecimal("3.00"))
                .optionOrderPrices(Lists.newArrayList(
                        CheckboxOptionOrderPrices.builder()
                                .checked(false)
                                .priceDiff(new BigDecimal("0.5"))
                                .build()
                ))
                .build();

        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("3.00")));
    }

    @Test
    public void testMixedOptions() throws Exception {
        ItemOrderPrices order = ItemOrderPrices.builder()
                .price(new BigDecimal("3.00"))
                .optionOrderPrices(Lists.newArrayList(
                        ValueOptionOrderPrices.builder()
                                .value(2)
                                .priceDiff(BigDecimal.ONE)
                                .build(),
                        CheckboxOptionOrderPrices.builder()
                                .checked(true)
                                .priceDiff(new BigDecimal("0.5"))
                                .build(),
                        RadioOptionOrderPrices.builder()
                                .selectedItemPriceDiff(new BigDecimal("-0.25"))
                                .build()
                ))
                .build();

        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("5.25")));
    }

    @Test
    public void testUnknownOptionType() throws Exception {
        ItemOrderPrices itemOrder = ItemOrderPrices.builder()
                .price(BigDecimal.ONE)
                .optionOrderPrices(Lists.<OptionOrderPrices>newArrayList(
                        new OptionOrderPrices() {
                        }
                ))
                .build();
        expectedException.expect(IllegalArgumentException.class);
        priceCalculator.calculateTotalPrice(itemOrder);
    }
}
