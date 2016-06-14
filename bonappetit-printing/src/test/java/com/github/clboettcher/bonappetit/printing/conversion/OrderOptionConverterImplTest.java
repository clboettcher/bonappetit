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
package com.github.clboettcher.bonappetit.printing.conversion;


import com.github.clboettcher.bonappetit.printing.AbstractConverterTest;

public class OrderOptionConverterImplTest extends AbstractConverterTest {
    // TODO  repair
//    private OrderOptionConverter converter = new OrderOptionConverterImpl();
//
//    /**
//     * Tests that all not not printed order options are filtered.
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testFilterNotPrintedOrderOptions() throws Exception {
//
//        Set<OrderOption> orderOptions = Sets.newLinkedHashSet();
//
//        // Some options are not printed no matter what strategy is set
//        // Unchecked checkbox option
//        orderOptions.add(orderForCheckboxOption(checkboxOption("Checkbox-Option", PrintStrategy.DEFAULT), false));
//        // Integer option with value == 0
//        orderOptions.add(orderForIntegerOption(integerOption("Integer, Option", PrintStrategy.DEFAULT), 0));
//
//        // Orders for options that are not printed by strategy
//        orderOptions.add(orderForCheckboxOption(checkboxOption("Checkbox-Option", PrintStrategy.NOT_PRINTED), true));
//        orderOptions.add(orderForIntegerOption(integerOption("Integer-Option", PrintStrategy.NOT_PRINTED), 1337));
//        orderOptions.add(orderForRadioItem(radioItem("Radio-Item", PrintStrategy.NOT_PRINTED)));
//
//        final OrderOptionStrings optionStrings = converter.convert(orderOptions);
//        assertThat(optionStrings.getEmphasisedOptions(), empty());
//        assertThat(optionStrings.getDefaultOptions(), empty());
//    }
//
//    @Test
//    public void testEmphasisedOptions() throws Exception {
//        Set<OrderOption> orderOptions = Sets.newLinkedHashSet();
//
//        orderOptions.add(orderForCheckboxOption(checkboxOption("Checkbox-Option", PrintStrategy.EMPHASISE), true));
//        orderOptions.add(orderForIntegerOption(integerOption("Integer-Option", PrintStrategy.EMPHASISE), 1337));
//        orderOptions.add(orderForRadioItem(radioItem("Radio-Item", PrintStrategy.EMPHASISE)));
//
//        final OrderOptionStrings optionStrings = converter.convert(orderOptions);
//        assertThat(optionStrings.getEmphasisedOptions().size(), is(3));
//        assertThat(optionStrings.getEmphasisedOptions(), containsInAnyOrder(
//                "Checkbox-Option", "Integer-Option (1337x)", "Radio-Item"));
//        assertThat(optionStrings.getDefaultOptions(), empty());
//    }
//
//    @Test
//    public void testDefaultOptions() throws Exception {
//        Set<OrderOption> orderOptions = Sets.newLinkedHashSet();
//
//        orderOptions.add(orderForCheckboxOption(checkboxOption("Checkbox-Option", PrintStrategy.DEFAULT), true));
//        orderOptions.add(orderForIntegerOption(integerOption("Integer-Option", PrintStrategy.DEFAULT), 1337));
//        orderOptions.add(orderForRadioItem(radioItem("Radio-Item", PrintStrategy.DEFAULT)));
//
//        final OrderOptionStrings optionStrings = converter.convert(orderOptions);
//        assertThat(optionStrings.getEmphasisedOptions(), empty());
//        assertThat(optionStrings.getDefaultOptions().size(), is(3));
//        assertThat(optionStrings.getDefaultOptions(), containsInAnyOrder(
//                "Checkbox-Option", "Integer-Option (1337x)", "Radio-Item"));
//    }
}