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

import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffMember;

import java.util.Set;

public class StaffListingBuilder {
    private long id;
    private Set<StaffMember> staffMembers;

    private StaffListingBuilder() {
    }

    public static StaffListingBuilder aStaffListing() {
        return new StaffListingBuilder();
    }

    public StaffListingBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public StaffListingBuilder withStaffMembers(Set<StaffMember> staffMembers) {
        this.staffMembers = staffMembers;
        return this;
    }

    public StaffListingBuilder but() {
        return aStaffListing().withId(id).withStaffMembers(staffMembers);
    }

    public StaffListing build() {
        StaffListing staffListing = new StaffListing();
        staffListing.setId(id);
        staffListing.setStaffMembers(staffMembers);
        return staffListing;
    }
}
