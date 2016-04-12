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
package com.github.clboettcher.bonappetit.server.persistence;

import com.github.clboettcher.bonappetit.server.persistence.entity.builder.*;
import com.github.clboettcher.bonappetit.server.persistence.entity.menu.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

/**
 * This class bootstraps some testdata and will be deleted at some point.
 */
@Component
public class Bootstrap {

    private MenuEntity createMenu() {
        return MenuEntityBuilder.aMenuEntity()
                .withItems(Sets.newLinkedHashSet(Lists.newArrayList(
                        ItemEntityBuilder.anItemEntity()
                                .withTitle("Pommes")
                                .withPrice(new BigDecimal("2.5"))
                                .withType(ItemEntityType.FOOD)
                                .withOptions(Sets.<AbstractOptionEntity>newLinkedHashSet(Lists.newArrayList(
                                        ValueOptionEntityBuilder.aValueOptionEntity()
                                                .withTitle("Extra Salz")
                                                .withIndex(2)
                                                .withDefaultChecked(false)
                                                .withPriceDiff(BigDecimal.ZERO)
                                                .build()
                                )))
                                .build(),
                        ItemEntityBuilder.anItemEntity()
                                .withTitle("Nachos")
                                .withPrice(new BigDecimal("2.0"))
                                .withType(ItemEntityType.FOOD)
                                .withOptions(Sets.<AbstractOptionEntity>newLinkedHashSet(Lists.newArrayList(
                                        ValueOptionEntityBuilder.aValueOptionEntity()
                                                .withTitle("Käsesoße")
                                                .withIndex(0)
                                                .withDefaultChecked(true)
                                                .withPriceDiff(new BigDecimal("0.5"))
                                                .build()
                                )))
                                .build(),
                        ItemEntityBuilder.anItemEntity()
                                .withTitle("Mineralwasser")
                                .withPrice(new BigDecimal("1.9"))
                                .withType(ItemEntityType.DRINK_NON_ALCOHOLIC)
                                .build(),
                        ItemEntityBuilder.anItemEntity()
                                .withTitle("Augustinger")
                                .withPrice(new BigDecimal("2.2"))
                                .withType(ItemEntityType.DRINK_ALCOHOLIC)
                                .withOptions(Sets.<AbstractOptionEntity>newHashSet(
                                        createRadioOption("Größe", 0,
                                                RadioItemEntityBuilder.aRadioItemEntity()
                                                        .withTitle("groß")
                                                        .withIndex(0)
                                                        .withPriceDiff(BigDecimal.ZERO)
                                                        .build(),
                                                RadioItemEntityBuilder.aRadioItemEntity()
                                                        .withTitle("klein")
                                                        .withIndex(1)
                                                        .withPriceDiff(new BigDecimal("0.5"))
                                                        .build())
                                ))
                                .build()
                )))
                .build();
    }

    private static RadioOptionEntity createRadioOption(String title, int index, RadioItemEntity defaultSelected, RadioItemEntity... otherItems) {
        Set<RadioItemEntity> items = Sets.newHashSet(otherItems);
        items.add(defaultSelected);

        return RadioOptionEntityBuilder.aRadioOptionEntity()
                .withIndex(index)
                .withDefaultSelected(defaultSelected)
                .withTitle(title)
                .withRadioItems(items)
                .build();
    }
}
