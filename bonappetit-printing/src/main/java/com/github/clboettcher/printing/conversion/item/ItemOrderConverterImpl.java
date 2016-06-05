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
package com.github.clboettcher.printing.conversion.item;

import com.github.clboettcher.bonappetit.domain.order.ItemOrder;
import com.github.clboettcher.printing.entity.Bon;

import java.util.Set;

/**
 * Converts item orders to bons.
 */
public class ItemOrderConverterImpl implements ItemOrderConverter {

    // TODO repair
//    private OrderOptionConverter orderOptionConverter;
//
//    public ItemOrderConverterImpl(OrderOptionConverter orderOptionConverter) {
//        this.orderOptionConverter = orderOptionConverter;
//    }
//
    @Override
    public Set<Bon> toBonList(Set<ItemOrder> orders) {
        throw new UnsupportedOperationException("Not yet implemented: convert to bons"); // TODO implement: convert to bons
//        Preconditions.checkNotNull(orders, "orders");
//        Preconditions.checkArgument(CollectionUtils.isNotEmpty(orders), "orders must not be empty");
//
//        Set<ItemOrder> ordersInput = Sets.newHashSet(orders);
//
//        Set<Bon> result = Sets.newHashSet();
//        for (ItemOrder order : ordersInput) {
//            result.addAll(toBonList(order));
//        }
//
//        return result;
//    }
//
//    private List<Bon> toBonList(ItemOrder order) {
//        Bon bon = new Bon();
//
//        // Order properties
//        bon.setStaffMemberName(order.getStaffMemberName());
//        bon.setCustomer(order.getCustomer());
//        bon.setNote(order.getNote());
//        bon.setOrderTime(order.getOrderTime());
//
//        final OrderOptionStrings orderOptionStrings = orderOptionConverter.convert(getOrderOptions(order));
//        bon.setEmphasisedOptions(orderOptionStrings.getEmphasisedOptions());
//        bon.setDefaultOptions(orderOptionStrings.getDefaultOptions());
//
//        // Item properties
//        final Item item = order.getItem();
//        bon.setItemName(item.getName());
//        bon.setItemType(item.getType());
//
//        final ArrayList<Bon> result = Lists.newArrayList(bon);
//
//        // Two Bons are printed for 1+1/2
//        if (Item.ITEM_NAME_ONE_ONE_HALF.equals(bon.getItemName())) {
//            final Bon foodBon = new Bon(bon);
//            result.add(foodBon);
//
//            // Add description to each bon
//            bon.setItemName(bon.getItemName() + " (Getränke-Bon)");
//            foodBon.setItemName(foodBon.getItemName() + " (Speise-Bon)");
//        }
//
//        return result;
    }
//
//    // TODO replace with no reflection code
//    @SuppressWarnings("unchecked")
//    private Set<OrderOption> getOrderOptions(ItemOrder order) {
//        return (Set<OrderOption>) InvokerHelper.invokeMethod(order, "getOrderOptions", InvokerHelper.EMPTY_ARGS);
//    }
}
