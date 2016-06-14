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

import com.github.clboettcher.bonappetit.printing.AbstractConverterTest;

/**
 * This test prints the bons using the real (physical) printer. Don't enable it
 * in normal builds.
 */
public class PosPrinterPhysicalPrintInspection extends AbstractConverterTest {
    // TODO  repair
//    private PosPrinter printer;
//
//    @Before
//    public void setUp() throws Exception {
//        ItemOrderConverter itemOrderConverter = new ItemOrderConverterImpl(new OrderOptionConverterImpl());
//        final PhysicalPrinter physicalPrinter = new PhysicalPrinterImpl(new ControlCharProviderPosImpl());
//        printer = new PrintManagerImpl(itemOrderConverter, physicalPrinter, physicalPrinter);
//    }
//
//    /**
//     * This test prints to the real printer! Enable with care!
//     */
////    @Test
//    public void testPrintSingleBonWithoutOptions() throws Exception {
//        final Item item = new ItemBuilder()
//                .setName("Simple Item")
//                .setPrice(BigDecimal.ONE)
//                .setType(ItemType.FOOD)
//                .createItem();
//
//        final ItemOrder order = new ItemOrderBuilder()
//                .setCustomer("Tisch 12")
//                .setNote("Viel Salz")
//                .setStaffMemberName("Claudius")
//                .setItem(item)
//                .setOrderTime((GregorianCalendar) GregorianCalendar.getInstance())
//                .createItemOrder();
//
//        printer.printOrders(Sets.newHashSet(order));
//    }
//
//    /**
//     * This test prints to the real printer! Enable with care!
//     */
////    @Test
//    public void testPrintTwoBonsWithoutOptions() throws Exception {
//        Item item = this.createItem("Simple Item");
//        ItemOrder order = this.orderForItem(item, "Viel Salz");
//        ItemOrder order2 = this.orderForItem(item, "Viel Salz");
//
//        printer.printOrders(Sets.newHashSet(order, order2));
//    }
//
//    /**
//     * This test prints to the real printer! Enable with care!
//     */
////    @Test
//    public void testPrintSingleBonWithOptions() throws Exception {
//        // Checkbox Options
//        final CheckboxOption checkboxOptionEm = this.checkboxOption("<em>Checkbox-Option</em>", PrintStrategy.EMPHASISE);
//        final CheckboxOption checkboxOptionDefault = this.checkboxOption("Checkbox-Option", PrintStrategy.DEFAULT);
//
//        // Radio Options
//        final RadioItem selectedInOrder1 = this.radioItem("<em>RadioItem default</em>", PrintStrategy.EMPHASISE);
//        final RadioItem selectedInOrder2 = this.radioItem("RadioItem other", PrintStrategy.DEFAULT);
//        RadioOption radioOption = this.radioOption("Radio-Option",
//                selectedInOrder1,
//                selectedInOrder2
//        );
//
//        // Integer Options
//        IntegerOption integerOptionEm = this.integerOption("<em>Integer-Option</em>", PrintStrategy.EMPHASISE);
//        IntegerOption integerOptionDefault = this.integerOption("Integer-Option", PrintStrategy.DEFAULT);
//
//
//        final Item item = this.createItem("Simple Item",
//                checkboxOptionEm,
//                checkboxOptionDefault,
//                radioOption,
//                integerOptionEm,
//                integerOptionDefault
//        );
//        final ItemOrder order1 = this.orderForItem(item, "Viel Salz",
//                orderForCheckboxOption(checkboxOptionEm, true),
//                orderForCheckboxOption(checkboxOptionDefault, true),
//                orderForRadioItem(selectedInOrder1),
//                orderForIntegerOption(integerOptionEm, 2),
//                orderForIntegerOption(integerOptionDefault, 1)
//        );
//
//        final ItemOrder order2 = this.orderForItem(item, null,
//                orderForCheckboxOption(checkboxOptionEm, false),
//                orderForCheckboxOption(checkboxOptionDefault, false),
//                orderForRadioItem(selectedInOrder2),
//                orderForIntegerOption(integerOptionEm, 0),
//                orderForIntegerOption(integerOptionDefault, 0)
//        );
//
//        printer.printOrders(Sets.newHashSet(order1, order2));
//    }
//
//    /**
//     * This test prints to the real printer! Enable with care!
//     */
////    @Test
//    public void testPrintSummary() throws Exception {
//        // Checkbox Options
//        final CheckboxOption checkboxOptionEm = this.checkboxOption("<em>Checkbox-Option</em>", PrintStrategy.EMPHASISE);
//        checkboxOptionEm.setPriceDiff(new BigDecimal("0.5"));
//
//        // Radio Options
//        final RadioItem selectedInOrder1 = this.radioItem("<em>RadioItem default</em>", PrintStrategy.EMPHASISE);
//        selectedInOrder1.setPriceDiff(new BigDecimal("3"));
//        RadioOption radioOption = this.radioOption("Radio-Option", selectedInOrder1);
//
//        // Integer Options
//        IntegerOption integerOptionEm = this.integerOption("<em>Integer-Option</em>", PrintStrategy.EMPHASISE);
//        integerOptionEm.setPriceDiff(new BigDecimal("2.2"));
//
//
//        final Item item = this.createItem("Simple Item",
//                checkboxOptionEm,
//                radioOption,
//                integerOptionEm
//        );
//        item.setPrice(BigDecimal.TEN);
//
//        final ItemOrder order1 = this.orderForItem(item, "Viel Salz",
//                orderForCheckboxOption(checkboxOptionEm, true),
//                orderForRadioItem(selectedInOrder1),
//                orderForIntegerOption(integerOptionEm, 2)
//        );
//
//        // Total price = (10 + 2 * 2.2 + 3) / 2 = 8.7
//        printer.printSummary(Sets.newHashSet(order1), "FooBar");
//    }
}
