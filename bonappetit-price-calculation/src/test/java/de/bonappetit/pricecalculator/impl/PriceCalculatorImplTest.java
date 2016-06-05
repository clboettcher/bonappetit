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
package de.bonappetit.pricecalculator.impl;

import de.bonappetit.pricecalculator.api.PriceCalculator;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link PriceCalculatorImpl}.
 */
public class PriceCalculatorImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private PriceCalculator priceCalculator = new PriceCalculatorImpl();

    // TODO repair
//    @Test
//    public void testItemWithoutOptions() throws Exception {
//        ItemOrder order = ItemOrder.builder()
//                .price(new BigDecimal("3.125"))
//                .build();
//        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
//        assertThat(totalPrice, is(new BigDecimal("3.13")));
//    }

    // TODO repair
//    @Test
//    public void testValueOptionWithValueZeroHasNoEffect() throws Exception {
//        ItemOrder order = new ItemOrder(new BigDecimal("3.00"),
//                Lists.<OptionOrder>newArrayList(
//                        new ValueOptionOrder(0, BigDecimal.ONE)
//                )
//        );
//
//        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
//        assertThat(totalPrice, is(new BigDecimal("3.00")));
//    }

    // TODO repair
//    @Test
//    public void testMixedOptions() throws Exception {
//        ItemOrder order = new ItemOrder(new BigDecimal("3.00"),
//                Lists.newArrayList(
//                        new ValueOptionOrder(2, BigDecimal.ONE),
//                        new CheckboxOptionOrder(new BigDecimal("0.5"), true),
//                        new RadioOptionOrder(new BigDecimal("-0.25"))
//                )
//        );
//
//        BigDecimal totalPrice = priceCalculator.calculateTotalPrice(order);
//        assertThat(totalPrice, is(new BigDecimal("5.25")));
//    }

    // TODO repair
//    @Test
//    public void testUnknnownOptionType() throws Exception {
//        expectedException.expect(IllegalArgumentException.class);
//        priceCalculator.calculateTotalPrice(new ItemOrder(BigDecimal.ONE, Lists.<OptionOrder>newArrayList(new OptionOrder() {
//        })));
//    }
}
