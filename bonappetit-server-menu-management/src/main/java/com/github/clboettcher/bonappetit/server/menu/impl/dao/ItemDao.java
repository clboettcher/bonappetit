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
package com.github.clboettcher.bonappetit.server.menu.impl.dao;


import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntity;

import java.util.List;

/**
 * Provides access to stored {@link ItemEntity}s.
 */
public interface ItemDao {

    /**
     * Returns the item with the given id.
     *
     * @param id The ID.
     * @return The item, or null if it does not exist.
     */
    ItemEntity getItem(Long id);

    /**
     * Returns all stored items.
     *
     * @return An item list, may be empty.
     */
    List<ItemEntity> getAll();

    /**
     * Returns the items with the given ids.
     *
     * @param itemIds The ids.
     * @return The items, may contain null elements.
     */
    List<ItemEntity> getItemsByIdList(List<Long> itemIds);

    /**
     * Returns whether an item exists in the db with the given id.
     *
     * @param id The id.
     * @return true, if an item exists with the given id.
     */
    boolean exists(Long id);

    /**
     * Creates the given entities in the db.
     *
     * @param itemEntities The items to create, may not contain null values or entities with the id property set.
     */
    List<ItemEntity> create(List<ItemEntity> itemEntities);
}
