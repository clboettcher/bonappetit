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
package com.github.clboettcher.bonappetit.server.persistence.impl.entity.config;

import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.MenuEntity;

import javax.persistence.*;

/**
 * Holds menu configuration.
 * <p/>
 * E.g. which menu is active at the moment. The corresponding table will never contain
 * more than one row.
 */
@Entity
@Table(name = "MENU_CONFIG")
public class MenuConfig {

    @Id
    @GeneratedValue
    @Column(name = "MENU_CONFIG_ID")
    private long id;

    @OneToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "MENU_ID", nullable = false)
    private MenuEntity current;

    /**
     * @return The ID of this menu config.
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return The menu that is currently active.
     */
    public MenuEntity getCurrent() {
        return current;
    }

    public void setCurrent(MenuEntity current) {
        this.current = current;
    }
}
