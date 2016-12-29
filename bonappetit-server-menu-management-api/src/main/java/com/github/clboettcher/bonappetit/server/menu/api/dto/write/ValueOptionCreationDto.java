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
package com.github.clboettcher.bonappetit.server.menu.api.dto.write;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(description = "An option consisting of an integer value")
public class ValueOptionCreationDto extends OptionCreationDto {

    @ApiModelProperty(value = "The price difference of this option. The total price of an order for an item can be calculated " +
            "using the items price and the price diff of all options.", required = true, example = "2.50")
    private BigDecimal priceDiff;

    @ApiModelProperty(value = "The default value for this option. That is the initial ordered count.",
            required = true, example = "2")
    private Integer defaultValue;

    /**
     * Constructor setting the specified properties.
     *
     * @param title        see {@link #getTitle()}.
     * @param index        see {@link #index}.
     * @param priceDiff    see {@link #getPriceDiff()}.
     * @param defaultValue see {@link  #getDefaultValue()}.
     */
    @Builder
    public ValueOptionCreationDto(String title, Integer index, BigDecimal priceDiff, Integer defaultValue) {
        super(title, index);
        this.priceDiff = priceDiff;
        this.defaultValue = defaultValue;
    }
}
