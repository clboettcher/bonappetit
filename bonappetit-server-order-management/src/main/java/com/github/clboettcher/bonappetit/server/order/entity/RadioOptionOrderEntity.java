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
package com.github.clboettcher.bonappetit.server.order.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * An order for a radio option.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RadioOptionOrderEntity extends AbstractOptionOrderEntity {

    /**
     * The ID of the radio item that was selected.
     */
    @Column(name = "SELECTED_RADIO_ITEM_ID", nullable = true)
    private Long selectedItemId;

    /**
     * Constructor setting the specified properties.
     *
     * @param id             see {@link #getId()}.
     * @param selectedItemId see {@link #getSelectedItemId()}.
     */
    @Builder
    public RadioOptionOrderEntity(long id, Long selectedItemId) {
        super(id);
        this.selectedItemId = selectedItemId;
    }
}
