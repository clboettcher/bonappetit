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
package com.github.clboettcher.bonappetit.server.staff.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * A list of staff members that are active at a point in time.
 */
@Entity
@Table(name = "STAFF_LISTING")
@NoArgsConstructor
@ToString
public class StaffListingEntity {

    @EmbeddedId
    private StaffListingId id;

    /**
     * The members that this listing consists of.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "STAFF_LISTING_STAFF_MEMBER",
            joinColumns = {
                    @JoinColumn(name = "STAFF_LISTING_TITLE", referencedColumnName = "TITLE"),
                    @JoinColumn(name = "STAFF_LISTING_VERSION", referencedColumnName = "VERSION")
            },
            inverseJoinColumns = @JoinColumn(name = "STAFF_MEMBER_ID"))
    private List<StaffMemberEntity> staffMembers;

    @Column(name = "VALID_FROM", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;

    @Column(name = "VALID_UNTIL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validUntil;

    /**
     * Constructor setting the specified properties.
     */
    @Builder
    public StaffListingEntity(StaffListingId id,
                              List<StaffMemberEntity> staffMembers,
                              Date validFrom,
                              Date validUntil) {
        this.id = id;
        this.staffMembers = staffMembers;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }


    public StaffListingId getId() {
        return id;
    }

    public List<StaffMemberEntity> getStaffMembers() {
        return staffMembers;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
