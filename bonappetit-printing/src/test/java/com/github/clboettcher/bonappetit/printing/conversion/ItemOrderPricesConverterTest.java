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

public class ItemOrderPricesConverterTest extends AbstractConverterTest {
    // TODO  repair
//
////    private ItemOrderConverter converter = new ItemOrderConverterImpl(new OrderOptionConverterImpl());
//
//    /**
//     * Item order for item without options
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testConversionWithoutOptions() throws Exception {
//
//        // menu.addToItems(new Item(name: "Burger", price: new BigDecimal("3.0"), type: ItemType.FOOD))
//        final GregorianCalendar orderTime = (GregorianCalendar) GregorianCalendar.getInstance();
//        ItemOrder o = new ItemOrderBuilder()
//                .setItem(new ItemBuilder()
//                        .setName("Testitem")
//                        .setPrice(new BigDecimal("9.99"))
//                        .setType(ItemType.FOOD)
//                        .createItem())
//                .setCustomer("Testcustomer")
//                .setOverwriteItemPrice(new BigDecimal("8.88"))
//                .setOverwriteItemName("New Itemname")
//                .setNote("Note")
//                .setOrderTime(orderTime)
//                .setStaffMemberName("Staffmember")
//                .createItemOrder();
//
//        final Set<Bon> bonList = converter.toBonList(Sets.newHashSet(o));
//        assertThat(bonList.size(), is(1));
//        Bon b = bonList.toArray(new Bon[bonList.size()])[0];
//        assertThat(b.getCustomer(), is("Testcustomer"));
//        assertThat(b.getItemName(), is("Testitem"));
//        assertThat(b.getItemType(), is(ItemType.FOOD));
//        assertThat(b.getCustomer(), is("Testcustomer"));
//        assertThat(b.getNote(), is("Note"));
//        assertThat(b.getOrderTime(), is(orderTime));
//        assertThat(b.getStaffMemberName(), is("Staffmember"));
//        assertThat(b.getEmphasisedOptions(), is(Collections.<String>emptySet()));
//        assertThat(b.getDefaultOptions(), is(Collections.<String>emptySet()));
//    }
//
//    @Test
//    public void testConversionWithOptions() throws Exception {
//        Set<OrderOption> orderOptions = Sets.newLinkedHashSet();
//
//        orderOptions.add(orderForIntegerOption(integerOption("Integer-Option", PrintStrategy.EMPHASISE), 1337));
//        orderOptions.add(orderForRadioItem(radioItem("Radio-Item", PrintStrategy.DEFAULT)));
//
//        ItemOrder o = new ItemOrderBuilder()
//                .setItem(new ItemBuilder()
//                        .setName("Item")
//                        .createItem())
//                .setOrderOptions(orderOptions)
//                .createItemOrder();
//
//        final Set<Bon> bons = converter.toBonList(Sets.newHashSet(o));
//        assertThat(bons.size(), is(1));
//        Bon b = bons.toArray(new Bon[bons.size()])[0];
//        assertThat(b.getEmphasisedOptions(), containsInAnyOrder("Integer-Option (1337x)"));
//        assertThat(b.getDefaultOptions(), containsInAnyOrder("Radio-Item"));
//    }
//
//    @Test
//    public void testOneOneHalfConversion() throws Exception {
//        ItemOrder oneOneHalf = new ItemOrderBuilder()
//                .setItem(new ItemBuilder()
//                        .setName(Item.ITEM_NAME_ONE_ONE_HALF)
//                        .createItem())
//                .createItemOrder();
//
//        final Set<Bon> bons = converter.toBonList(Sets.newHashSet(oneOneHalf));
//        assertThat(bons.size(), is(2));
//        boolean foundFood = false;
//        boolean foundDrink = false;
//
//        for (Bon bon : bons) {
//            if ("1+1/2 (Getränke-Bon)".equals(bon.getItemName())) {
//                foundFood = true;
//            } else if ("1+1/2 (Speise-Bon)".equals(bon.getItemName())) {
//                foundDrink = true;
//            }
//        }
//
//        assertTrue(foundDrink);
//        assertTrue(foundFood);
//
////        assertThat(bons, hasItem(Matchers.<Bon>hasProperty("itemName", equalTo()))); // // TODO find dependency issue and use this method
////        assertThat(bons, hasItem(Matchers.<Bon>hasProperty("itemName", equalTo())));
//    }
}
