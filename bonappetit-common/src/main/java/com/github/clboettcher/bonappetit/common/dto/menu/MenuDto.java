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
package com.github.clboettcher.bonappetit.common.dto.menu;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

/**
 * The menu represents the items that can be ordered.
 */
public class MenuDto {
    /**
     * See {@link #getItemDtos()}.
     */
    private Set<ItemDto> itemDtos;

    public MenuDto() {
    }

    private MenuDto(Builder builder) {
        setItemDtos(builder.itemDtos);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * @return The menu items that this menu consists of.
     */
    public Set<ItemDto> getItemDtos() {
        return itemDtos;
    }

    /**
     * @param itemDtos see {@link #getItemDtos()}.
     */
    public void setItemDtos(Set<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("items", itemDtos)
                .toString();
    }

    /**
     * {@code MenuDto} builder static inner class.
     */
    public static final class Builder {
        private Set<ItemDto> itemDtos;

        private Builder() {
        }

        /**
         * Sets the {@code items} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code items} to set
         * @return a reference to this Builder
         */
        public Builder items(Set<ItemDto> val) {
            itemDtos = val;
            return this;
        }

        /**
         * Returns a {@code MenuDto} built from the parameters previously set.
         *
         * @return a {@code MenuDto} built with parameters of this {@code MenuDto.Builder}
         */
        public MenuDto build() {
            return new MenuDto(this);
        }
    }
}
