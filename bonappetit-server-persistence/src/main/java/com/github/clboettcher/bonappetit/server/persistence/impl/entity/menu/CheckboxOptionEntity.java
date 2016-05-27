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
package com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * An option which can be selected or not selected.
 * <p>
 * Example: A hamburger with the option for bacon.
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CheckboxOptionEntity extends AbstractOptionEntity {

    /**
     * The price difference of this option.
     * <p>
     * The total price of an order for an item can be calculated
     * using the items price and the price diff of all options.
     */
    @Column(name = "PRICE_DIFF")
    private BigDecimal priceDiff;

    /**
     * Whether this option should be checked (marked for order) per default when
     * an order for the corresponding item is taken.
     */
    @Column(name = "DEFAULT_CHECKED")
    private Boolean defaultChecked;

    /**
     * Constructor setting the specified properties.
     *
     * @param id             see {@link #id}.
     * @param title          see {@link #title}.
     * @param index          see {@link #index}.
     * @param priceDiff      see {@link #priceDiff}.
     * @param defaultChecked see {@link #defaultChecked}.
     */
    @Builder
    public CheckboxOptionEntity(long id, String title, Integer index, BigDecimal priceDiff, Boolean defaultChecked) {
        super(id, title, index);
        this.priceDiff = priceDiff;
        this.defaultChecked = defaultChecked;
    }
}
