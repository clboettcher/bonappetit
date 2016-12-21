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

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PhysicalPrinterInspectionTest.Context.class)
// This tests should not be executed in a normal build
@Ignore
public class PhysicalPrinterInspectionTest {

    @Autowired
    private PhysicalPrinter physicalPrinter;

    @Test
    public void testAlignLeft() throws Exception {
        String string = initBuilder()
                .appendLine("Align left test")
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendPartialCut()
                .build();
        physicalPrinter.print(string);
    }

    @Test
    public void testAlignCenter() throws Exception {
        String string = initBuilder()
                .appendLine("Align center test", PhysicalPrinterStringBuilder.Align.CENTER)
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendPartialCut()
                .build();
        physicalPrinter.print(string);
    }

    @Test
    public void testHeading() throws Exception {
        String string = initBuilder()
                .heading("Heading")
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendPartialCut()
                .build();
        physicalPrinter.print(string);
    }

    @Test
    public void testHeadingThenAlignLeft() throws Exception {
        String string = initBuilder()
                .heading("Heading")
                .appendLineFeed()
                .appendLineFeed()
                .appendLine("Align left test")
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendPartialCut()
                .build();
        physicalPrinter.print(string);
    }

    private PhysicalPrinterStringBuilder initBuilder() {
        return PhysicalPrinterStringBuilder.newInstance(
                new ControlCharProviderCITIZENCTS310IIImpl(),
                new SpecialCharEncoderCITIZENCT310IIGermanImpl());
    }

    @Configuration
    @PropertySource({
            "classpath:config/printing.properties"
    })
    static class Context {

        /**
         * The spring environment providing access to configuration properties.
         */
        @Autowired
        private Environment env;

        @Bean
        public PhysicalPrinter physicalPrinter() {
            return new PhysicalPrinterImpl(env);
        }

    }
}
