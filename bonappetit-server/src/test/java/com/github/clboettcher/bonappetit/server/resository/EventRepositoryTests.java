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
package com.github.clboettcher.bonappetit.server.resository;

import com.github.clboettcher.bonappetit.common.entity.ItemType;
import com.github.clboettcher.bonappetit.server.BonappetitServerApplication;
import com.github.clboettcher.bonappetit.server.entity.builder.*;
import com.github.clboettcher.bonappetit.server.entity.event.Event;
import com.github.clboettcher.bonappetit.server.entity.menu.Menu;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;
import com.github.clboettcher.bonappetit.server.repository.EventRepository;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link EventRepository}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = BonappetitServerApplication.class)
public class EventRepositoryTests {


    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testSaveEvent() throws Exception {
        Menu menu = MenuBuilder.aMenu()
                .withItems(Sets.newHashSet(ItemBuilder.anItem()
                        .withTitle("Title")
                        .withPrice(new BigDecimal("1.9"))
                        .withType(ItemType.DRINK_NON_ALCOHOLIC)
                        .build()))
                .build();

        StaffListing staffListing = StaffListingBuilder.aStaffListing()
                .withStaffMembers(Sets.newHashSet(
                        StaffMemberBuilder.aStaffMember()
                                .withFirstName("John")
                                .withLastName("Smith")
                                .build()
                ))
                .build();

        Event event = EventBuilder.anEvent()
                .withTitle("Title")
                .withCreated(new Date())
                .withMenu(menu)
                .withStaffListing(staffListing)
                .build();

        eventRepository.save(event);

        Event dbEvent = eventRepository.findOne(event.getId());
        assertThat(dbEvent.getCreated(), notNullValue());
        assertThat(dbEvent.getTitle(), not(isEmptyOrNullString()));
        assertThat(dbEvent.getMenu(), notNullValue());
        assertThat(dbEvent.getStaffListing(), notNullValue());
        // For more complete tests see test classes for the individual repositories.
    }
}
