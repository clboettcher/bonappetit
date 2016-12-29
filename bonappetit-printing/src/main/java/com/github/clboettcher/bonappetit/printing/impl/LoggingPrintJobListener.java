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

import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;

public class LoggingPrintJobListener implements PrintJobListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingPrintJobListener.class);

    @Override
    public void printDataTransferCompleted(PrintJobEvent pje) {
        LOGGER.info(String.format("Print data transfer completed. Event: %s", pje));
    }

    @Override
    public void printJobCompleted(PrintJobEvent pje) {
        LOGGER.info(String.format("Print job completed. Event: %s", pje));
    }

    @Override
    public void printJobFailed(PrintJobEvent pje) {
        LOGGER.info(String.format("Print job failed. Event: %s", pje));

    }

    @Override
    public void printJobCanceled(PrintJobEvent pje) {
        LOGGER.info(String.format("Print job canceled. Event: %s", pje));
    }

    @Override
    public void printJobNoMoreEvents(PrintJobEvent pje) {
        LOGGER.info(String.format("Print job no more events. Event: %s", pje));
    }

    @Override
    public void printJobRequiresAttention(PrintJobEvent pje) {
        LOGGER.info(String.format("Print job requires attention. Event: %s", pje));
    }
}
