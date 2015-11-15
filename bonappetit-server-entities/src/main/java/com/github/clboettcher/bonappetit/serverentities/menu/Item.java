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

import com.github.clboettcher.bonappetit.common.entity.ItemType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Set;

/**
 * A menu item.
 */
@Entity
public class Item {

    /**
     * See {@link #getId()}.
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * See {@link #getTitle()}.
     */
    private String title;

    /**
     * See {@link #getPrice()}.
     */
    private BigDecimal price;

    /**
     * See {@link #getType()}.
     */
    private ItemType type;

    /**
     * See {@link #getOptions()}.
     */
    private Set<Option> options;

    /**
     * @return The ID of this item.
     */
    public long getId() {
        return id;
    }

    /**
     * @return The title / name of this item, e.g. "Cola".
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title see {@link #getTitle()}.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the price of this item.
     * <p/>
     * This is the 'raw' price of the item, not consisting any options which
     * might have effects on the total price.
     *
     * @return The price of this item.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price see {@link #getPrice()}.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return The type of this item.
     */
    public ItemType getType() {
        return type;
    }

    /**
     * @param type see {@link #getType()}.
     */
    public void setType(ItemType type) {
        this.type = type;
    }

    /**
     * @return The options available for this item (optional).
     */
    public Set<Option> getOptions() {
        return options;
    }

    /**
     * @param options see {@link #getOptions()}.
     */
    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("title", title)
                .append("price", price)
                .append("type", type)
                .append("options", options)
                .toString();
    }
}
