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
package com.github.clboettcher.bonappetit.server.impl.mapping;

import com.github.clboettcher.bonappetit.domain.staff.StaffMember;
import com.github.clboettcher.bonappetit.dto.staff.StaffMemberDto;
import com.google.common.collect.Sets;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Tests for {@link StaffMemberDtoMapper}.
 */
public class StaffMemberDtoMapperTests {

    @Test
    public void testConverter() throws Exception {
        HashSet<StaffMember> input = Sets.newHashSet(
                StaffMember.builder()
                        .id(1)
                        .firstName("John")
                        .lastName("Smith")
                        .build(),
                StaffMember.builder()
                        .id(2)
                        .firstName("Jane")
                        .lastName("Smith")
                        .build()
        );
        Set<StaffMemberDto> actual = StaffMemberDtoMapper.INSTANCE.convert(input);

        Assert.assertThat(actual.size(), Matchers.is(2));
    }
}
