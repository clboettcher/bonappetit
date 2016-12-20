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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * Default impl of {@link PhysicalPrinter}.
 */
@Component
public class PhysicalPrinterImpl implements PhysicalPrinter {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PhysicalPrinterImpl.class);

    private final String printServiceName;

    @Autowired
    public PhysicalPrinterImpl(Environment environment) {
        this.printServiceName = environment.getRequiredProperty("printing.printService.name");
    }

    @Override
    public void print(String output) throws PrintException {
        DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        InputStream inputStream = new ByteArrayInputStream(
                output.getBytes(Charset.forName("UTF-8")));
        Doc doc = new SimpleDoc(inputStream, docFlavor, null);


        Optional<PrintService> printServiceOpt = lookupPrintService(docFlavor, printServiceName);

        if (!printServiceOpt.isPresent()) {
            throw new IllegalStateException(String.format("Could not find print service for name '%s'. " +
                    "Is a printer with this name connected?", this.printServiceName));
        } else {
            PrintService printService = printServiceOpt.get();

            LOGGER.info(String.format("Trying to print to service '%s'", printService.getName()));
            DocPrintJob job = printService.createPrintJob();
            job.print(doc, null);
        }
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
