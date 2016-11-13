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

import com.github.clboettcher.bonappetit.server.staff.api.dto.StaffMemberDto;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import com.github.clboettcher.bonappetit.server.staff.impl.StaffMemberDtoMapper;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link StaffMemberDtoMapper}.
 */
public class StaffMemberDtoMapperTests {

    private StaffMemberDtoMapper mapper;

    @Before
    public void setUp() throws Exception {
        this.mapper = Mappers.getMapper(StaffMemberDtoMapper.class);
    }

    @Test
    public void testMapper() throws Exception {
        List<StaffMemberEntity> input = Lists.newArrayList(
                StaffMemberEntity.builder()
                        .id(1L)
                        .firstName("John")
                        .lastName("Smith")
                        .build(),
                StaffMemberEntity.builder()
                        .id(2L)
                        .firstName("Jane")
                        .lastName("Smith")
                        .build()
        );
        List<StaffMemberDto> actual = mapper.mapToStaffMemberDtos(input);
        assertThat(actual.size(), is(2));
        for (StaffMemberDto staffMemberDto : actual) {
            assertThat(staffMemberDto.getId(), notNullValue());
            assertThat(staffMemberDto.getFirstName(), notNullValue());
            assertThat(staffMemberDto.getLastName(), notNullValue());
        }
    }
}
