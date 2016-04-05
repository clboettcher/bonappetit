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
package com.github.clboettcher.bonappetit.server.rest;

import com.github.clboettcher.bonappetit.server.BonappetitServerApplication;
import com.github.clboettcher.bonappetit.server.entity.builder.StaffListingBuilder;
import com.github.clboettcher.bonappetit.server.entity.builder.StaffMemberBuilder;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;
import com.github.clboettcher.bonappetit.server.repository.StaffListingRepository;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests the rest access for {@link com.github.clboettcher.bonappetit.server.entity.staff.StaffListing}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BonappetitServerApplication.class)
@WebIntegrationTest
public class StaffListingTests {

    @Autowired
    private StaffListingRepository staffListingRepository;

    private TestRestTemplate restTemplate;
    private StaffListing staffListing;

    @Before
    public void setUp() throws Exception {
        staffListing = StaffListingBuilder.aStaffListing()
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
        staffListingRepository.save(staffListing);
        restTemplate = new TestRestTemplate("user", "s3cret");
    }

    @Test
    public void testGetStaffLising() throws Exception {
        String url = String.format("http://localhost:8080/staffListings/%s", staffListing.getId());
        ParameterizedTypeReference<Resource<StaffListing>> responseType = new ParameterizedTypeReference<Resource<StaffListing>>() {
        };

        ResponseEntity<Resource<StaffListing>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

        StaffListing actual = responseEntity.getBody().getContent();
        assertThat(actual.getStaffMembers().size(), is(2));
    }
}
