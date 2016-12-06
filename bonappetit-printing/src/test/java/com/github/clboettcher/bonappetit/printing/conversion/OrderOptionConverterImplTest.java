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
import com.github.clboettcher.bonappetit.printing.entity.OptionOrderStrings;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderDto;
import com.google.common.collect.Lists;
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
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OrderOptionConverterImplTest.Context.class)
public class OrderOptionConverterImplTest extends AbstractConverterTest {

    @Autowired
    private OptionOrderStringsConverter converter;

    /**
     * Tests that all not not printed order options are filtered.
     *
     * @throws Exception
     */
    @Test
    public void testFilterNotPrintedOrderOptions() throws Exception {
        List<OptionOrderDto> orderOptions = Lists.newArrayList();

        // Some options are not printed no matter what strategy is set
        // Unchecked checkbox option
        orderOptions.add(orderForCheckboxOption(1L, false, "Checkbox-Option", BigDecimal.ONE));
        // Integer option with value == 0
        orderOptions.add(orderForValueOption(0, "Integer-Option", BigDecimal.ONE));

        // Orders for options that are not printed via config
        orderOptions.add(orderForCheckboxOption(1L, true, "Checkbox-Option (not printed)", BigDecimal.ONE));
        orderOptions.add(orderForValueOption(1337, "Integer-Option (not printed)", BigDecimal.ONE));
        orderOptions.add(orderForRadioOption(1L, "Radio-Item (not printed)", BigDecimal.ONE));

        final OptionOrderStrings optionStrings = converter.convert(orderOptions);
        assertThat(optionStrings.getEmphasisedOptions(), empty());
        assertThat(optionStrings.getDefaultOptions(), empty());
    }

    @Test
    public void testEmphasisedOptions() throws Exception {
        List<OptionOrderDto> orderOptions = Lists.newArrayList();

        orderOptions.add(orderForCheckboxOption(1L, true, "Checkbox-Option (emphasised)", BigDecimal.ONE));
        orderOptions.add(orderForValueOption(1337, "Integer-Option (emphasised)", BigDecimal.ONE));
        orderOptions.add(orderForRadioOption(1L, "Radio-Item (emphasised)", BigDecimal.ONE));

        final OptionOrderStrings optionStrings = converter.convert(orderOptions);
        assertThat(optionStrings.getEmphasisedOptions().size(), is(3));
        assertThat(optionStrings.getEmphasisedOptions(), containsInAnyOrder(
                "Checkbox-Option (emphasised)", "Integer-Option (emphasised) (1337x)", "Radio-Item (emphasised)"));
        assertThat(optionStrings.getDefaultOptions(), empty());
    }

    @Test
    public void testDefaultOptions() throws Exception {
        List<OptionOrderDto> orderOptions = Lists.newArrayList();

        orderOptions.add(orderForCheckboxOption(1L, true, "Checkbox-Option (default)", BigDecimal.ONE));
        orderOptions.add(orderForValueOption(1337, "Integer-Option (default)", BigDecimal.ONE));
        orderOptions.add(orderForRadioOption(1L, "Radio-Item (default)", BigDecimal.ONE));

        final OptionOrderStrings optionStrings = converter.convert(orderOptions);
        assertThat(optionStrings.getEmphasisedOptions(), empty());
        assertThat(optionStrings.getDefaultOptions().size(), is(3));
        assertThat(optionStrings.getDefaultOptions(), containsInAnyOrder(
                "Checkbox-Option (default)",
                "Integer-Option (default) (1337x)",
                "Radio-Item (default)"));
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
    }
}