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
package com.github.clboettcher.bonappetit.server.staff.fc.impl;

import com.github.clboettcher.bonappetit.server.staff.StaffMemberResource;
import com.github.clboettcher.bonappetit.server.staff.ba.ListStaffMembersActivity;
import com.github.clboettcher.bonappetit.server.staff.et.StaffMemberEntity;
import com.github.clboettcher.bonappetit.server.staff.to.StaffMemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Default impl of {@link StaffMemberResource}.
 */
@Component
public class StaffMemberResourceImpl implements StaffMemberResource {

    /**
     * The activity for listing staff members.
     */
    @Autowired
    private ListStaffMembersActivity listMembersActivity;

    /**
     * The bean mapper.
     */
    @Autowired
    private StaffMemberDtoMapper mapper;

    @Override
    public Set<StaffMemberDto> getStaffMembers() {
        Set<StaffMemberEntity> result = listMembersActivity.getStaffMembers();
        return mapper.mapToStaffMemberDtos(result);
    }
}
