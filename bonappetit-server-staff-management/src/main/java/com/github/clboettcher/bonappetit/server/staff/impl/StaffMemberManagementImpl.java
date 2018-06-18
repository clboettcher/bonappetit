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

import com.github.clboettcher.bonappetit.server.staff.api.StaffMemberManagement;
import com.github.clboettcher.bonappetit.server.staff.api.dto.StaffMemberCreationDto;
import com.github.clboettcher.bonappetit.server.staff.api.dto.StaffMemberDto;
import com.github.clboettcher.bonappetit.server.staff.dao.StaffMemberDao;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Default impl of {@link StaffMemberManagement}.
 */
@Component
public class StaffMemberManagementImpl implements StaffMemberManagement {

    @Context
    private UriInfo uriInfo;

    /**
     * The bean providing access to stored staff members.
     */
    @Autowired
    private StaffMemberDao staffMemberDao;

    /**
     * The bean mapper.
     */
    @Autowired
    private StaffMemberDtoMapper toDtoMapper;

    @Autowired
    private StaffMemberEntityMapper toEntityMapper;

    @Override
    public List<StaffMemberDto> getStaffMembers() {
        List<StaffMemberEntity> result = staffMemberDao.getStaffMembers();
        return Lists.newArrayList(toDtoMapper.mapToStaffMemberDtos(result));
    }

    @Override
    public Response createStaffMembers(List<StaffMemberCreationDto> staffMemberDtos) {
        assertValid(staffMemberDtos);

        List<StaffMemberEntity> entities = toEntityMapper.mapToStaffMemberEntities(staffMemberDtos);
        staffMemberDao.save(entities);

        return Response.noContent().build();
    }

    private void assertValid(List<StaffMemberCreationDto> staffMemberCreationDtos) {
        if (CollectionUtils.isEmpty(staffMemberCreationDtos)) {
            throw new BadRequestException("At least one staff member must be provided.");
        }

        staffMemberCreationDtos.forEach(this::assertValid);
    }

    private void assertValid(StaffMemberCreationDto staffMemberDto) {
        if (staffMemberDto == null) {
            throw new BadRequestException("Request body must contain a valid staff member.");
        }

        String firstName = staffMemberDto.getFirstName();
        if (StringUtils.isBlank(firstName)) {
            throw new BadRequestException("Staff member first name may not be blank.");
        }

        String lastName = staffMemberDto.getLastName();
        if (StringUtils.isBlank(lastName)) {
            throw new BadRequestException("Staff member last name may not be blank.");
        }

        // Make sure this combination of first and last name does not exist.
        StaffMemberEntity result = staffMemberDao.findByFirstNameAndLastName(
                firstName,
                lastName
        );

        if (result != null) {
            throw new BadRequestException(String.format(
                    "Staff member named '%s %s' already exists.",
                    firstName,
                    lastName
            ));
        }
    }
}
