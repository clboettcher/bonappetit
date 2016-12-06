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

/**
 * An order for a radio option.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("An order for a radio option")
@ToString(callSuper = true)
public class RadioOptionOrderDto extends OptionOrderDto {

    @ApiModelProperty(value = "The id of the radio item that was selected", required = true)
    private Long selectedRadioItemId;

    /**
     * Constructor setting the specified properties.
     *
     * @param selectedRadioItemId see {@link #selectedRadioItemId}.
     * @param selectedRadioItemTitle         see {@link #optionTitle}.
     * @param selectedRadioItemPriceDiff     see {@link #optionPriceDiff}.
     */
    @Builder
    public RadioOptionOrderDto(Long selectedRadioItemId,
                               String selectedRadioItemTitle,
                               BigDecimal selectedRadioItemPriceDiff) {
        super(selectedRadioItemTitle, selectedRadioItemPriceDiff);
        this.selectedRadioItemId = selectedRadioItemId;
    }

}
