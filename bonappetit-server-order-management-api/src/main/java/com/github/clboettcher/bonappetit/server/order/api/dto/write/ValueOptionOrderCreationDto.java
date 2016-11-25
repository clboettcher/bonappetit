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
package com.github.clboettcher.bonappetit.server.order.api.dto.write;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(description = "An order for a value option")
public class ValueOptionOrderCreationDto extends OptionOrderCreationDto {

    @ApiModelProperty(value = "The server ID of the ordered option", required = true)
    private Long optionId;

    @ApiModelProperty(value = "The ordered value", required = true, example = "2")
    private int value;

    /**
     * Constructor setting the specified properties.
     *
     * @param optionId see {@link #optionId}.
     * @param value    see {@link #value}.
     */
    @Builder
    public ValueOptionOrderCreationDto(Long optionId, int value) {
        this.optionId = optionId;
        this.value = value;
    }
}
