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
import java.util.HashMap;
import java.util.Map;
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

    private Map<Integer, MenuEntity> menus = new HashMap<>();

    private int currentMenuId = 1;

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

    @Override
    public MenuEntity save(MenuEntity menuEntity) {
        int id = getNewMenuId();
        menuEntity.setId(id);
        this.menus.put(id, menuEntity);
        return menuEntity;
    }

    private MenuEntity createMenu() {
        return MenuEntity.builder()
                .id(this.getNewMenuId())
                .items(Sets.newLinkedHashSet(Lists.newArrayList(
                        ItemEntity.builder()
                                .id(1)
                                .title("Pommes")
                                .price(new BigDecimal("2.5"))
                                .type(ItemEntityType.FOOD)
                                .options(Sets.newLinkedHashSet(Lists.newArrayList(
                                        CheckboxOptionEntity.builder()
                                                .id(1)
                                                .title("Extra Salz")
                                                .index(0)
                                                .defaultChecked(false)
                                                .priceDiff(BigDecimal.ZERO)
                                                .build(),
                                        ValueOptionEntity.builder()
                                                .id(2)
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
                                                .id(3)
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
                                        createRadioOptionEntity(1, 0, "Größe",
                                                RadioItemEntity.builder()
                                                        .id(1)
                                                        .index(0)
                                                        .title("groß")
                                                        .priceDiff(BigDecimal.ZERO)
                                                        .build(),
                                                RadioItemEntity.builder()
                                                        .id(2)
                                                        .index(1)
                                                        .title("klein")
                                                        .priceDiff(new BigDecimal("0.5"))
                                                        .build())
                                ))
                                .build()
                )))
                .build();
    }

    private static RadioOptionEntity createRadioOptionEntity(long id, int index, String title, RadioItemEntity defaultSelected, RadioItemEntity... otherItemEntities) {
        Set<RadioItemEntity> items = Sets.newHashSet(otherItemEntities);
        items.add(defaultSelected);

        return RadioOptionEntity.builder()
                .id(id)
                .index(index)
                .defaultSelected(defaultSelected)
                .title(title)
                .radioItems(items)
                .build();
    }

    private int getNewMenuId() {
        return this.currentMenuId++;
    }
}
