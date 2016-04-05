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
package com.github.clboettcher.bonappetit.server.entity.order;

import com.github.clboettcher.bonappetit.server.entity.menu.IntegerOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * An order for a {@link IntegerOption}.
 */
@Entity
public class IntegerOptionOrder extends OptionOrder {

    @OneToOne(optional = false)
    @JoinColumn(name = "INTEGER_OPTION_ID", referencedColumnName = "OPTION_ID")
    private IntegerOption option;

    @Column(name = "VALUE", nullable = false)
    private Integer value;

    /**
     * @return The ordered option.
     */
    public IntegerOption getOption() {
        return option;
    }

    public void setOption(IntegerOption option) {
        this.option = option;
    }

    /**
     * Returns how often the refferenced option was ordered.
     *
     * @return The value.
     */
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
