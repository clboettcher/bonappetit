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

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * An option consisting of a boolean checkbox and (optionally) a integer.
 * Example:
 * French fries with the option for ketchup for. The int value is the number of ketchup packages.
 * Example:
 * A hamburger with the option for bacon. The "defaultValue" is 0 since this is a yes-or-no option and has no int value.
 */
@Entity
public class ValueOptionEntity extends AbstractOptionEntity {

    @Column(name = "PRICE_DIFF")
    private BigDecimal priceDiff = BigDecimal.ZERO;

    @Column(name = "DEFAULT_CHECKED")
    private Boolean defaultChecked;

    /**
     * The default value for this option.
     * A defaultValue of zero indicates that this option is only a yes/no option and no number is required.
     */
    @Column(name = "DEFAULT_VALUE")
    private int defaultValue;


    /**
     * Returns the price difference of this option.
     * <p/>
     * The total price of an order for an item can be calculated
     * using the items price and the price diff of all options.
     *
     * @return The price difference.
     */
    public BigDecimal getPriceDiff() {
        return priceDiff;
    }

    public void setPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
    }

    /**
     * @return The default value to set when this option is ordered.
     */
    public Boolean getDefaultChecked() {
        return defaultChecked;
    }

    /**
     * @param defaultChecked see {@link #getDefaultChecked()}.
     */
    public void setDefaultChecked(Boolean defaultChecked) {
        this.defaultChecked = defaultChecked;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
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
        ValueOptionEntity rhs = (ValueOptionEntity) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.priceDiff, rhs.priceDiff)
                .append(this.defaultChecked, rhs.defaultChecked)
                .append(this.defaultValue, rhs.defaultValue)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(priceDiff)
                .append(defaultChecked)
                .append(defaultValue)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("priceDiff", priceDiff)
                .append("defaultChecked", defaultChecked)
                .append("defaultValue", defaultValue)
                .toString();
    }
}
