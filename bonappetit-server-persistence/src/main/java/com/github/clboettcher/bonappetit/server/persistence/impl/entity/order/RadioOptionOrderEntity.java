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
package com.github.clboettcher.bonappetit.server.persistence.impl.entity.order;

import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.RadioItemEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * An order for a {@link RadioItemEntity}.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RadioOptionOrderEntity extends AbstractOptionOrderEntity {

    /**
     * The radio item that was selected.
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "SELECTED_RADIO_ITEM_ID", nullable = false)
    private RadioItemEntity selectedItem;

    /**
     * Constructor setting the specified properties.
     *
     * @param id           see {@link #getId()}.
     * @param selectedItem see {@link #getSelectedItem()}.
     */
    @Builder
    public RadioOptionOrderEntity(long id, RadioItemEntity selectedItem) {
        super(id);
        this.selectedItem = selectedItem;
    }
}
