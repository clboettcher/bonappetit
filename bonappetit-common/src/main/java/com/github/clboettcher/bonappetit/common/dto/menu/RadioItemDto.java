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
package com.github.clboettcher.bonappetit.common.dto.menu;

import com.github.clboettcher.bonappetit.common.dto.AbstractEntityDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * A single item of a {@link RadioOptionDto}.
 */
public class RadioItemDto extends AbstractEntityDto {

    /**
     * See {@link #getTitle()}.
     */
    private String title;

    /**
     * See {@link #getPriceDiff()}.
     */
    private BigDecimal priceDiff;

    /**
     * No-op no-args constructor.
     */
    public RadioItemDto() {
        // No-op.
    }

    private RadioItemDto(Builder builder) {
        this.setId(builder.id);
        setTitle(builder.title);
        setPriceDiff(builder.priceDiff);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * @return The title / name of this item, e.g. "klein".
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
     * Returns the price difference of this radio item.
     * <p/>
     * The total price of an order for an item can be calculated
     * using the items price and the price diff of all options.
     *
     * @return The price difference.
     */
    public BigDecimal getPriceDiff() {
        return priceDiff;
    }

    /**
     * @param priceDiff see {@link #getPriceDiff()}.
     */
    public void setPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
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
        RadioItemDto rhs = (RadioItemDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.title, rhs.title)
                .append(this.priceDiff, rhs.priceDiff)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.title)
                .append(this.priceDiff)
                .hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("title", title)
                .append("priceDiff", priceDiff)
                .toString();
    }


    /**
     * {@code RadioItemDto} builder static inner class.
     */
    public static final class Builder {
        private Long id;
        private String title;
        private BigDecimal priceDiff;

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
         * Sets the {@code priceDiff} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code priceDiff} to set
         * @return a reference to this Builder
         */
        public Builder priceDiff(BigDecimal val) {
            priceDiff = val;
            return this;
        }

        /**
         * Returns a {@code RadioItemDto} built from the parameters previously set.
         *
         * @return a {@code RadioItemDto} built with parameters of this {@code RadioItemDto.Builder}
         */
        public RadioItemDto build() {
            return new RadioItemDto(this);
        }
    }
}
