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
package com.github.clboettcher.bonappetit.server.menu.impl.entity.config;

import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.MenuEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Holds menu configuration.
 * <p>
 * E.g. which menu is active at the moment. The corresponding table will never contain
 * more than one row.
 */
@Entity
@Table(name = "MENU_CONFIG")
@Data
@NoArgsConstructor
public class MenuConfig {

    /**
     * The ID.
     */
    @Id
    @GeneratedValue
    @Column(name = "MENU_CONFIG_ID")
    private long id;

    /**
     * The currently active menu.
     */
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "MENU_ID", nullable = false)
    private MenuEntity current;

    /**
     * Constructor setting the specified properties.
     *
     * @param id      see {@link #id}.
     * @param current see {@link #current}.
     */
    @Builder
    public MenuConfig(long id, MenuEntity current) {
        this.id = id;
        this.current = current;
    }
}
