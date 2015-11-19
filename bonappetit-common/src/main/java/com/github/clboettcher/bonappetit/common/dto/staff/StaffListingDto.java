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
package com.github.clboettcher.bonappetit.common.dto.staff;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

/**
 * A collection of staff members.
 */
public class StaffListingDto {

    /**
     * See {@link #getStaffMemberDtos()}.
     */
    private Set<StaffMemberDto> staffMemberDtos;

    /**
     * No-op no-args constructor.
     */
    public StaffListingDto() {
    }

    private StaffListingDto(Builder builder) {
        setStaffMemberDtos(builder.staffMemberDtos);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * @return The members that correspond to this listing.
     */
    public Set<StaffMemberDto> getStaffMemberDtos() {
        return staffMemberDtos;
    }

    /**
     * @param staffMemberDtos see {@link #getStaffMemberDtos()}.
     */
    public void setStaffMemberDtos(Set<StaffMemberDto> staffMemberDtos) {
        this.staffMemberDtos = staffMemberDtos;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("staffMembers", staffMemberDtos)
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
        StaffListingDto rhs = (StaffListingDto) obj;
        return new EqualsBuilder()
                .append(this.staffMemberDtos, rhs.staffMemberDtos)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.staffMemberDtos)
                .hashCode();
    }

    /**
     * {@code StaffListingDto} builder static inner class.
     */
    public static final class Builder {
        private Set<StaffMemberDto> staffMemberDtos;

        private Builder() {
        }

        /**
         * Sets the {@code staffMembers} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code staffMembers} to set
         * @return a reference to this Builder
         */
        public Builder staffMembers(Set<StaffMemberDto> val) {
            staffMemberDtos = val;
            return this;
        }

        /**
         * Returns a {@code StaffListingDto} built from the parameters previously set.
         *
         * @return a {@code StaffListingDto} built with parameters of this {@code StaffListingDto.Builder}
         */
        public StaffListingDto build() {
            return new StaffListingDto(this);
        }
    }
}
