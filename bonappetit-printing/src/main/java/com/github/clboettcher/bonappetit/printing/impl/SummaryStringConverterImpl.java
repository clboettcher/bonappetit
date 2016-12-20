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

import com.github.clboettcher.bonappetit.printing.util.DateFormatter;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderSummaryDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.SummaryDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.SummaryEntryDto;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class SummaryStringConverterImpl implements SummaryStringConverter {

    /**
     * The bean that provides control chars that cause the printer to perform certain actions.
     */
    private ControlCharProvider controlCharProvider;

    /**
     * The bean that encodes special chars in a certain char set.
     */
    private SpecialCharEncoder specialCharEncoder;

    private Comparator<SummaryEntryDto> COUNT_COMPARATOR = (o1, o2) -> o1.getCount().compareTo(o2.getCount());

    /**
     * The format to use for prices.
     */
    private DecimalFormat priceFormat = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.GERMANY));

    /**
     * The bean that formattes dates according to a configured locale.
     */
    private DateFormatter dateFormatter;

    /**
     * Constructor setting the specified properties.
     *
     * @param controlCharProvider see {@link #controlCharProvider}.
     * @param specialCharEncoder  see {@link #specialCharEncoder}.
     * @param dateFormatter       see {@link #dateFormatter}.
     */
    @Autowired
    public SummaryStringConverterImpl(ControlCharProvider controlCharProvider, SpecialCharEncoder specialCharEncoder, DateFormatter dateFormatter) {
        Preconditions.checkNotNull(controlCharProvider, "controlCharProvider");
        Preconditions.checkNotNull(specialCharEncoder, "specialCharEncoder");
        Preconditions.checkNotNull(dateFormatter, "dateFormatter");
        this.controlCharProvider = controlCharProvider;
        this.specialCharEncoder = specialCharEncoder;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public String toString(SummaryDto summary) {
        PhysicalPrinterStringBuilder builder = PhysicalPrinterStringBuilder.newInstance(
                controlCharProvider,
                specialCharEncoder);

        List<SummaryEntryDto> orderSummaries = summary.getOrderSummaries();
        builder
                .heading("Zusammenfassung")
                .appendLineFeed()
                .appendLine(String.format("Erste Bestellung:  %s", summary.getOldestOrderTime() != null ?
                        dateFormatter.formatDayMonthYearTime(summary.getOldestOrderTime()) :
                        "--"))
                .appendLine(String.format("Letzte Bestellung: %s", summary.getNewestOrderTime() != null ?
                        dateFormatter.formatDayMonthYearTime(summary.getNewestOrderTime()) :
                        "--"))
                .appendLine(String.format("Bestellungen: %d", orderSummaries.stream()
                        .mapToLong(SummaryEntryDto::getCount)
                        .sum()
                ))
                .appendLineFeed();

        Collections.sort(orderSummaries, COUNT_COMPARATOR);
        for (SummaryEntryDto summaryEntryDto : orderSummaries) {
            this.appendSummaryEntry(builder, summaryEntryDto);
        }

        builder.appendLineFeed()
                .appendLine(String.format("Einnahmen laut BonAppetit: %s EUR", priceFormat.format(summary.getTotalPrice())))
                .appendLineFeed()
                .appendLine("Tatsaechliches Bargeld (ohne Wechselgeld):")
                .appendLineFeed()
                .appendLine("_____________________________ EUR")
                .appendLineFeed()
                .appendLine("Umbuchungsbelege:")
                .appendLineFeed()
                .appendLine("_____________________________ EUR")
                .appendLineFeed()
                .appendLine("Summe:")
                .appendLineFeed()
                .appendLine("_____________________________ EUR")
                .appendLineFeed()
                .appendLine("Unterschrift KuechenmitarbeiterIn:")
                .appendLineFeed()
                .appendLine("_________________________________")
                .appendLineFeed()
                .appendLine("Unterschrift ServicemitarbeiterIn:")
                .appendLineFeed()
                .appendLine("_________________________________")
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendPartialCut()
        ;

        return builder.build();
    }

    private void appendSummaryEntry(PhysicalPrinterStringBuilder builder, SummaryEntryDto summaryEntryDto) {

        String optionsString;
        List<OptionOrderSummaryDto> optionOrders = summaryEntryDto.getOrderSummary().getOptionOrders();
        if (CollectionUtils.isNotEmpty(optionOrders)) {
            List<String> titles = optionOrders.stream()
                    .map(OptionOrderSummaryDto::getOptionTitle)
                    .collect(Collectors.toList());
            optionsString = " (" + Joiner.on(", ").join(titles) + ")";
        } else {
            optionsString = "";
        }

        builder.appendLine(String.format("%dx %s%s je %s EUR",
                summaryEntryDto.getCount(),
                summaryEntryDto.getOrderSummary().getItemTitle(),
                optionsString,
                priceFormat.format(summaryEntryDto.getOrderSummary().getTotalPrice())
        ));
    }
}
