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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(description = "An order for a checkbox option")
@ToString(callSuper = true)
public class CheckboxOptionOrderDto extends OptionOrderDto {

    @ApiModelProperty(value = "The id of the ordered option", required = true)
    private Long checkboxOptionId;

    @ApiModelProperty(value = "Whether the option has been checked or not", required = true)
    private Boolean checked;

    /**
     * Constructor setting the specified properties.
     *
     * @param checkboxOptionId see {@link #checkboxOptionId}.
     * @param checked          see {@link #checked}.
     * @param optionTitle      see {@link #optionTitle}.
     * @param optionPriceDiff  see {@link #optionPriceDiff}.
     */
    @Builder
    public CheckboxOptionOrderDto(Long checkboxOptionId,
                                  Boolean checked,
                                  String optionTitle,
                                  BigDecimal optionPriceDiff) {
        super(optionTitle, optionPriceDiff);
        this.checkboxOptionId = checkboxOptionId;
        this.checked = checked;
    }
}
