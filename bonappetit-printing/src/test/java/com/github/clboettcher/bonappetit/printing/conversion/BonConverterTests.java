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
package com.github.clboettcher.bonappetit.printing.conversion;

import com.github.clboettcher.bonappetit.printing.AbstractConverterTest;
import com.github.clboettcher.bonappetit.printing.config.ConfigProvider;
import com.github.clboettcher.bonappetit.printing.config.ConfigProviderImpl;
import com.github.clboettcher.bonappetit.printing.entity.Bon;
import com.github.clboettcher.bonappetit.server.menu.api.dto.common.ItemDtoType;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.*;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BonConverterTests.Context.class)
public class BonConverterTests extends AbstractConverterTest {

    @Autowired
    private BonConverterImpl converter;

    /**
     * Item order for item without options
     *
     * @throws Exception
     */
    @Test
    public void testConversionWithoutOptions() throws Exception {
        // Setup
        DateTime orderTime = DateTime.now(DateTimeZone.UTC);
        ItemOrderDto input = ItemOrderDto.builder()
                .itemId(1L)
                .itemPrice(new BigDecimal("9.99"))
                .itemType(ItemDtoType.FOOD)
                .itemTitle("Testitem")
                .customer(FreeTextCustomerDto.builder().value("Testcustomer").build())
                .note("Note")
                .orderTime(orderTime)
                .staffMemberFirstName("John")
                .staffMemberLastName("Cena")
                .build();

        // Test
        List<Bon> bonList = converter.toBons(Lists.newArrayList(input));

        // Verify
        assertThat(bonList.size(), is(1));
        Bon b = bonList.toArray(new Bon[bonList.size()])[0];
        assertThat(b.getDeliverTo(), is("Testcustomer"));
        assertThat(b.getItemTitle(), is("Testitem"));
        assertThat(b.getItemType(), is(ItemDtoType.FOOD));
        assertThat(b.getNote(), is("Note"));
        assertThat(b.getOrderTime(), is(orderTime));
        assertThat(b.getStaffMemberName(), is("John Cena"));
        assertThat(b.getEmphasisedOptions(), is(Collections.<String>emptyList()));
        assertThat(b.getDefaultOptions(), is(Collections.<String>emptyList()));
    }

    @Test
    public void testConversionWithOptions() throws Exception {
        // Setup
        List<OptionOrderDto> optionOrders = Lists.newArrayList();
        optionOrders.add(orderForValueOption(1337, "Integer-Option (emphasised)", BigDecimal.ONE));
        optionOrders.add(orderForRadioOption(1L, "Radio-Item (default)", BigDecimal.ONE));

        ItemOrderDto o = ItemOrderDto.builder()
                .itemId(1L)
                .itemTitle("Item")
                .optionOrders(optionOrders)
                .build();

        // Test
        List<Bon> bons = converter.toBons(Lists.newArrayList(o));

        // Verify
        assertThat(bons.size(), is(1));
        Bon b = bons.get(0);
        assertThat(b.getEmphasisedOptions(), is(Collections.singletonList("Integer-Option (emphasised) (1337x)")));
        assertThat(b.getDefaultOptions(), is(Collections.singletonList("Radio-Item (default)")));
    }

    @Test
    public void testOneOneHalfConversion() throws Exception {
        ItemOrderDto oneOneHalf = ItemOrderDto.builder()
                .itemId(1L)
                .itemTitle("1+1/2")
                .build();

        List<Bon> actual = converter.toBons(Lists.newArrayList(oneOneHalf));
        assertThat(actual.size(), is(2));
        assertThat(actual.get(0).getItemTitle(), is("1+1/2 (Getränke-Bon)"));
        assertThat(actual.get(1).getItemTitle(), is("1+1/2 (Speise-Bon)"));
    }

    @Test
    public void testDeliverToFreeText() throws Exception {
        String actual = converter.getDeliverTo(FreeTextCustomerDto.builder().value("freeTextValue").build());
        assertThat(actual, is("freeTextValue"));
    }

    @Test
    public void testDeliverToTable() throws Exception {
        String actual = converter.getDeliverTo(TableCustomerDto.builder().tableNumber(12L).build());
        assertThat(actual, is("Tisch 12"));
    }

    @Test
    public void testDeliverToTableWithDisplayValue() throws Exception {
        String actual = converter.getDeliverTo(TableCustomerDto.builder()
                .tableNumber(20L)
                .displayValue("Bar")
                .build());
        assertThat(actual, is("Bar"));
    }

    @Test
    public void testDeliverToStaffMember() throws Exception {
        String actual = converter.getDeliverTo(StaffMemberCustomerDto.builder()
                .staffMemberFirstName("John")
                .staffMemberLastName("Smith")
                .build());
        assertThat(actual, is("John Smith (MA)"));
    }

    @Configuration
    @PropertySource({
            "classpath:/config/printing-test.properties"
    })
    static class Context {

        /**
         * The spring environment providing access to configuration properties.
         */
        @Autowired
        private Environment env;

        @Bean
        public ConfigProvider configProvider() {
            return new ConfigProviderImpl(env);
        }

        @Bean
        public OptionOrderStringsConverter optionOrderStringsConverter(ConfigProvider configProvider) {
            return new OptionOrderStringsConverterImpl(configProvider);
        }

        @Bean
        public BonConverterImpl bonConverter() {
            return new BonConverterImpl();
        }
    }
}
