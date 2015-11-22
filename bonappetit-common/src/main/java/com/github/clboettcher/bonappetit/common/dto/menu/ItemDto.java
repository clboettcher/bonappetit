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
public class ItemDto {

    /**
     * See {@link #getId()}.
     */
    private Long id;

    /**
     * See {@link #getTitle()}.
     */
    private String title;

    /**
     * See {@link #getPrice()}.
     */
    private BigDecimal price;

    /**
     * See {@link #getType()}.
     */
    private ItemType type;

    /**
     * See {@link #getOptionDtos()}.
     */
    private Set<OptionDto> optionDtos;

    /**
     * No-op no-args constructor.
     */
    public ItemDto() {
        // No-op.
    }

    private ItemDto(Builder builder) {
        id = builder.id;
        setTitle(builder.title);
        setPrice(builder.price);
        setType(builder.type);
        optionDtos = builder.optionDtos;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * @param id see {@link #getId()}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param optionDtos see {@link #getOptionDtos()}.
     */
    public void setOptionDtos(Set<OptionDto> optionDtos) {
        this.optionDtos = optionDtos;
    }

    /**
     * @return The ID of this item.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return The title / name of this item, e.g. "Cola".
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title see {@link #getTitle()}.
     */
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

    /**
     * @param price see {@link #getPrice()}.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return The type of this item.
     */
    public ItemType getType() {
        return type;
    }

    /**
     * @param type see {@link #getType()}.
     */
    public void setType(ItemType type) {
        this.type = type;
    }

    /**
     * @return The options available for this item (optional).
     */
    public Set<OptionDto> getOptionDtos() {
        return optionDtos;
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
                .append(this.id, rhs.id)
                .append(this.title, rhs.title)
                .append(this.optionDtos, rhs.optionDtos)
                .append(this.price, rhs.price)
                .append(this.type, rhs.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.title)
                .append(this.optionDtos)
                .append(this.price)
                .append(this.type)
                .hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("title", title)
                .append("price", price)
                .append("type", type)
                .append("optionDtos", optionDtos)
                .toString();
    }

    /**
     * {@code ItemDto} builder static inner class.
     */
    public static final class Builder {
        private Long id;
        private String title;
        private BigDecimal price;
        private ItemType type;
        private Set<OptionDto> optionDtos;

        private Builder() {
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(Long val) {
            id = val;
            return this;
        }

        /**
         * Sets the {@code title} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code title} to set
         * @return a reference to this Builder
         */
        public Builder title(String val) {
            title = val;
            return this;
        }

        /**
         * Sets the {@code price} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code price} to set
         * @return a reference to this Builder
         */
        public Builder price(BigDecimal val) {
            price = val;
            return this;
        }

        /**
         * Sets the {@code type} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code type} to set
         * @return a reference to this Builder
         */
        public Builder type(ItemType val) {
            type = val;
            return this;
        }

        /**
         * Sets the {@code optionDtos} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code optionDtos} to set
         * @return a reference to this Builder
         */
        public Builder optionDtos(Set<OptionDto> val) {
            optionDtos = val;
            return this;
        }

        /**
         * Returns a {@code ItemDto} built from the parameters previously set.
         *
         * @return a {@code ItemDto} built with parameters of this {@code ItemDto.Builder}
         */
        public ItemDto build() {
            return new ItemDto(this);
        }
    }
}
