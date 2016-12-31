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

import com.github.clboettcher.bonappetit.server.staff.dao.impl.StaffMemberEntityRepository;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
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
public class StaffMembersBootstrap {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StaffMembersBootstrap.class);


    public static final List<StaffMemberEntity> TEST_DATA = Arrays.asList(
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
    public StaffMembersBootstrap(StaffMemberEntityRepository staffMemberEntityRepository) {
        LOGGER.info("Saving test staff members in the DB.");
        staffMemberEntityRepository.save(TEST_DATA);
    }
}
