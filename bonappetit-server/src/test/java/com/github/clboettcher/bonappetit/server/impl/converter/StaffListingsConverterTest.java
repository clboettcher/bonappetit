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
package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.builder.StaffListingDtoBuilder;
import com.github.clboettcher.bonappetit.common.dto.builder.StaffMemberDtoBuilder;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;
import com.github.clboettcher.bonappetit.server.entity.builder.StaffListingBuilder;
import com.github.clboettcher.bonappetit.server.entity.builder.StaffMemberBuilder;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;
import com.github.clboettcher.bonappetit.server.impl.converter.api.StaffListingsConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.impl.StaffListingsConverterImpl;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link StaffListingsConverterImpl}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StaffListingsConverterTest.Context.class)
public class StaffListingsConverterTest {

    @Autowired
    private StaffListingsConverter converter;

    @Test
    public void testConversion() throws Exception {
        final StaffListing input = StaffListingBuilder.aStaffListing()
                .withStaffMembers(Sets.newHashSet(
                        StaffMemberBuilder.aStaffMember()
                                .withFirstName("John")
                                .withLastName("Smith")
                                .build(),
                        StaffMemberBuilder.aStaffMember()
                                .withFirstName("Agathe")
                                .withLastName("Bauer")
                                .build()
                ))
                .build();

        final StaffListingDto expected = StaffListingDtoBuilder.aStaffListingDto()
                .withStaffMemberDtos(Sets.newHashSet(
                        StaffMemberDtoBuilder.aStaffMemberDto()
                                .withFirstName("John")
                                .withLastName("Smith")
                                .build(),
                        StaffMemberDtoBuilder.aStaffMemberDto()
                                .withFirstName("Agathe")
                                .withLastName("Bauer")
                                .build()
                ))
                .build();

        assertThat(converter.convertToDto(input), is(expected));
    }

    static class Context {
        @Bean
        public StaffListingsConverter staffListingsConverter() {
            return new StaffListingsConverterImpl();
        }
    }
}
