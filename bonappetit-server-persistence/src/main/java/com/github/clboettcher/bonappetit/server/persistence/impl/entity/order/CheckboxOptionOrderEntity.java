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

import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.CheckboxOptionEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * An order for a {@link CheckboxOptionEntity}.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CheckboxOptionOrderEntity extends AbstractOptionOrderEntity {

    /**
     * The ordered option.
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "CHECKBOX_OPTION_ID", referencedColumnName = "OPTION_ID")
    private CheckboxOptionEntity option;

    /**
     * Whether the option has been checked or not.
     */
    @Column(name = "CHECKED", nullable = false)
    private Boolean checked;

    /**
     * Constructor setting the specified properties.
     *
     * @param id      see {@link #id}.
     * @param option  see {@link #option}.
     * @param checked see {@link #checked}.
     */
    @Builder
    public CheckboxOptionOrderEntity(long id, CheckboxOptionEntity option, Boolean checked) {
        super(id);
        this.option = option;
        this.checked = checked;
    }
}
