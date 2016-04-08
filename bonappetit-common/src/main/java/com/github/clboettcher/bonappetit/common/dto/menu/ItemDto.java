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
package com.github.clboettcher.bonappetit.common.dto.menu;

import com.github.clboettcher.bonappetit.common.domain.menu.Item;
import com.github.clboettcher.bonappetit.common.dto.AbstractEntityDto;
import com.github.clboettcher.bonappetit.common.entity.ItemType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Set;

/**
 * A menu item.
 */
public class ItemDto extends AbstractEntityDto {

    private String title;

    private BigDecimal price;

    private ItemType type;

    private Set<OptionDto> optionDtos;

    private Set<Item> sideDishes;

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
    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    /**
     * @return The options available for this item (optional).
     */
    public Set<OptionDto> getOptionDtos() {
        return optionDtos;
    }

    public void setOptionDtos(Set<OptionDto> optionDtos) {
        this.optionDtos = optionDtos;
    }

    public Set<Item> getSideDishes() {
        return sideDishes;
    }

    public void setSideDishes(Set<Item> sideDishes) {
        this.sideDishes = sideDishes;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("title", title)
                .append("price", price)
                .append("type", type)
                .append("optionDtos", optionDtos)
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
        ItemDto rhs = (ItemDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.title, rhs.title)
                .append(this.price, rhs.price)
                .append(this.type, rhs.type)
                .append(this.optionDtos, rhs.optionDtos)
                .append(this.sideDishes, rhs.sideDishes)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(title)
                .append(price)
                .append(type)
                .append(optionDtos)
                .append(sideDishes)
                .toHashCode();
    }
}
