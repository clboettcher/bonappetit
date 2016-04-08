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
package com.github.clboettcher.bonappetit.common.dto.order;

import com.github.clboettcher.bonappetit.common.domain.menu.RadioItem;
import com.github.clboettcher.bonappetit.common.domain.menu.RadioOption;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * An order for a {@link RadioOption}.
 */
public class RadioOptionOrderDto extends OptionOrderDto {

    private RadioItem selectedItem;

    /**
     * @return The radio item that was selected.
     */
    public RadioItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(RadioItem selectedItem) {
        this.selectedItem = selectedItem;
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
        RadioOptionOrderDto rhs = (RadioOptionOrderDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.selectedItem, rhs.selectedItem)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(selectedItem)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("selectedItem", selectedItem)
                .toString();
    }

}
