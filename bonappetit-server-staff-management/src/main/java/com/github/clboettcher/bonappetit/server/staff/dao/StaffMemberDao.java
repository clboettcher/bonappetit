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
package com.github.clboettcher.bonappetit.server.staff.dao;

import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;

import java.util.List;
import java.util.Set;

/**
 * Provides access to stored {@link StaffMemberEntity}s.
 */
public interface StaffMemberDao {

    /**
     * Returns all stored staff members.
     *
     * @return All stored {@link StaffMemberEntity}s. May be empty.
     */
    List<StaffMemberEntity> getStaffMembers();

    /**
     * Returns the staff member with the givern id.
     *
     * @param id The id.
     *
     * @return The staff member, or null if it does not exist.
     */
    StaffMemberEntity getStaffMember(Long id);

    /**
     * Returns whether a staff member exists in the db with the specified id.
     *
     * @param id the ID.
     *
     * @return true, if a staff member exists with the given id.
     */
    boolean exists(Long id);

    /**
     * Saves the given staff members in the db.
     *
     * @param staffMember The staff members.
     *
     * @return The saved staff members (with IDs not null).
     */
    List<StaffMemberEntity> save(List<StaffMemberEntity> staffMember);

    List<StaffMemberEntity> findAllByIds(Set<Long> staffMemberIds);

    StaffMemberEntity findByFirstNameAndLastName(String firstName, String lastName);
}
