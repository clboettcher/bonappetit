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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Set;

/**
 * An option that consists of multiple items of which one must be selected.
 */
@Entity
public class RadioOptionEntity extends AbstractOptionEntity {

    @OneToOne
    @JoinColumn(name = "RADIO_ITEM_ID")
    private RadioItemEntity defaultSelected;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "OPTION_ID", nullable = false)
    private Set<RadioItemEntity> radioItems;

    /**
     * Returns the default selected item.
     * <p>
     * Must be contained ind the list of items as returned by {@link #getRadioItems()}.
     *
     * @return The item that should be selected per default when this option is available.
     */
    public RadioItemEntity getDefaultSelected() {
        return defaultSelected;
    }

    public void setDefaultSelected(RadioItemEntity defaultSelected) {
        this.defaultSelected = defaultSelected;
    }

    /**
     * @return The items that this option consists of.
     */
    public Set<RadioItemEntity> getRadioItems() {
        return radioItems;
    }

    public void setRadioItems(Set<RadioItemEntity> radioItems) {
        this.radioItems = radioItems;
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
        RadioOptionEntity rhs = (RadioOptionEntity) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.defaultSelected, rhs.defaultSelected)
                .append(this.radioItems, rhs.radioItems)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(defaultSelected)
                .append(radioItems)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("defaultSelected", defaultSelected)
                .append("radioItems", radioItems)
                .toString();
    }
}
