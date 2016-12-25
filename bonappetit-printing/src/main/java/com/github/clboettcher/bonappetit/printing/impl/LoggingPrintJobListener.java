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
