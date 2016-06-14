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

/**
 * Does not test anything, just for viewing the strings (on system out) that would be printed.
 */
public class PosPrinterInspection {
    // TODO repair
//
//    private PosPrinter printer;
//
//    @Before
//    public void setUp() throws Exception {
//        final OrderOptionConverter orderOptionConverter = new OrderOptionConverterImpl();
//        final ItemOrderConverter itemOrderConverter = new ItemOrderConverterImpl(orderOptionConverter);
//        final ControlCharProviderConsoleTestImpl controlCharProvider = new ControlCharProviderConsoleTestImpl();
//        printer = new PrintManagerConsoleTestImpl(itemOrderConverter, new PhysicalPrinterImpl(controlCharProvider));
//    }
//
//    @Test
//    public void inspectPrinting() throws Exception {
//        final Item item = createItem("Pommes & Burger");
//        ItemOrder order = createOrder(item);
//        ItemOrder order2 = createOrder(item);
//        printer.printOrders(Sets.newHashSet(order, order2));
//    }
//
//    @Test
//    public void inspectOneOneHalf() throws Exception {
//        Item item = createItem(Item.ITEM_NAME_ONE_ONE_HALF);
//        ItemOrder order = createOrder(item);
//        printer.printOrders(Sets.newHashSet(order));
//    }
//
////    @Test       // TODO fixit
////    public void inspectAugustiner() throws Exception {
////        final Option augustinerOptions = new OptionBuilder()
////                .setName("Geschmack")
////                .setType(OptionType.RADIO)
////                .setRadioItems(Sets.newHashSet(
////                        new RadioItemBuilder()
////                                .setIndex(0)
////                                .setName("Hell")
////                                .createRadioItem(),
////                        new RadioItemBuilder()
////                                .setIndex(1)
////                                .setName("Edelstoff")
////                                .createRadioItem()
////                ))
////                .createOption();
////
////        final Item augustingerHell = new ItemBuilder()
////                .setName("Augustiner")
////                .setType(ItemType.DRINK_ALCOHOLIC)
////                .setPrice(new BigDecimal("2.5"))
////                .addToOptions(augustinerOptions)
////                .createItem();
////
////        ItemOrder orderHell = new ItemOrderBuilder()
////                .setItem(augustingerHell)
////                .setCustomer("Customer")
////                .setStaffMemberName("Staff-Member")
////                .setOrderTime((GregorianCalendar) GregorianCalendar.getInstance())
////                .setOrderOptions(Sets.newLinkedHashSet(Lists.newArrayList(
////                        new OrderOptionBuilder()
////                                .setOption(augustinerOptions)
////                                .setValue(0)
////                                .createOrderOption()
////                )))
////                .createItemOrder();
////
////        ItemOrder orderEdelstoff = new ItemOrderBuilder()
////                .setItem(augustingerHell)
////                .setCustomer("Customer")
////                .setStaffMemberName("Staff-Member")
////                .setOrderTime((GregorianCalendar) GregorianCalendar.getInstance())
////                .setOrderOptions(Sets.newLinkedHashSet(Lists.newArrayList(
////                        new OrderOptionBuilder()
////                                .setOption(augustinerOptions)
////                                .setValue(1)
////                                .createOrderOption()
////                )))
////                .createItemOrder();
////
////        printer.print(Lists.newArrayList(orderHell, orderEdelstoff));
////    }
//
//    private ItemOrder createOrder(Item forItem) {
//        final ItemOrder itemOrder = new ItemOrder();
//        itemOrder.setItem(forItem);
//        itemOrder.setStaffMemberName("Claudius");
//        itemOrder.setNote("Note");
//        itemOrder.setOrderTime((GregorianCalendar) GregorianCalendar.getInstance());
//        itemOrder.setOrderOptions(createOrderOptions());
//        itemOrder.setCustomer("Tisch 15");
//        return itemOrder;
//    }
//
//    private Set<OrderOption> createOrderOptions() {
//        return null;
//    }
//
//    private Item createItem(String name) {
//        Item item = new Item();
//
//        item.setName(name);
//        item.setPrice(new BigDecimal("2.5"));
//        item.setType(ItemType.FOOD);
//
//        return item;
//    }
}
