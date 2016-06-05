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
package com.github.clboettcher.bonappetit.server.impl;

import com.github.clboettcher.bonappetit.domain.staff.StaffMember;
import com.github.clboettcher.bonappetit.dto.staff.StaffMemberDto;
import com.github.clboettcher.bonappetit.server.api.StaffMembersService;
import com.github.clboettcher.bonappetit.server.impl.mapping.StaffMemberDtoMapper;
import com.github.clboettcher.bonappetit.server.staff.api.StaffManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StaffMembersServiceImpl implements StaffMembersService {

    /**
     * The management bean for {@link StaffMember}s.
     */
    private StaffManagement staffManagement;


    /**
     * Constructor setting the specified properties.
     *
     * @param staffManagement see {@link #staffManagement}.
     */
    @Autowired
    public StaffMembersServiceImpl(StaffManagement staffManagement) {
        this.staffManagement = staffManagement;
    }

    @Override
    public Set<StaffMemberDto> getStaffMembers() {
        Set<StaffMember> result = staffManagement.getStaffMembers();
        return StaffMemberDtoMapper.INSTANCE.convert(result);
    }
}
