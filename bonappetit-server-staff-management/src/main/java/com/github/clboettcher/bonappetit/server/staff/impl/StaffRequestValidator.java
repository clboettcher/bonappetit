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
package com.github.clboettcher.bonappetit.server.staff.impl;

import com.github.clboettcher.bonappetit.server.staff.dao.StaffListingDao;
import com.github.clboettcher.bonappetit.server.staff.dao.StaffMemberDao;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffListingEntity;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.BadRequestException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StaffRequestValidator {

    @Autowired
    private StaffMemberDao staffMemberDao;

    @Autowired
    private StaffListingDao staffListingDao;

    void assertStaffMembersExist(Set<Long> staffMemberIds) {
        if (CollectionUtils.isEmpty(staffMemberIds)) {
            throw new BadRequestException("List of staff members must be provided.");
        }

        for (Long staffMemberId : staffMemberIds) {
            if (!staffMemberDao.exists(staffMemberId)) {
                throw new BadRequestException(String.format("Staff member with id %d does not exist.", staffMemberId));
            }
        }

    }

    void assertStaffListingExists(String title) {
        StaffListingEntity result = staffListingDao.findCurrentVersionByTitle(title);

        if (result == null) {
            throw new BadRequestException(String.format("Staff listing with title '%s' does not exist.", title));
        }
    }

    void assertStaffListingDoesNotExist(String title) {
        StaffListingEntity result = staffListingDao.findCurrentVersionByTitle(title);

        if (result != null) {
            throw new BadRequestException(String.format("Staff listing with title '%s' already exists.", title));
        }
    }

    void assertStaffMembersDifferent(String title, Set<Long> staffMemberIdsUpdate) {
        StaffListingEntity result = staffListingDao.findCurrentVersionByTitle(title);

        Set<Long> stored = result.getStaffMembers().stream().map(StaffMemberEntity::getId).collect(Collectors.toSet());

        if (stored.equals(staffMemberIdsUpdate)) {
            throw new BadRequestException(String.format(
                    "List of staff member ids must be different than the stored ids when updating a staff listing. " +
                            "Update contained: %s",
                    staffMemberIdsUpdate
            ));
        }

    }
}
