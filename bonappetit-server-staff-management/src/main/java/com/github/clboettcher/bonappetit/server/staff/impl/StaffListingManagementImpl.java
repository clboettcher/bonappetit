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

import com.github.clboettcher.bonappetit.server.staff.api.StaffListingManagement;
import com.github.clboettcher.bonappetit.server.staff.api.dto.StaffListingDto;
import com.github.clboettcher.bonappetit.server.staff.dao.StaffListingDao;
import com.github.clboettcher.bonappetit.server.staff.dao.StaffMemberDao;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffListingEntity;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffListingId;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Default impl of {@link StaffListingManagement}.
 */
@Component
public class StaffListingManagementImpl implements StaffListingManagement {

    @Autowired
    private StaffRequestValidator validator;

    @Autowired
    private StaffMemberDao staffMemberDao;

    @Autowired
    private StaffListingDao staffListingDao;

    @Autowired
    private StaffListingDtoMapper staffListingDtoMapper;

    @Override
    public List<StaffListingDto> getStaffListing(boolean showInactive) {

        List<StaffListingEntity> result = showInactive
                ? this.staffListingDao.findAll()
                : this.staffListingDao.findAllActive();

        return this.staffListingDtoMapper.mapToStaffListingDtos(result);
    }

    @Override
    public StaffListingDto getStaffListing(String title) {
        StaffListingEntity result = this.staffListingDao.findCurrentVersionByTitle(title);

        if (result == null) {
            throw new NotFoundException(String.format("Staff listing with title '%s' does not exist.", title));
        }

        return this.staffListingDtoMapper.mapToStaffListingDto(result);
    }

    @Override
    public StaffListingDto createStaffListing(String title, Set<Long> staffMemberIds) {
        this.validator.assertStaffListingDoesNotExist(title);
        this.validator.assertStaffMembersExist(staffMemberIds);

        StaffListingEntity saved = this.saveNewStaffListing(
                staffMemberIds,
                title,
                1,
                DateTime.now(DateTimeZone.UTC).toDate()
        );

        return this.staffListingDtoMapper.mapToStaffListingDto(saved);
    }

    @Override
    public StaffListingDto updateStaffListing(String title, Set<Long> staffMemberIds) {
        this.validator.assertStaffListingExists(title);
        this.validator.assertStaffMembersExist(staffMemberIds);
        this.validator.assertStaffMembersDifferent(title, staffMemberIds);

        Date updateTimestamp = DateTime.now(DateTimeZone.UTC).toDate();

        // Update and disable current version
        StaffListingEntity currentVersion = this.staffListingDao.findCurrentVersionByTitle(title);
        currentVersion.setValidUntil(updateTimestamp);
        this.staffListingDao.update(currentVersion);

        // Create and save new version
        StaffListingEntity saved = this.saveNewStaffListing(
                staffMemberIds,
                title,
                currentVersion.getId().getVersion() + 1,
                updateTimestamp
        );
        return this.staffListingDtoMapper.mapToStaffListingDto(saved);
    }

    private StaffListingEntity saveNewStaffListing(Set<Long> staffMemberIds,
                                                   String title,
                                                   Integer version,
                                                   Date validFrom) {
        List<StaffMemberEntity> staffMembers = this.staffMemberDao.findAllByIds(staffMemberIds);
        StaffListingEntity staffListing = StaffListingEntity.builder()
                .id(new StaffListingId(title, version))
                .staffMembers(staffMembers)
                .validFrom(validFrom)
                .build();

        return this.staffListingDao.save(staffListing);
    }
}
