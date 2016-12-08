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
package com.github.clboettcher.bonappetit.server.menu.api.dto.read;

import com.github.clboettcher.bonappetit.server.menu.api.dto.common.ItemDtoType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(description = "A menu item")
public class ItemDto {

    @ApiModelProperty(value = "The technical ID", required = true, example = "1337")
    private Long id;

    @ApiModelProperty(value = "The title / name of this item", required = true, example = "Cola")
    private String title;

    @ApiModelProperty(value = "The price of this item. This is the 'raw' price of the item, not consisting " +
            "any options which might have effects on the total price.", required = true, example = "2.50")
    private BigDecimal price;

    @ApiModelProperty(value = "The type of this item", required = true)
    private ItemDtoType type;

    @ApiModelProperty(value = "The options available for this item")
    private List<OptionDto> options;

    @ApiModelProperty(value = "The side dishes available for this item")
    private List<ItemDto> sideDishes;

    @Builder
    public ItemDto(Long id,
                   String title,
                   BigDecimal price,
                   ItemDtoType type,
                   List<OptionDto> options,
                   List<ItemDto> sideDishes) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.type = type;
        this.options = options;
        this.sideDishes = sideDishes;
    }
}
