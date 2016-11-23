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


import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.MenuEntity;

import java.util.List;

/**
 * Provides access to stored {@link MenuEntity}s.
 */
public interface MenuDao {

    /**
     * Returns the current {@link MenuEntity}.
     *
     * @return The current {@link MenuEntity}, may be null if it has not been configured.
     */
    MenuEntity getCurrentMenu();

    /**
     * Saves the given {@link MenuEntity} to the database.
     *
     * @param menuEntity The menu to save.
     * @return The saved instance (including non null IDs).
     */
    MenuEntity save(MenuEntity menuEntity);

    /**
     * Returns the {@link MenuEntity} with the given ID.
     *
     * @param id The ID.
     * @return The saved instance, may be null if it does not exist.
     */
    MenuEntity getMenuById(Long id);

    /**
     * Returns all menus.
     *
     * @return A list of menus, may be empty.
     */
    List<MenuEntity> getAllMenus();

    /**
     * Checks whether a menu with the given ID exists in the DB.
     *
     * @param id The id to look for.
     * @return Whether a menu with the given ID exists.
     */
    boolean exists(Long id);

    /**
     * Sets the given menu as current in the menu config.
     *
     * @param menuEntity The menu to set as current.
     */
    void setCurrent(MenuEntity menuEntity);
}
