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
package com.github.clboettcher.bonappetit.server.entity.staff;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Set;

/**
 * A collection of staff members.
 */
@Entity
@Table(name = "STAFF_LISTING")
public class StaffListing {

    @Id
    @GeneratedValue
    @Column(name = "STAFF_LISTING_ID")
    private long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "STAFF_LISTING_STAFF_MEMBER", joinColumns = @JoinColumn(name = "STAFF_LISTING_ID"),
            inverseJoinColumns = @JoinColumn(name = "STAFF_MEMBER_ID"))
    private Set<StaffMember> staffMembers;

    /**
     * @return The members that correspond to this listing.
     */
    public Set<StaffMember> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(Set<StaffMember> staffMembers) {
        this.staffMembers = staffMembers;
    }

    /**
     * @return The ID of this event.
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("staffMembers", staffMembers)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        StaffListing rhs = (StaffListing) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.staffMembers, rhs.staffMembers)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(staffMembers)
                .toHashCode();
    }
}
