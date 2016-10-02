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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * An option consisting of an integer value.
 * <p>
 * Example: French fries with the option for ketchup. The int value is the number of ketchup packages.
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ValueOptionEntity extends AbstractOptionEntity {

    /**
     * The price difference of this option.
     * <p>
     * The total price of an order for an item can be calculated
     * using the items price and the price diff of all options.
     */
    @Column(name = "PRICE_DIFF")
    private BigDecimal priceDiff = BigDecimal.ZERO;

    /**
     * The default value for this option.
     * A defaultValue of zero indicates that this option is only a yes/no option and no number is required.
     */
    @Column(name = "DEFAULT_VALUE")
    private int defaultValue;

    /**
     * Constructor setting the specified properties.
     *
     * @param id           see {@link #getId()}.
     * @param title        see {@link #getTitle()}.
     * @param index        see {@link #getIndex()}.
     * @param priceDiff    see {@link #getPriceDiff()}.
     * @param defaultValue see {@link #getDefaultValue()}.
     */
    @Builder
    public ValueOptionEntity(long id, String title, Integer index, BigDecimal priceDiff, int defaultValue) {
        super(id, title, index);
        this.priceDiff = priceDiff;
        this.defaultValue = defaultValue;
    }

    public BigDecimal getPriceDiff() {
        return priceDiff;
    }

    public void setPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
}
