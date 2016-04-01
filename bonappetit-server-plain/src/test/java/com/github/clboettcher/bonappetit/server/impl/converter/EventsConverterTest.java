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

import com.github.clboettcher.bonappetit.common.dto.event.EventDto;
import com.github.clboettcher.bonappetit.common.dto.menu.MenuDto;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;
import com.github.clboettcher.bonappetit.serverentities.event.Event;
import com.github.clboettcher.bonappetit.serverentities.menu.Menu;
import com.github.clboettcher.bonappetit.serverentities.staff.StaffListing;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link com.github.clboettcher.bonappetit.server.impl.converter.EventsConverter}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EventsConverterTest.Context.class)
public class EventsConverterTest {

    @Autowired
    private StaffListingsConverter staffListingsConverterMock;

    @Autowired
    private MenusConverter menusConverterMock;

    @Autowired
    private EventsConverter eventsConverter;

    private StaffListing inputStaffListing;
    private StaffListingDto expectedStaffListingDto;

    private Menu inputMenu;
    private MenuDto expectedMenuDto;

    @Before
    public void setUp() throws Exception {
        inputStaffListing = mock(StaffListing.class);
        inputMenu = mock(Menu.class);
        expectedStaffListingDto = mock(StaffListingDto.class);
        expectedMenuDto = mock(MenuDto.class);

        when(menusConverterMock.convertToDto(inputMenu)).thenReturn(expectedMenuDto);
        when(staffListingsConverterMock.convertToDto(inputStaffListing)).thenReturn(expectedStaffListingDto);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(menusConverterMock, staffListingsConverterMock);
    }

    @Test
    public void testConversion() throws Exception {
        Event input = Event.newBuilder()
                .id(1337)
                .title("Test Event Title")
                .menu(inputMenu)
                .staffListing(inputStaffListing)
                .build();

        EventDto expected = EventDto.newBuilder()
                .id(1337)
                .title("Test Event Title")
                .menuDto(expectedMenuDto)
                .staffListingDto(expectedStaffListingDto)
                .build();

        assertThat(eventsConverter.convertToDto(input), is(expected));
    }

    @Configuration
    static class Context {
        @Bean
        public EventsConverter eventsConverter(StaffListingsConverter staffListingsConverter, MenusConverter menusConverter) {
            return new EventsConverter(staffListingsConverter, menusConverter);
        }

        @Bean
        public StaffListingsConverter staffListingsConverter() {
            return mock(StaffListingsConverter.class);
        }

        @Bean
        public MenusConverter menusConverter() {
            return mock(MenusConverter.class);
        }
    }
}
