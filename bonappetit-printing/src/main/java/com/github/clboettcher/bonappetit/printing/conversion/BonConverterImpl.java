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

import com.github.clboettcher.bonappetit.printing.entity.Bon;
import com.github.clboettcher.bonappetit.printing.entity.OptionOrderStrings;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderDto;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts item orders to bons.
 */
@Component
public class BonConverterImpl implements BonConverter {

    @Autowired
    private OptionOrderStringsConverter optionOrderStringsConverter;

    @Override
    public List<Bon> toBons(List<ItemOrderDto> orders) {
        Preconditions.checkNotNull(orders, "orders");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(orders), "orders must not be empty");

        List<Bon> result = Lists.newArrayList();
        for (ItemOrderDto order : orders) {
            result.addAll(toBons(order));
        }

        return result;
    }

    private List<Bon> toBons(ItemOrderDto order) {
        Bon bon = new Bon();

        // Order properties
        bon.setStaffMemberName(String.format("%s %s",
                order.getStaffMemberFirstName(),
                order.getStaffMemberLastName()));
        bon.setDeliverTo(order.getDeliverTo());
        bon.setNote(order.getNote());
        bon.setOrderTime(order.getOrderTime());

        final OptionOrderStrings optionOrderStrings = optionOrderStringsConverter.convert(order.getOptionOrders());
        bon.setEmphasisedOptions(optionOrderStrings.getEmphasisedOptions());
        bon.setDefaultOptions(optionOrderStrings.getDefaultOptions());

        // Item properties
        bon.setItemTitle(order.getItemTitle());
        bon.setItemType(order.getItemType());

        final ArrayList<Bon> result = Lists.newArrayList(bon);

        // Two Bons are printed for 1+1/2
        // Note: this is special treatment for a menu item that is in use in a restaurant where
        // this software is used.
        // TODO: model special bon treatment as hooks that are not part of the codebase.
        if ("1+1/2".equals(bon.getItemTitle())) {
            // Add separate bon for the food part of 1+1/2
            final Bon foodBon = new Bon(bon);
            foodBon.setItemTitle(String.format("%s (Speise-Bon)", foodBon.getItemTitle()));
            result.add(foodBon);

            // Add discriminator for the two 1/1+2 bons.
            bon.setItemTitle(String.format("%s (Getränke-Bon)", bon.getItemTitle()));
        }

        return result;
    }
}
