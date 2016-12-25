package com.github.clboettcher.bonappetit.printing.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.print.PrintException;

/**
 * Implementation of {@link PhysicalPrinter} to use for development.
 * <p>
 * The implementation just logs what would be printed.
 */
@Component
@Profile("mockPrinter")
public class PhysicalPrinterLoggerImpl implements PhysicalPrinter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhysicalPrinterLoggerImpl.class);

    public PhysicalPrinterLoggerImpl() {
        LOGGER.info(String.format("Using %s as printer implementation.", this.getClass().getSimpleName()));
    }

    @Override
    public void print(String output) throws PrintException {
        LOGGER.info(String.format("Printing output: %s", output));
    }
}
