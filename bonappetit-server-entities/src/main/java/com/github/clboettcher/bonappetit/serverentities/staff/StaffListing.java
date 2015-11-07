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
package com.github.clboettcher.bonappetit.serverentities.staff;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

/**
 * A collection of staff members.
 */
public class StaffListing {

    /**
     * See {@link #getStaffMembers()}.
     */
    private Set<StaffMember> staffMembers;

    /**
     * @return The members that correspond to this listing.
     */
    public Set<StaffMember> getStaffMembers() {
        return staffMembers;
    }

    /**
     * @param staffMembers see {@link #getStaffMembers()}.
     */
    public void setStaffMembers(Set<StaffMember> staffMembers) {
        this.staffMembers = staffMembers;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("staffMembers", staffMembers)
                .toString();
    }
}
