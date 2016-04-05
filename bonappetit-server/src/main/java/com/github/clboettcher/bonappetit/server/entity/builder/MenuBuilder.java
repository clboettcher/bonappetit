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
package com.github.clboettcher.bonappetit.server.entity.builder;

import com.github.clboettcher.bonappetit.server.entity.menu.Item;
import com.github.clboettcher.bonappetit.server.entity.menu.Menu;

import java.util.Set;

public class MenuBuilder {
    private long id;
    private Set<Item> items;

    private MenuBuilder() {
    }

    public static MenuBuilder aMenu() {
        return new MenuBuilder();
    }

    public MenuBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public MenuBuilder withItems(Set<Item> items) {
        this.items = items;
        return this;
    }

    public MenuBuilder but() {
        return aMenu().withId(id).withItems(items);
    }

    public Menu build() {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setItems(items);
        return menu;
    }
}
