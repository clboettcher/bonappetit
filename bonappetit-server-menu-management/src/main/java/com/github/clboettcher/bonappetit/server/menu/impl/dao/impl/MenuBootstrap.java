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
package com.github.clboettcher.bonappetit.server.menu.impl.dao.impl;

import com.github.clboettcher.bonappetit.server.menu.impl.entity.config.MenuConfig;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * This class bootstraps some testdata into the db and will be deleted at some point.
 */
// Enable to save test menu in the db
@Component
@Profile("INMEM")
public class MenuBootstrap {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuBootstrap.class);

    /**
     * Constructor that saves some testdata in the db.
     *
     * @param menuConfigRepository The bean used to access the stored menu config.
     */
    @Autowired
    public MenuBootstrap(MenuConfigRepository menuConfigRepository,
                         MenuRepository menuRepository,
                         ItemRepository itemRepository
    ) {
        LOGGER.info("Saving test data in the DB.");

        MenuEntity menuEntity = createMenu();
        itemRepository.save(menuEntity.getItems());
        MenuConfig menuConfig = new MenuConfig();
        menuConfig.setCurrent(menuEntity);
        menuRepository.save(menuEntity);
        menuConfigRepository.save(menuConfig);
    }

    private MenuEntity createMenu() {
        return MenuEntity.builder()
                .title("My awesome menu")
                .creationTimestamp(new Date())
                .lastUpdateTimestamp(new Date())
                .items(Lists.newArrayList(
                        ItemEntity.builder()
                                .title("Pommes")
                                .price(new BigDecimal("2.5"))
                                .type(ItemEntityType.FOOD)
                                .creationTimestamp(new Date())
                                .options(Lists.newArrayList(
                                        CheckboxOptionEntity.builder()
                                                .title("Extra Salz")
                                                .index(0)
                                                .defaultChecked(false)
                                                .priceDiff(BigDecimal.ZERO)
                                                .build(),
                                        ValueOptionEntity.builder()
                                                .title("Ketchup")
                                                .index(1)
                                                .defaultValue(2)
                                                .priceDiff(BigDecimal.ZERO)
                                                .build()
                                ))
                                .build(),
                        ItemEntity.builder()
                                .title("Nachos")
                                .price(new BigDecimal("2.0"))
                                .type(ItemEntityType.FOOD)
                                .creationTimestamp(new Date())
                                .options(Lists.newArrayList(
                                        CheckboxOptionEntity.builder()
                                                .title("Käsesoße")
                                                .index(0)
                                                .defaultChecked(true)
                                                .priceDiff(new BigDecimal("0.5"))
                                                .build()
                                ))
                                .build(),
                        ItemEntity.builder()
                                .title("Mineralwasser")
                                .price(new BigDecimal("1.9"))
                                .type(ItemEntityType.DRINK_NON_ALCOHOLIC)
                                .creationTimestamp(new Date())
                                .build(),
                        ItemEntity.builder()
                                .title("Augustinger")
                                .price(new BigDecimal("2.2"))
                                .type(ItemEntityType.DRINK_ALCOHOLIC)
                                .creationTimestamp(new Date())
                                .options(Lists.newArrayList(
                                        createRadioOption("Größe", 0,
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
                ))
                .build();
    }

    private static RadioOptionEntity createRadioOption(String title, int index, RadioItemEntity defaultSelected, RadioItemEntity... otherItems) {
        List<RadioItemEntity> items = Lists.newArrayList(otherItems);
        items.add(defaultSelected);

        return RadioOptionEntity.builder()
                .index(index)
                .defaultSelected(defaultSelected)
                .title(title)
                .radioItems(items)
                .build();
    }
}
