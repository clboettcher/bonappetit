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
package com.github.clboettcher.bonappetit.printing.impl;

import com.github.clboettcher.bonappetit.common.BonAppetitResourceUtils;
import com.github.clboettcher.bonappetit.printing.entity.Bon;
import com.github.clboettcher.bonappetit.server.menu.api.dto.common.ItemDtoType;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link BonStringConverterImpl}.
 */
public class BonStringConverterImplTests {

    private BonStringConverter converter;
    private DateTime orderTime;

    @Before
    public void setUp() throws Exception {
        this.converter = new BonStringConverterImpl(
                new ControlCharProviderConsoleTestImpl(),
                new SpecialCharEncoderCITIZENCT310IIGermanImpl()
        );
        this.orderTime = DateTime.parse("2016-12-06T21:33:00.000Z");
    }

    @Test
    public void testMinimalBon() throws Exception {
        Bon input = minimalBon().build();
        String actual = converter.toString(Collections.singletonList(input));
        String expected = BonAppetitResourceUtils.readFileContentAsString("testdata/bon-minimal.txt");
        assertThat(actual, is(expected));
    }

    // TODO add missing test cases.

    @Test
    public void testBonWithNote() throws Exception {
        Bon input = minimalBon("Burger", ItemDtoType.DRINK_NON_ALCOHOLIC)
                .note("ohne Zwiebeln")
                .build();
        String actual = converter.toString(Collections.singletonList(input));
        String expected = BonAppetitResourceUtils.readFileContentAsString("testdata/bon-with-note.txt");
        assertThat(actual, is(expected));
    }


    @Test
    public void testBonsAreSortedByTypeThenByOrderTime() throws Exception {
        List<Bon> input = Lists.newArrayList(
                minimalBon("Bon_6", ItemDtoType.FOOD)
                        .orderTime(DateTime.parse("2016-12-06T20:00Z"))
                        .build(),
                minimalBon("Bon_4", ItemDtoType.DRINK_NON_ALCOHOLIC)
                        .orderTime(DateTime.parse("2016-12-06T21:00Z"))
                        .build(),
                minimalBon("Bon_5", ItemDtoType.FOOD)
                        .orderTime(DateTime.parse("2016-12-06T20:01Z"))
                        .build(),
                minimalBon("Bon_3", ItemDtoType.DRINK_NON_ALCOHOLIC)
                        .orderTime(DateTime.parse("2016-12-06T21:01Z"))
                        .build(),
                minimalBon("Bon_1", ItemDtoType.DRINK_ALCOHOLIC)
                        .orderTime(DateTime.parse("2016-12-06T19:00Z"))
                        .build(),
                minimalBon("Bon_2", ItemDtoType.DRINK_NON_ALCOHOLIC)
                        .orderTime(DateTime.parse("2016-12-06T21:02Z"))
                        .build()
        );

        String actual = converter.toString(input);
        String expected = BonAppetitResourceUtils.readFileContentAsString("testdata/bon-sorted-by-type-and-time.txt");
        assertThat(actual, is(expected));
    }

    @Test
    @Ignore
    public void testOptionsAreSortedByTitle() throws Exception {
        Assert.fail();
    }

    @Test
    @Ignore
    public void testEmphasisedOptions() throws Exception {
        Assert.fail();
    }

    @Test
    @Ignore
    public void testDefaultOptions() throws Exception {
        Assert.fail();
    }

    @Test
    @Ignore
    public void testEmphasisedAndDefaultOptions() throws Exception {
        Assert.fail();
    }

    private Bon.BonBuilder minimalBon() {
        return minimalBon("Mineralwasser", ItemDtoType.DRINK_NON_ALCOHOLIC);
    }

    private Bon.BonBuilder minimalBon(String itemTitle, ItemDtoType itemType) {
        return Bon.builder()
                .itemTitle(itemTitle)
                .deliverTo("Tisch 12")
                .staffMemberName("John Smith")
                .itemType(itemType)
                .orderTime(orderTime);
    }
}
