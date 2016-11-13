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
package com.github.clboettcher.bonappetit.server.menu.impl.entity.menu;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * A menu item.
 */
@Entity
@Table(name = "ITEM")
@NoArgsConstructor
@ToString
public class ItemEntity {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private long id;

    /**
     * The title / name of this item, e.g. "Cola".
     */
    @Column(name = "TITLE", nullable = false)
    private String title;

    /**
     * The price of this item.
     * <p>
     * This is the 'raw' price of the item, not consisting any options which
     * might have effects on the total price.
     */
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    /**
     * The type of this item.
     */
    @Column(name = "TYPE", nullable = false)
    private ItemEntityType type;

    /**
     * The options available for this item (optional).
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ID")
    private Set<AbstractOptionEntity> options;

    /**
     * The side dishes available for this item (optional).
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ITEM_SIDE_DISH",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "SIDE_DISH_ID", referencedColumnName = "ITEM_ID"))
    private Set<ItemEntity> sideDishes;

    /**
     * Constructor setting the specified properties.
     *
     * @param id         see {@link #id}.
     * @param title      see {@link #title}.
     * @param price      see {@link #price}.
     * @param type       see {@link #type}.
     * @param options    see {@link #options}.
     * @param sideDishes see {@link #sideDishes}.
     */
    @Builder
    public ItemEntity(long id, String title, BigDecimal price, ItemEntityType type, Set<AbstractOptionEntity> options, Set<ItemEntity> sideDishes) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.type = type;
        this.options = options;
        this.sideDishes = sideDishes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ItemEntityType getType() {
        return type;
    }

    public void setType(ItemEntityType type) {
        this.type = type;
    }

    public Set<AbstractOptionEntity> getOptions() {
        return options;
    }

    public void setOptions(Set<AbstractOptionEntity> options) {
        this.options = options;
    }

    public Set<ItemEntity> getSideDishes() {
        return sideDishes;
    }

    public void setSideDishes(Set<ItemEntity> sideDishes) {
        this.sideDishes = sideDishes;
    }

    public boolean hasOptions() {
        return CollectionUtils.isNotEmpty(this.options);
    }
}
