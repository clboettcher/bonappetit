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
package com.github.clboettcher.bonappetit.server.menu.api.dto.read;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(description = "An option that consists of multiple items of which one must be selected")
public class RadioOptionDto extends OptionDto {

    @ApiModelProperty(value = "The raiod itme that should be selected per default.", required = true)
    private RadioItemDto defaultSelected;

    @ApiModelProperty(value = "The items that this option consists of.", required = true)
    private List<RadioItemDto> radioItems;

    /**
     * Constructor setting the specified properties.
     *
     * @param id              see {@link #getId()}.
     * @param title           see {@link #getTitle()}.
     * @param index           see {@link #index}.
     * @param defaultSelected see {@link #getDefaultSelected()}.
     * @param radioItems      see {@link #getRadioItems()}.
     */
    @Builder
    public RadioOptionDto(Long id, String title, Integer index, RadioItemDto defaultSelected, List<RadioItemDto> radioItems) {
        super(id, title, index);
        this.defaultSelected = defaultSelected;
        this.radioItems = radioItems;
    }
}
