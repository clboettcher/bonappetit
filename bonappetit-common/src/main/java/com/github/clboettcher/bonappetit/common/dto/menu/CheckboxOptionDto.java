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

import com.github.clboettcher.bonappetit.common.printing.PrintStrategy;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * An option consisting of a boolean checkbox.
 */
public class CheckboxOptionDto extends OptionDto {

    /**
     * See {@link #getPriceDiff()}.
     */
    private BigDecimal priceDiff = new BigDecimal("0.00");

    /**
     * See {@link #getPrintStrategy()}.
     */
    private PrintStrategy printStrategy = PrintStrategy.DEFAULT;

    /**
     * See {@link #getDefaultChecked()}.
     */
    Boolean defaultChecked = false;

    public CheckboxOptionDto() {
    }

    private CheckboxOptionDto(Builder builder) {
        setPriceDiff(builder.priceDiff);
        setPrintStrategy(builder.printStrategy);
        setDefaultChecked(builder.defaultChecked);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

    /**
     * @param priceDiff see {@link #getPriceDiff()}.
     */
    public void setPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
    }

    /**
     * @return The strategy that determines how this option should be printed.
     */
    public PrintStrategy getPrintStrategy() {
        return printStrategy;
    }

    /**
     * @param printStrategy see {@link #getPrintStrategy()}.
     */
    public void setPrintStrategy(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("priceDiff", priceDiff)
                .append("printStrategy", printStrategy)
                .append("defaultChecked", defaultChecked)
                .toString();
    }

    /**
     * {@code CheckboxOptionDto} builder static inner class.
     */
    public static final class Builder {
        private BigDecimal priceDiff;
        private PrintStrategy printStrategy;
        private Boolean defaultChecked;

        private Builder() {
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
         * Sets the {@code printStrategy} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code printStrategy} to set
         * @return a reference to this Builder
         */
        public Builder printStrategy(PrintStrategy val) {
            printStrategy = val;
            return this;
        }

        /**
         * Sets the {@code defaultChecked} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code defaultChecked} to set
         * @return a reference to this Builder
         */
        public Builder defaultChecked(Boolean val) {
            defaultChecked = val;
            return this;
        }

        /**
         * Returns a {@code CheckboxOptionDto} built from the parameters previously set.
         *
         * @return a {@code CheckboxOptionDto} built with parameters of this {@code CheckboxOptionDto.Builder}
         */
        public CheckboxOptionDto build() {
            return new CheckboxOptionDto(this);
        }
    }
}
