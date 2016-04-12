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
package com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * A menu item.
 */
@Entity
@Table(name = "ITEM")
public class ItemEntity {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "TYPE", nullable = false)
    private ItemEntityType type;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ID")
    private Set<AbstractOptionEntity> options;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ITEM_SIDE_DISH",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "SIDE_DISH_ID", referencedColumnName = "ITEM_ID"))
    private Set<ItemEntity> sideDishes;

    /**
     * @return The title / name of this item, e.g. "Cola".
     */
    public String getTitle() {
        return title;
    }

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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return The type of this item.
     */
    public ItemEntityType getType() {
        return type;
    }

    public void setType(ItemEntityType type) {
        this.type = type;
    }

    /**
     * @return The options available for this item (optional).
     */
    public Set<AbstractOptionEntity> getOptions() {
        return options;
    }

    public void setOptions(Set<AbstractOptionEntity> options) {
        this.options = options;
    }

    /**
     * @return The ID of this event.
     */
    public long getId() {
        return id;
    }

    /**
     * @param id see {@link #getId()}.
     */
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("options", options)
                .append("price", price)
                .append("title", title)
                .append("type", type)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        ItemEntity rhs = (ItemEntity) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.title, rhs.title)
                .append(this.price, rhs.price)
                .append(this.type, rhs.type)
                .append(this.options, rhs.options)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(title)
                .append(price)
                .append(type)
                .append(options)
                .toHashCode();
    }
}
