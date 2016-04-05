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
package com.github.clboettcher.bonappetit.server.entity.builder;

import com.github.clboettcher.bonappetit.server.entity.event.Event;
import com.github.clboettcher.bonappetit.server.entity.menu.Menu;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;

import java.util.Date;

public class EventBuilder {
    private long id;
    private String title;
    private Date created;
    private Menu menu;
    private StaffListing staffListing;

    private EventBuilder() {
    }

    public static EventBuilder anEvent() {
        return new EventBuilder();
    }

    public EventBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public EventBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public EventBuilder withCreated(Date created) {
        this.created = created;
        return this;
    }

    public EventBuilder withMenu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public EventBuilder withStaffListing(StaffListing staffListing) {
        this.staffListing = staffListing;
        return this;
    }

    public EventBuilder but() {
        return anEvent().withId(id).withTitle(title).withCreated(created).withMenu(menu).withStaffListing(staffListing);
    }

    public Event build() {
        Event event = new Event();
        event.setId(id);
        event.setTitle(title);
        event.setCreated(created);
        event.setMenu(menu);
        event.setStaffListing(staffListing);
        return event;
    }
}
