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

import java.util.function.Predicate;

@Component
public class MenuValidator {

    /**
     * Predicate that resolves to true, if the tested {@link RadioItemEntity} has a non null ID property.
     */
    private static final Predicate<RadioItemEntity> RADIO_ITEM_HAS_ID_PREDICATE = radioItemEntity ->
            radioItemEntity.getId() != null;

    /**
     * Predicate that resolves to true, if the tested {@link AbstractOptionEntity}
     * and all referenced entities have non null ID properties.
     */
    public static final Predicate<AbstractOptionEntity> OPTION_REFS_HAVE_IDS_PREDICATE = optionEntity -> {
        // Check if all entities referenced by a radio option have ids set to non null.
        if (optionEntity instanceof RadioOptionEntity) {
            RadioOptionEntity radioOptionEntity = (RadioOptionEntity) optionEntity;
            boolean defaultSelectedHasId = RADIO_ITEM_HAS_ID_PREDICATE.test(radioOptionEntity.getDefaultSelected());
            boolean radioItemsHaveIds = radioOptionEntity.getRadioItems()
                    .stream()
                    .allMatch(RADIO_ITEM_HAS_ID_PREDICATE);
            if (!defaultSelectedHasId || !radioItemsHaveIds) {
                return false;
            }
        }

        // Now the result depends only on the option's id itself.
        return optionEntity.getId() != null;
    };

    private static final Predicate<ItemEntity> ITEM_HAS_ID_PREDICATE = itemEntity -> itemEntity.getId() != null;

    private static final Predicate<ItemEntity> ALL_OPTIONS_HAVE_ID_PREDICATES = itemEntity -> {
        if (CollectionUtils.isNotEmpty(itemEntity.getOptions())) {
            return itemEntity.getOptions().stream().allMatch(OPTION_REFS_HAVE_IDS_PREDICATE);
        }

        return true;
    };

    /**
     * Predicate that resolves to true, if the tested {@link ItemEntity}
     * and all referenced entities have non null ID properties.
     */
    private static final Predicate<ItemEntity> ITEM_REFS_HAVE_IDS_PREDICATE = itemEntity ->
            ITEM_HAS_ID_PREDICATE
                    .and(ALL_OPTIONS_HAVE_ID_PREDICATES)
                    .test(itemEntity);


    /**
     * Predicate that resolves to true, if the tested {@link MenuEntity} has
     * a non null ID property.
     */
    private static final Predicate<MenuEntity> MENU_HAS_ID_PREDICATE = menuEntity -> menuEntity.getId() != null;

    /**
     * Predicate that resolves to true, if the tested {@link MenuEntity}
     * and all referenced entities have non null ID properties.
     */
    private static final Predicate<MenuEntity> MENU_REFS_HAVE_IDS_PREDICATE = menuEntity -> {
        if (CollectionUtils.isNotEmpty(menuEntity.getItems())) {
            boolean allItemRefsHaveIds = menuEntity.getItems().stream().allMatch(ITEM_REFS_HAVE_IDS_PREDICATE);
            if (!allItemRefsHaveIds) {
                return false;
            }

        }
        return MENU_HAS_ID_PREDICATE.test(menuEntity);
    };

    void assertNewMenuValid(MenuEntity menuEntity) {
        // Make sure that we do not update existing entities by checking if the ID fields are not
        // set on all entities reachable from the given menu entity.
        if (MENU_REFS_HAVE_IDS_PREDICATE.test(menuEntity)) {
            throw new IllegalArgumentException(String.format("New entities to be saved must not contain ids. " +
                    "Found at least one offending entity referenced from menu %s", menuEntity));
        }
    }

    void assertMenuUpdateValid(MenuEntity menuEntity) {
        // Make sure that we do not create new entities by checking if the ID fields of
        // all reachable entities is set.
        if (!MENU_HAS_ID_PREDICATE.test(menuEntity)) {
            throw new IllegalArgumentException(String.format("Updated entity must contain an id. " +
                    "Offending entity:  %s", menuEntity));
        }
    }

    public void assertItemUpdateValid(ItemEntity itemEntity) {
        if (!ITEM_HAS_ID_PREDICATE.test(itemEntity)) {
            throw new IllegalArgumentException(String.format("Updated entity must contain an id. " +
                    "Offending entity:  %s", itemEntity));
        }
    }
}
