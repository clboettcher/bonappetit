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
