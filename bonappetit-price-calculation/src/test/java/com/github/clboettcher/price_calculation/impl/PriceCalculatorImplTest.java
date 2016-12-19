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

import com.gihub.clboettcher.bonappetit.price_calculation.impl.PriceCalculatorImpl;
import com.github.clboettcher.bonappetit.server.menu.api.dto.common.ItemDtoType;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.*;
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
        BigDecimal foodDiscount = new BigDecimal("0.75");
        BigDecimal drinkDiscount = new BigDecimal("0.5");
        priceCalculator = new PriceCalculatorImpl(foodDiscount, drinkDiscount);
    }

    @Test
    public void testItemWithoutOptions() throws Exception {
        ItemOrderDto order = ItemOrderDto.builder()
                .itemPrice(new BigDecimal("3.125"))
                .build();
        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("3.13")));
    }

    @Test
    public void testValueOptionWithValueZeroHasNoEffect() throws Exception {
        ItemOrderDto order = ItemOrderDto.builder()
                .itemPrice(new BigDecimal("3.00"))
                .optionOrders(Lists.<OptionOrderDto>newArrayList(ValueOptionOrderDto.builder()
                        .value(0)
                        .optionPriceDiff(BigDecimal.ONE)
                        .build()
                ))
                .build();

        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("3.00")));
    }

    @Test
    public void testUncheckedCheckboxHasNoEffect() throws Exception {
        ItemOrderDto order = ItemOrderDto.builder()
                .itemPrice(new BigDecimal("3.00"))
                .optionOrders(Lists.<OptionOrderDto>newArrayList(
                        CheckboxOptionOrderDto.builder()
                                .checked(false)
                                .optionPriceDiff(new BigDecimal("0.5"))
                                .build()
                ))
                .build();

        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("3.00")));
    }

    @Test
    public void testMixedOptions() throws Exception {
        ItemOrderDto order = ItemOrderDto.builder()
                .itemPrice(new BigDecimal("3.00"))
                .optionOrders(Lists.newArrayList(
                        ValueOptionOrderDto.builder()
                                .value(2)
                                .optionPriceDiff(BigDecimal.ONE)
                                .build(),
                        CheckboxOptionOrderDto.builder()
                                .checked(true)
                                .optionPriceDiff(new BigDecimal("0.5"))
                                .build(),
                        RadioOptionOrderDto.builder()
                                .selectedRadioItemPriceDiff(new BigDecimal("-0.25"))
                                .build()
                ))
                .build();

        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
        assertThat(totalPrice, is(new BigDecimal("5.25")));
    }

    @Test
    public void testUnknownOptionType() throws Exception {
        ItemOrderDto itemOrder = ItemOrderDto.builder()
                .itemPrice(BigDecimal.ONE)
                .optionOrders(Lists.<OptionOrderDto>newArrayList(
                        new OptionOrderDto() {
                        }
                ))
                .build();
        expectedException.expect(IllegalArgumentException.class);
        priceCalculator.calculateTotalPrice(itemOrder);
    }

    @Test
    public void testFoodDiscountForStaffMembers() throws Exception {
        BigDecimal actual = priceCalculator.calculateTotalPrice(ItemOrderDto.builder()
                .itemType(ItemDtoType.FOOD)
                .itemPrice(BigDecimal.TEN)
                .customer(StaffMemberCustomerDto.builder().build())
                .build());
        assertThat(actual, is(new BigDecimal("7.50")));
    }

    @Test
    public void testDrinkDiscountForStaffMembers() throws Exception {
        ItemOrderDto input = ItemOrderDto.builder()
                .itemType(ItemDtoType.DRINK_ALCOHOLIC)
                .itemPrice(BigDecimal.TEN)
                .customer(StaffMemberCustomerDto.builder().build())
                .build();
        BigDecimal actual = priceCalculator.calculateTotalPrice(input);
        assertThat(actual, is(new BigDecimal("5.00")));

        input.setItemType(ItemDtoType.DRINK_NON_ALCOHOLIC);
        actual = priceCalculator.calculateTotalPrice(input);
        assertThat(actual, is(new BigDecimal("5.00")));
    }

    @Test
    public void testNoFoodDiscountsForOtherCustomers() throws Exception {
        ItemOrderDto input = ItemOrderDto.builder()
                .itemType(ItemDtoType.FOOD)
                .itemPrice(BigDecimal.TEN)
                .customer(FreeTextCustomerDto.builder().build())
                .build();
        BigDecimal actual = priceCalculator.calculateTotalPrice(input);
        assertThat(actual, is(new BigDecimal("10.00")));

        input.setCustomer(TableCustomerDto.builder().build());
        actual = priceCalculator.calculateTotalPrice(input);
        assertThat(actual, is(new BigDecimal("10.00")));
    }

    @Test
    public void testNoDrinkDiscountsForOtherCustomers() throws Exception {
        ItemOrderDto input = ItemOrderDto.builder()
                .itemType(ItemDtoType.DRINK_ALCOHOLIC)
                .itemPrice(BigDecimal.TEN)
                .customer(FreeTextCustomerDto.builder().build())
                .build();
        BigDecimal actual = priceCalculator.calculateTotalPrice(input);
        assertThat(actual, is(new BigDecimal("10.00")));

        input.setCustomer(TableCustomerDto.builder().build());
        input.setItemType(ItemDtoType.DRINK_NON_ALCOHOLIC);
        actual = priceCalculator.calculateTotalPrice(input);
        assertThat(actual, is(new BigDecimal("10.00")));
    }
}
