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
package com.github.clboettcher.bonappetit.server.staff.dao;

import com.github.clboettcher.bonappetit.server.staff.dao.impl.StaffListingEntityRepository;
import com.github.clboettcher.bonappetit.server.staff.dao.impl.StaffMemberEntityRepository;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffListingEntity;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffListingId;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * This class bootstraps some testdata into the db and will be deleted at some point.
 */
@Component
@Profile("INMEM")
public class StaffBootstrap {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StaffBootstrap.class);


    private static final List<StaffMemberEntity> STAFF_MEMBERS_TEST_DATA = Arrays.asList(
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

    /**
     * Constructor that saves some testdata in the db.
     *
     * @param staffMemberEntityRepository The bean used to save the staff members.
     */
    @Autowired
    public StaffBootstrap(StaffMemberEntityRepository staffMemberEntityRepository,
                          StaffListingEntityRepository staffListingEntityRepository) {
        LOGGER.info("Saving test staff members in the DB.");
        Iterable<StaffMemberEntity> saved = staffMemberEntityRepository.save(STAFF_MEMBERS_TEST_DATA);

        StaffListingEntity staffListing = StaffListingEntity.builder()
                .validFrom(DateTime.now(DateTimeZone.UTC).toDate())
                .staffMembers(Lists.newArrayList(saved))
                .id(new StaffListingId("Friday Lounge", 1))
                .build();

        staffListingEntityRepository.save(staffListing);
    }
}
