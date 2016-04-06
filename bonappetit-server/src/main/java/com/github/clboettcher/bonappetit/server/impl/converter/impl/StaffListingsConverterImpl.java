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
package com.github.clboettcher.bonappetit.server.impl.converter.impl;

import com.github.clboettcher.bonappetit.common.dto.builder.StaffListingDtoBuilder;
import com.github.clboettcher.bonappetit.common.dto.builder.StaffMemberDtoBuilder;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffMemberDto;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffMember;
import com.github.clboettcher.bonappetit.server.impl.converter.api.StaffListingsConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Default impl of {@link StaffListingsConverter}.
 */
@Component
public class StaffListingsConverterImpl implements StaffListingsConverter {

    @Override
    public StaffListingDto convertToDto(StaffListing staffListing) {
        return StaffListingDtoBuilder.aStaffListingDto()
                .withStaffMemberDtos(convertToDto(staffListing.getStaffMembers()))
                .build();
    }

    /**
     * Converts the given {@link StaffMember}s to {@link StaffMemberDto}s.
     *
     * @param staffMembers The {@link StaffMember}s to convert.
     * @return The resulting {@link StaffMemberDto}s.
     */
    private static Set<StaffMemberDto> convertToDto(Set<StaffMember> staffMembers) {
        Set<StaffMemberDto> staffMemberDtos = new HashSet<>(staffMembers.size());
        for (StaffMember staffMember : staffMembers) {
            staffMemberDtos.add(convertToDto(staffMember));
        }

        return staffMemberDtos;
    }

    /**
     * Converts the given {@link StaffMember} to a {@link StaffMemberDto}.
     *
     * @param staffMember The {@link StaffMember} to convert.
     * @return The resulting {@link StaffMemberDto}.
     */
    private static StaffMemberDto convertToDto(StaffMember staffMember) {
        return StaffMemberDtoBuilder.aStaffMemberDto()
                .withFirstName(staffMember.getFirstName())
                .withLastName(staffMember.getLastName())
                .build();
    }
}
