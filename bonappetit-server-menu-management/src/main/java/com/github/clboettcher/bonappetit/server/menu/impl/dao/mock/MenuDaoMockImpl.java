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
package com.github.clboettcher.bonappetit.server.menu.impl.dao.mock;

import com.github.clboettcher.bonappetit.server.menu.impl.dao.MenuDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Impl of {@link MenuDao} that returns static testdata.
 */
@Component
@Profile("mock")
public class MenuDaoMockImpl implements MenuDao {

    /**
     * The test menu that is returned.
     */
    private MenuEntity menu;

    /**
     * Constructor initializing the bean with test data.
     */
    public MenuDaoMockImpl() {
        this.menu = createMenu();
    }

    @Override
    public MenuEntity getCurrentMenu() {
        return menu;
    }

    private MenuEntity createMenu() {
        return MenuEntity.builder()
                .items(Sets.newLinkedHashSet(Lists.newArrayList(
                        ItemEntity.builder()
                                .id(1)
                                .title("Pommes")
                                .price(new BigDecimal("2.5"))
                                .type(ItemEntityType.FOOD)
                                .options(Sets.newLinkedHashSet(Lists.newArrayList(
                                        CheckboxOptionEntity.builder()
                                                .title("Extra Salz")
                                                .index(0)
                                                .defaultChecked(false)
                                                .priceDiff(BigDecimal.ZERO)
                                                .build(),
                                        ValueOptionEntity.builder()
                                                .title("Ketchup")
                                                .defaultValue(2)
                                                .priceDiff(BigDecimal.ZERO)
                                                .index(1)
                                                .build()
                                )))
                                .build(),
                        ItemEntity.builder()
                                .id(2)
                                .title("Nachos")
                                .price(new BigDecimal("2.0"))
                                .type(ItemEntityType.FOOD)
                                .options(Sets.<AbstractOptionEntity>newLinkedHashSet(Lists.newArrayList(
                                        CheckboxOptionEntity.builder()
                                                .title("Käsesoße")
                                                .index(0)
                                                .defaultChecked(true)
                                                .priceDiff(new BigDecimal("0.5"))
                                                .build()
                                )))
                                .build(),
                        ItemEntity.builder()
                                .id(3)
                                .title("Mineralwasser")
                                .price(new BigDecimal("1.9"))
                                .type(ItemEntityType.DRINK_NON_ALCOHOLIC)
                                .build(),
                        ItemEntity.builder()
                                .id(4)
                                .title("Augustinger")
                                .price(new BigDecimal("2.2"))
                                .type(ItemEntityType.DRINK_ALCOHOLIC)
                                .options(Sets.<AbstractOptionEntity>newHashSet(
                                        createRadioOptionEntity("Größe", 0,
                                                RadioItemEntity.builder()
                                                        .title("groß")
                                                        .index(0)
                                                        .priceDiff(BigDecimal.ZERO)
                                                        .build(),
                                                RadioItemEntity.builder()
                                                        .title("klein")
                                                        .index(1)
                                                        .priceDiff(new BigDecimal("0.5"))
                                                        .build())
                                ))
                                .build()
                )))
                .build();
    }

    private static RadioOptionEntity createRadioOptionEntity(String title, int index, RadioItemEntity defaultSelected, RadioItemEntity... otherItemEntitys) {
        Set<RadioItemEntity> items = Sets.newHashSet(otherItemEntitys);
        items.add(defaultSelected);

        return RadioOptionEntity.builder()
                .index(index)
                .defaultSelected(defaultSelected)
                .title(title)
                .radioItems(items)
                .build();
    }
}
