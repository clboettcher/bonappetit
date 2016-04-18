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
package com.github.clboettcher.bonappetit.dto.order;

import com.github.clboettcher.bonappetit.dto.menu.ValueOptionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(description = "An order for a value option")
public class ValueOptionOrderDto extends OptionOrderDto {

    @ApiModelProperty(value = "The ordered option", required = true)
    private ValueOptionDto option;

    @ApiModelProperty(value = "Whether the option has been checked or not", required = true)
    private Boolean checked;

    @ApiModelProperty(value = "The ordered value", required = true, example = "2")
    private int value;

    /**
     * Constructor setting the specified properties.
     *
     * @param option  see {@link #option}.
     * @param checked see {@link #checked}.
     * @param value   see {@link #value}.
     */
    public ValueOptionOrderDto(ValueOptionDto option, Boolean checked, int value) {
        this.option = option;
        this.checked = checked;
        this.value = value;
    }
}
