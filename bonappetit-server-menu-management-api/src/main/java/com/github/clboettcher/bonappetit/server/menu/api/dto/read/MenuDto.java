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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.Set;

/**
 * The menu represents the items that can be ordered.
 */
@NoArgsConstructor
@Data
@ApiModel(description = "The menu contains the items that can be ordered")
public class MenuDto {

    @ApiModelProperty(value = "The technical ID", required = true, example = "1337")
    private Long id;

    @ApiModelProperty(value = "The title of this menu", example = "My awesome menu", required = true)
    private String title;

    @ApiModelProperty(value = "The timestamp that the menu was created")
    private DateTime creationTimestamp;

    @ApiModelProperty(value = "The items that this menu consists of")
    private Set<ItemDto> items;

    @ApiModelProperty(value = "The timestamp that this menu was last updated.", required = true)
    private DateTime lastUpdateTimestamp;

    @Builder
    public MenuDto(Long id,
                   String title,
                   Set<ItemDto> items,
                   DateTime lastUpdateTimestamp) {
        this.id = id;
        this.title = title;
        this.items = items;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }
}
