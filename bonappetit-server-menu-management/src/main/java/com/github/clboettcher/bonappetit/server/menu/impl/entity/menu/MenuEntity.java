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
package com.github.clboettcher.bonappetit.server.menu.impl.entity.menu;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The menu represents the items that can be ordered.
 */
@Entity
@Table(name = "MENU")
@NoArgsConstructor
@ToString
public class MenuEntity {

    /**
     * The ID.
     */
    @Id
    @GeneratedValue
    @Column(name = "MENU_ID")
    private Long id;

    /**
     * The timestamp that the menu was taken.
     */
    @Column(name = "CREATION_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTimestamp;

    /**
     * The timestamp that the menu was created.
     */
    @Column(name = "TITLE", nullable = false)
    private String title;

    /**
     * The items that this menu consists of.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "MENU_ID", nullable = false)
    private List<ItemEntity> items;

    /**
     * Constructor setting the specified properties.
     *
     * @param title             see {@link #title}.
     * @param creationTimestamp see {@link #creationTimestamp}.
     * @param items             see {@link #items}.
     */
    @Builder
    public MenuEntity(Long id, String title, Date creationTimestamp, List<ItemEntity> items) {
        this.id = id;
        this.creationTimestamp = creationTimestamp;
        this.title = title;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
