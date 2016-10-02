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
package com.github.clboettcher.bonappetit.server.staff.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * A single item of a {@link RadioOptionDto}.
 */
@Data
@NoArgsConstructor
@ApiModel(description = "A single item of a radio option")
public class RadioItemDto {

    @ApiModelProperty(value = "The technical ID", required = true, example = "1337")
    private Long id;

    @ApiModelProperty(value = "The title / name of this item", required = true, example = "small")
    private String title;

    @ApiModelProperty(value = "The price difference of this radio item. The total price of an order for an item " +
            "can be calculated using the items price and the price diff of all options.", required = true, example = "2.50")
    private BigDecimal priceDiff;

    @ApiModelProperty(value = "The index this item should be displayed at", required = true, example = "0")
    private Integer index;

    @Builder
    public RadioItemDto(Long id, String title, BigDecimal priceDiff, Integer index) {
        this.id = id;
        this.title = title;
        this.priceDiff = priceDiff;
        this.index = index;
    }
}
