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

import com.github.clboettcher.bonappetit.printing.api.PrintManager;
import com.github.clboettcher.bonappetit.printing.conversion.BonConverter;
import com.github.clboettcher.bonappetit.printing.entity.Bon;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.SummaryDto;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.PrintException;
import java.util.List;

/**
 * Default impl of {@link PrintManager}.
 */
@Component
public class PrintManagerImpl implements PrintManager {

    /**
     * Bean that converts {@link ItemOrderDto}s to {@link Bon}s.
     */
    private BonConverter bonConverter;

    /**
     * Bean that converts {@link Bon}s to string.
     */
    private BonStringConverter bonStringConverter;

    /**
     * Converts summaries into printable strings.
     */
    private SummaryStringConverter summaryStringConverter;

    /**
     * Bean that wraps talking to the physical printer.
     */
    private PhysicalPrinter physicalPrinter;

    /**
     * Constructor setting the specified properties.
     *
     * @param bonConverter           see {@link #bonConverter}.
     * @param bonStringConverter     see {@link #bonStringConverter}.
     * @param summaryStringConverter see {@link #summaryStringConverter}.
     * @param physicalPrinter        see {@link #physicalPrinter}.
     */
    @Autowired
    public PrintManagerImpl(BonConverter bonConverter,
                            BonStringConverter bonStringConverter,
                            SummaryStringConverter summaryStringConverter,
                            PhysicalPrinter physicalPrinter) {
        this.bonConverter = bonConverter;
        this.bonStringConverter = bonStringConverter;
        this.summaryStringConverter = summaryStringConverter;
        this.physicalPrinter = physicalPrinter;
    }

    @Override
    public void print(List<ItemOrderDto> orders) throws PrintException {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(orders), "orders empty");
        List<Bon> bons = bonConverter.toBons(orders);
        String output = bonStringConverter.toString(bons);
        physicalPrinter.print(output);
    }

    @Override
    public void print(SummaryDto summary) throws PrintException {
        Preconditions.checkNotNull(summary, "summary");
        String output = summaryStringConverter.toString(summary);
        physicalPrinter.print(output);
    }
}

