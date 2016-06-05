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

import com.gihub.clboettcher.price_calculation.impl.PriceCalculatorImpl;
import com.github.clboettcher.bonappetit.domain.menu.CheckboxOption;
import com.github.clboettcher.bonappetit.domain.menu.RadioItem;
import com.github.clboettcher.bonappetit.domain.menu.ValueOption;
import com.github.clboettcher.bonappetit.domain.order.*;
import com.google.common.collect.Sets;
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
        ItemOrder order = ItemOrder.builder()
                .price(new BigDecimal("3.125"))
                .build();
        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("3.13")));
    }

    @Test
    public void testValueOptionWithValueZeroHasNoEffect() throws Exception {
        ItemOrder order = ItemOrder.builder()
                .price(new BigDecimal("3.00"))
                .optionOrders(Sets.<OptionOrder>newHashSet(ValueOptionOrder.builder()
                        .value(0)
                        .option(ValueOption.builder()
                                .priceDiff(BigDecimal.ONE)
                                .build())
                        .build()
                ))
                .build();

        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("3.00")));
    }

    @Test
    public void testMixedOptions() throws Exception {
        ItemOrder order = ItemOrder.builder()
                .price(new BigDecimal("3.00"))
                .optionOrders(Sets.newHashSet(
                        ValueOptionOrder.builder()
                                .value(2)
                                .option(ValueOption.builder()
                                        .priceDiff(BigDecimal.ONE)
                                        .build())
                                .build(),
                        CheckboxOptionOrder.builder()
                                .option(CheckboxOption.builder()
                                        .priceDiff(new BigDecimal("0.5"))
                                        .build())
                                .build(),
                        RadioOptionOrder.builder()
                                .selectedItem(RadioItem.builder()
                                        .priceDiff(new BigDecimal("-0.25"))
                                        .build())
                                .build()
                ))
                .build();

        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("5.25")));
    }

    @Test
    public void testUnknownOptionType() throws Exception {
        ItemOrder itemOrder = ItemOrder.builder()
                .price(BigDecimal.ONE)
                .optionOrders(Sets.<OptionOrder>newHashSet(
                        new OptionOrder() {
                            @Override
                            public long getId() {
                                return super.getId();
                            }
                        }
                ))
                .build();
        expectedException.expect(IllegalArgumentException.class);
        priceCalculator.calculateTotalPrice(itemOrder);
    }
}
