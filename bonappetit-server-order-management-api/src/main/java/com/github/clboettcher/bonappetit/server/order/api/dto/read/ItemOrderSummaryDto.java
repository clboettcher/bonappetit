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
package com.github.clboettcher.bonappetit.server.order.api.dto.read;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class ItemOrderSummaryDto {

    @ApiModelProperty(value = "The title of the ordered item", required = true)
    private String itemTitle;

    @ApiModelProperty(value = "The total price of the order already considering all ordered options", required = true)
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "The ordered options of the ordered item")
    private List<OptionOrderSummaryDto> optionOrders;

    @Builder
    public ItemOrderSummaryDto(String itemTitle, BigDecimal totalPrice, List<OptionOrderSummaryDto> optionOrders) {
        this.itemTitle = itemTitle;
        this.totalPrice = totalPrice;
        this.optionOrders = optionOrders;
    }
}
