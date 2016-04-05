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

import com.github.clboettcher.bonappetit.server.entity.staff.StaffMember;

public class StaffMemberBuilder {
    private long id;
    private String firstName;
    private String lastName;

    private StaffMemberBuilder() {
    }

    public static StaffMemberBuilder aStaffMember() {
        return new StaffMemberBuilder();
    }

    public StaffMemberBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public StaffMemberBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StaffMemberBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StaffMemberBuilder but() {
        return aStaffMember().withId(id).withFirstName(firstName).withLastName(lastName);
    }

    public StaffMember build() {
        StaffMember staffMember = new StaffMember();
        staffMember.setId(id);
        staffMember.setFirstName(firstName);
        staffMember.setLastName(lastName);
        return staffMember;
    }
}
