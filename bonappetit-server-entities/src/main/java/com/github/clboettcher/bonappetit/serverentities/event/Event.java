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
package com.github.clboettcher.bonappetit.serverentities.event;

import com.github.clboettcher.bonappetit.serverentities.menu.Menu;
import com.github.clboettcher.bonappetit.serverentities.staff.StaffListing;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An event consisting of staff members and a menu.
 */
@Entity
@XmlRootElement
public class Event {

    /**
     * See {@link #getId()}.
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * See {@link #getTitle()}.
     */
    private String title;

    /**
     * See {@link #getMenu()}.
     */
    private Menu menu;

    /**
     * See {@link #getStaffListing()}.
     */
    private StaffListing staffListing;

    /**
     * @return The ID of this event.
     */
    public long getId() {
        return id;
    }

    /**
     * @return The unique title / name of this event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title see {@link #getTitle()}.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The menu that is served during this event.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu see {@link #getMenu()}.
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * @return The listing of staff members that work in this event.
     */
    public StaffListing getStaffListing() {
        return staffListing;
    }

    /**
     * @param staffListing see {@link #getStaffListing()}.
     */
    public void setStaffListing(StaffListing staffListing) {
        this.staffListing = staffListing;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("title", title)
                .append("menu", menu)
                .append("staffListing", staffListing)
                .toString();
    }
}
