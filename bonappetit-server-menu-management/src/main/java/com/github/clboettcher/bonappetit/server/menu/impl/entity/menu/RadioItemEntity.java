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
package com.github.clboettcher.bonappetit.server.menu.impl.entity.menu;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * A single item of a {@link RadioOptionEntity}.
 */
@Entity
@Table(name = "RADIO_ITEM")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RadioItemEntity {

    /**
     * The ID.
     */
    @Id
    @GeneratedValue
    @Column(name = "RADIO_ITEM_ID")
    private Long id;

    /**
     * The title / name of this item, e.g. "small".
     */
    @Column(name = "TITLE", nullable = false)
    private String title;

    /**
     * The zero based index to display this item at.
     */
    @Column(name = "RADIO_ITEM_INDEX", nullable = false) // 'index' is reserved in mysql
    private Integer index;

    /**
     * The price difference of this radio item.
     * <p>
     * The total price of an order for an item can be calculated
     * using the items price and the price diff of all options.
     */
    @Column(name = "PRICE_DIFF", nullable = false)
    private BigDecimal priceDiff = BigDecimal.ZERO;

    /**
     * Constructor setting the specified properties.
     *
     * @param id        see {@link #id}.
     * @param title     see {@link #title}.
     * @param index     see {@link #index}.
     * @param priceDiff see {@link #priceDiff}.
     */
    @Builder
    public RadioItemEntity(Long id, String title, Integer index, BigDecimal priceDiff) {
        this.id = id;
        this.title = title;
        this.index = index;
        this.priceDiff = priceDiff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public BigDecimal getPriceDiff() {
        return priceDiff;
    }

    public void setPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
    }
}
