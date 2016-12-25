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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.print.*;
import javax.print.attribute.Attribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * Default impl of {@link PhysicalPrinter}.
 */
@Component
@Profile("!mockPrinter")
public class PhysicalPrinterImpl implements PhysicalPrinter {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PhysicalPrinterImpl.class);

    private final DocFlavor docFlavor;
    private final PrintService printService;

    @Autowired
    public PhysicalPrinterImpl(Environment environment) {
        String printServiceName = environment.getRequiredProperty("printing.printService.name");
        this.docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Optional<PrintService> printServiceOpt = lookupPrintService(docFlavor, printServiceName);
        this.printService = printServiceOpt.orElseThrow(() ->
                new IllegalStateException(String.format("Could not find print service for name '%s'. " +
                        "Is a printer with this name connected?", printServiceName))
        );
        PrintServiceAttributeSet attributes = this.printService.getAttributes();
        for (Attribute attribute : attributes.toArray()) {
            LOGGER.info(String.format("Printer has attribute '%s' with value '%s' in category '%s'",
                    attribute.getName(),
                    attribute.toString(),
                    attribute.getCategory()));
        }
        PrinterIsAcceptingJobs isAcceptingJobsAttr = printService.getAttribute(PrinterIsAcceptingJobs.class);
        if (PrinterIsAcceptingJobs.NOT_ACCEPTING_JOBS.equals(isAcceptingJobsAttr)) {
            throw new IllegalStateException(String.format("Printer '%s' is not accepting jobs. " +
                    "Make sure the printer is connected and running.", printServiceName));
        }
    }

    @Override
    public void print(String output) throws PrintException {
        InputStream inputStream = new ByteArrayInputStream(
                output.getBytes(Charset.forName("UTF-8")));
        Doc doc = new SimpleDoc(inputStream, this.docFlavor, null);

        LOGGER.info(String.format("Trying to print to service '%s'", this.printService.getName()));
        DocPrintJob job = printService.createPrintJob();
        job.addPrintJobListener(new LoggingPrintJobListener());
        job.print(doc, null);
    }

    private Optional<PrintService> lookupPrintService(DocFlavor docFlavor, String printServiceName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(docFlavor, null);
        for (PrintService printService : printServices) {
            LOGGER.info(String.format("Looking for print service '%s'. Currently checking '%s' ... ",
                    printServiceName,
                    printService.getName()));
            if (printServiceName.equals(printService.getName())) {
                return Optional.of(printService);
            }
        }

        return Optional.empty();
    }
}
