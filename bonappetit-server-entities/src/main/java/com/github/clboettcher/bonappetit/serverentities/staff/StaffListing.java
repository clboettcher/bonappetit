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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

/**
 * A collection of staff members.
 */
@Entity
public class StaffListing {

    /**
     * See {@link #getId()}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * See {@link #getStaffMembers()}.
     */
    private Set<StaffMember> staffMembers;

    public StaffListing() {
    }

    private StaffListing(Builder builder) {
        id = builder.id;
        setStaffMembers(builder.staffMembers);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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
                .append("id", id)
                .append("staffMembers", staffMembers)
                .toString();
    }

    /**
     * @return The ID of this entity.
     */
    public long getId() {
        return id;
    }

    /**
     * {@code StaffListing} builder static inner class.
     */
    public static final class Builder {
        private Set<StaffMember> staffMembers;
        private long id;

        private Builder() {
        }

        /**
         * Sets the {@code staffMembers} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code staffMembers} to set
         * @return a reference to this Builder
         */
        public Builder staffMembers(Set<StaffMember> val) {
            staffMembers = val;
            return this;
        }

        /**
         * Returns a {@code StaffListing} built from the parameters previously set.
         *
         * @return a {@code StaffListing} built with parameters of this {@code StaffListing.Builder}
         */
        public StaffListing build() {
            return new StaffListing(this);
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(long val) {
            id = val;
            return this;
        }
    }
}
