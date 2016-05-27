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
package com.github.clboettcher.bonappetit.domain.menu;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * An option consisting of an integer value.
 * <p>
 * Example: French fries with the option for ketchup. The int value is the number of ketchup packages.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ValueOption extends Option {

    /**
     * The price difference of this option.
     * <p>
     * The total price of an order for an item can be calculated
     * using the items price and the price diff of all options.
     */
    private BigDecimal priceDiff;

    /**
     * The default value for this option.
     * <p>
     * A defaultValue of zero indicates that this option is only a yes/no option and no number is required.
     */
    private int defaultValue;

    /**
     * Constructor setting the specified properties.
     *
     * @param id           see {@link #getId()}.
     * @param title        see {@link #getTitle()}.
     * @param index        see {@link #getIndex()}.
     * @param priceDiff    see {@link #priceDiff}.
     * @param defaultValue see {@link #defaultValue}.
     */
    @Builder
    public ValueOption(long id, String title, Integer index, BigDecimal priceDiff, int defaultValue) {
        super(id, title, index);
        this.priceDiff = priceDiff;
        this.defaultValue = defaultValue;
    }
}
