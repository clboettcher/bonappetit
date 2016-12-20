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
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<Long, MenuEntity> menus = new HashMap<>();

    private Long currentMenuId = 1L;

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
    public MenuEntity create(MenuEntity menuEntity) {
        Long id = getNewMenuId();
        menuEntity.setId(id);
        this.menus.put(id, menuEntity);
        return menuEntity;
    }

    @Override
    public MenuEntity getMenuById(Long id) {
        return menus.get(id);
    }

    private MenuEntity createMenu() {
        return MenuEntity.builder()
                .id(this.getNewMenuId())
                .items(Lists.newArrayList(
                        ItemEntity.builder()
                                .id(1L)
                                .title("Pommes")
                                .price(new BigDecimal("2.5"))
                                .type(ItemEntityType.FOOD)
                                .options(Lists.newArrayList(
                                        CheckboxOptionEntity.builder()
                                                .id(1L)
                                                .title("Extra Salz")
                                                .index(0)
                                                .defaultChecked(false)
                                                .priceDiff(BigDecimal.ZERO)
                                                .build(),
                                        ValueOptionEntity.builder()
                                                .id(2L)
                                                .title("Ketchup")
                                                .defaultValue(2)
                                                .priceDiff(BigDecimal.ZERO)
                                                .index(1)
                                                .build()
                                ))
                                .build(),
                        ItemEntity.builder()
                                .id(2L)
                                .title("Nachos")
                                .price(new BigDecimal("2.0"))
                                .type(ItemEntityType.FOOD)
                                .options(Lists.newArrayList(
                                        CheckboxOptionEntity.builder()
                                                .id(3L)
                                                .title("Käsesoße")
                                                .index(0)
                                                .defaultChecked(true)
                                                .priceDiff(new BigDecimal("0.5"))
                                                .build()
                                ))
                                .build(),
                        ItemEntity.builder()
                                .id(3L)
                                .title("Mineralwasser")
                                .price(new BigDecimal("1.9"))
                                .type(ItemEntityType.DRINK_NON_ALCOHOLIC)
                                .build(),
                        ItemEntity.builder()
                                .id(4L)
                                .title("Augustinger")
                                .price(new BigDecimal("2.2"))
                                .type(ItemEntityType.DRINK_ALCOHOLIC)
                                .options(Lists.newArrayList(
                                        createRadioOptionEntity(1L, 0, "Größe",
                                                RadioItemEntity.builder()
                                                        .id(1L)
                                                        .index(0)
                                                        .title("groß")
                                                        .priceDiff(BigDecimal.ZERO)
                                                        .build(),
                                                RadioItemEntity.builder()
                                                        .id(2L)
                                                        .index(1)
                                                        .title("klein")
                                                        .priceDiff(new BigDecimal("0.5"))
                                                        .build())
                                ))
                                .build()
                ))
                .build();
    }

    private static RadioOptionEntity createRadioOptionEntity(Long id,
                                                             int index,
                                                             String title,
                                                             RadioItemEntity defaultSelected,
                                                             RadioItemEntity... otherItemEntities) {
        List<RadioItemEntity> items = Lists.newArrayList(otherItemEntities);
        items.add(defaultSelected);

        return RadioOptionEntity.builder()
                .id(id)
                .index(index)
                .defaultSelected(defaultSelected)
                .title(title)
                .radioItems(items)
                .build();
    }

    private Long getNewMenuId() {
        return this.currentMenuId++;
    }

    @Override
    public List<MenuEntity> getAllMenus() {
        return Lists.newArrayList(this.menus.values());
    }

    @Override
    public boolean exists(Long id) {
        return this.menus.containsKey(id);
    }

    @Override
    public void setCurrent(MenuEntity menuEntity) {
        throw new UnsupportedOperationException();
    }
}
