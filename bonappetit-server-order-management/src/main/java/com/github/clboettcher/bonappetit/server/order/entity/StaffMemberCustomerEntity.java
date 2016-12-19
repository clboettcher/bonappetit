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
package com.github.clboettcher.bonappetit.server.order.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class StaffMemberCustomerEntity extends CustomerEntity {

    @Column(name = "STAFF_MEMBER_ID", nullable = true)
    private Long staffMemberId;

    /**
     * The first name of the staff member that represents this customer.
     * <p>
     * Use this property rather than the properties of the entity that
     * belongs to the {@link #staffMemberId}
     * because the referenced staff member might have changed since
     * the order was created.
     */
    @Column(name = "STAFF_MEMBER_FIRST_NAME", nullable = false)
    private String staffMemberFirstName;

    /**
     * The last name of the staff member that represents this customer.
     * <p>
     * Use this property rather than the properties of the entity that
     * belongs to the {@link #staffMemberId}
     * because the referenced staff member might have changed since
     * the order was created.
     */
    @Column(name = "STAFF_MEMBER_LAST_NAME", nullable = false)
    private String staffMemberLastName;

    @Builder
    public StaffMemberCustomerEntity(Long id,
                                     Long staffMemberId,
                                     String staffMemberFirstName,
                                     String staffMemberLastName) {
        super(id);
        this.staffMemberId = staffMemberId;
        this.staffMemberFirstName = staffMemberFirstName;
        this.staffMemberLastName = staffMemberLastName;
    }
}
