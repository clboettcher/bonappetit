/*
* Copyright (c) 2015 Claudius Boettcher (pos.bonappetit@gmail.com)
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
package com.github.clboettcher.bonappetit.serverentities.menu;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

/**
 * The menu represents the items that can be ordered.
 */
public class Menu {

    /**
     * See {@link #getId()}.
     */
    private long id;

    /**
     * See {@link #getItems()}.
     */
    private Set<Item> items;

    /**
     * No-op no-args constructor.
     */
    public Menu() {
    }

    private Menu(Builder builder) {
        id = builder.id;
        setItems(builder.items);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * @return The menu items that this menu consists of.
     */
    public Set<Item> getItems() {
        return items;
    }

    /**
     * @param items see {@link #getItems()}.
     */
    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("items", items)
                .toString();
    }

    /**
     * @return The ID of this entity.
     */
    public long getId() {
        return id;
    }

    /**
     * {@code Menu} builder static inner class.
     */
    public static final class Builder {
        private Set<Item> items;
        private long id;

        private Builder() {
        }

        /**
         * Sets the {@code items} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code items} to set
         * @return a reference to this Builder
         */
        public Builder items(Set<Item> val) {
            items = val;
            return this;
        }

        /**
         * Returns a {@code Menu} built from the parameters previously set.
         *
         * @return a {@code Menu} built with parameters of this {@code Menu.Builder}
         */
        public Menu build() {
            return new Menu(this);
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(long val) {
            id = val;
            return this;
        }
    }
}
