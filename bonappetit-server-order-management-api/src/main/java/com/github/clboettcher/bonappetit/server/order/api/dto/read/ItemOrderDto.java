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

import com.github.clboettcher.bonappetit.server.menu.api.dto.common.ItemDtoType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(description = "An order for an item")
public class ItemOrderDto {

    @ApiModelProperty(value = "The ID of the order", required = true)
    private Long id;

    @ApiModelProperty(value = "The ID of the ordered item", required = true)
    private Long itemId;

    @ApiModelProperty(value = "The title of the ordered item", required = true)
    private String itemTitle;

    @ApiModelProperty(value = "The price of the ordered item", required = true)
    private BigDecimal itemPrice;

    @ApiModelProperty(value = "The type of the ordered item", required = true)
    private ItemDtoType itemType;

    @ApiModelProperty(value = "The ordered options of the ordered item")
    private List<OptionOrderDto> optionOrders;

    @ApiModelProperty(value = "The person or location that this order should be delivered to", required = true,
            example = "Table 4")
    private String deliverTo;

    @ApiModelProperty(value = "The ID of the staff member who took this order", required = true)
    private Long staffMemberId;

    @ApiModelProperty(value = "The time this order was taken", required = true)
    private DateTime orderTime;

    @ApiModelProperty(value = "A note further describing this order", example = "Without onions")
    private String note;

    @ApiModelProperty(value = "The status the order is in.", required = true)
    private OrderStatusDto orderStatus;

    @Builder
    public ItemOrderDto(Long id,
                        Long itemId,
                        String itemTitle, BigDecimal itemPrice, ItemDtoType itemType, List<OptionOrderDto> optionOrders,
                        String deliverTo,
                        Long staffMemberId,
                        DateTime orderTime,
                        String note,
                        OrderStatusDto orderStatus) {

        this.id = id;
        this.itemId = itemId;
        this.optionOrders = optionOrders;
        this.deliverTo = deliverTo;
        this.staffMemberId = staffMemberId;
        this.orderTime = orderTime;
        this.note = note;
        this.orderStatus = orderStatus;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
    }
}
