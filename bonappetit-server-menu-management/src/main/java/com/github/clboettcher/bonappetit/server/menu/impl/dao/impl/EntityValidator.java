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

import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class EntityValidator {

    private static final Predicate<RadioItemEntity> RADIO_ITEM_HAS_ID_PREDICATE = radioItemEntity ->
            radioItemEntity.getId() != null;

    public static final Predicate<AbstractOptionEntity> ANY_OPTION_REF_HAS_ID_PREDICATE = optionEntity -> {
        if (optionEntity instanceof RadioOptionEntity) {
            RadioOptionEntity radioOptionEntity = (RadioOptionEntity) optionEntity;
            boolean defaultSelectedHasId = RADIO_ITEM_HAS_ID_PREDICATE.test(radioOptionEntity.getDefaultSelected());
            boolean anyRadioItemsHasId = radioOptionEntity.getRadioItems()
                    .stream()
                    .anyMatch(RADIO_ITEM_HAS_ID_PREDICATE);
            if (defaultSelectedHasId || anyRadioItemsHasId) {
                return true;
            }
        }

        // Now the result depends only on the option's id itself.
        return optionEntity.getId() != null;
    };

    private static final Predicate<ItemEntity> ITEM_HAS_ID_PREDICATE = itemEntity -> itemEntity.getId() != null;

    private static final Predicate<ItemEntity> ANY_OPTIONS_HAVE_ID_PREDICATES = itemEntity ->
            CollectionUtils.isNotEmpty(itemEntity.getOptions())
                    && itemEntity.getOptions().stream().anyMatch(ANY_OPTION_REF_HAS_ID_PREDICATE);

    private static final Predicate<ItemEntity> ANY_ITEM_REF_HAS_ID_PREDICATE = itemEntity ->
            ITEM_HAS_ID_PREDICATE
                    .or(ANY_OPTIONS_HAVE_ID_PREDICATES)
                    .test(itemEntity);

    private static final Predicate<List<ItemEntity>> ANY_ITEMS_REF_HAS_ID_PREDICATE = itemEntities ->
            CollectionUtils.isNotEmpty(itemEntities)
                    && itemEntities.stream().anyMatch(ANY_ITEM_REF_HAS_ID_PREDICATE);

    private static final Predicate<MenuEntity> MENU_HAS_ID_PREDICATE = menuEntity -> menuEntity.getId() != null;

    void assertNewMenuValid(MenuEntity menuEntity) {
        // Make sure that we do not update existing entities by checking if the ID fields are not
        // set on all entities reachable from the given menu entity.
        if (MENU_HAS_ID_PREDICATE.test(menuEntity)) {
            throw new IllegalArgumentException(String.format("New entities to be saved must not contain ids. " +
                    "Offending entity: %s", menuEntity));
        }
    }

    public void assertNewItemsValid(List<ItemEntity> items) {
        if (ANY_ITEMS_REF_HAS_ID_PREDICATE.test(items)) {
            throw new IllegalArgumentException(String.format("New entities to be saved must not contain ids. " +
                    "Found at least one offending entity referenced from items %s", items));
        }
    }
}
