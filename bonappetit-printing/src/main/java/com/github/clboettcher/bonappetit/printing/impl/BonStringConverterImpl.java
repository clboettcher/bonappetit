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

import com.github.clboettcher.bonappetit.printing.config.ConfigProvider;
import com.github.clboettcher.bonappetit.printing.entity.Bon;
import com.github.clboettcher.bonappetit.printing.util.DateFormatter;
import com.github.clboettcher.bonappetit.server.menu.api.dto.common.ItemDtoType;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BonStringConverterImpl implements BonStringConverter {

    /**
     * The bean that provides control chars that cause the printer to perform certain actions.
     */
    private ControlCharProvider controlCharProvider;

    /**
     * The bean that encodes special chars in a certain char set.
     */
    private SpecialCharEncoder specialCharEncoder;

    /**
     * The bean that formattes dates according to a configured locale.
     */
    private DateFormatter dateFormatter;

    /**
     * Constructor setting the specified properties.
     *
     * @param controlCharProvider see {@link #controlCharProvider}.
     * @param specialCharEncoder  see {@link #specialCharEncoder}.
     * @param configProvider      The bean that provides the module config.
     */
    @Autowired
    public BonStringConverterImpl(ControlCharProvider controlCharProvider,
                                  SpecialCharEncoder specialCharEncoder,
                                  ConfigProvider configProvider) {
        Preconditions.checkNotNull(controlCharProvider, "controlCharProvider");
        Preconditions.checkNotNull(specialCharEncoder, "specialCharEncoder");
        Preconditions.checkNotNull(configProvider, "configProvider");
        this.controlCharProvider = controlCharProvider;
        this.specialCharEncoder = specialCharEncoder;
        this.dateFormatter = configProvider.getDateFormatter();
    }

    @Override
    public String toString(List<Bon> bons) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(bons), "bons empty");
        PhysicalPrinterStringBuilder physicalPrinterStringBuilder = PhysicalPrinterStringBuilder.newInstance(
                controlCharProvider,
                specialCharEncoder);
        sortByItemTypeAndTime(bons);
        for (Bon bon : bons) {
            appendBon(bon, physicalPrinterStringBuilder);
        }

        return physicalPrinterStringBuilder.build();
    }

    /**
     * Appends the given {@code bon} to the given {@code physicalPrinterStringBuilder}.
     *
     * @param bon                          The bon.
     * @param physicalPrinterStringBuilder The builder to append the {@code bon} to.
     */
    private void appendBon(Bon bon, PhysicalPrinterStringBuilder physicalPrinterStringBuilder) {
        Optional<String> emphOptionsOpt = sortAndJoin(bon.getEmphasisedOptions());

        String itemTitleLine = String.format("%s%s%s",
                bon.getItemTitle(),
                emphOptionsOpt.isPresent() ? " " + emphOptionsOpt.get() : "",
                StringUtils.isNotBlank(bon.getNote()) ? " " + bon.getNote() : "");

        physicalPrinterStringBuilder
                .appendLine(String.format("Kunde: %s",
                        bon.getDeliverTo()),
                        PhysicalPrinterStringBuilder.Align.CENTER)
                .heading(StringUtils.trim(itemTitleLine));

        // Append separate line for default options only if present
        Optional<String> defaultOptionsOpt = sortAndJoin(bon.getDefaultOptions(), ", ");
        if (defaultOptionsOpt.isPresent()) {
            physicalPrinterStringBuilder
                    .appendLineFeed()
                    .appendLine(defaultOptionsOpt.get());
        }

        // Separate staff member + order time from the order to enhance readability
        physicalPrinterStringBuilder.appendLineFeed();

        // Staff member name + order time
        physicalPrinterStringBuilder.appendLine(String.format("Bedienung: %s, %s",
                bon.getStaffMemberName(),
                dateFormatter.formatDayMonthHourMinuteShort(bon.getOrderTime()
                        .toDateTime(DateTimeZone.forID("Europe/Berlin")))))
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendPartialCut();
    }

    /**
     * @param strings The strings, may be null or empty.
     * @return A string created from joining the given {@code strings} with spaces
     * after sorting them alphabetically.
     */
    private Optional<String> sortAndJoin(List<String> strings) {
        return sortAndJoin(strings, " ");
    }

    /**
     * @param strings   The strings, may be null or empty.
     * @param separator The separator to write between elements.
     * @return A string created from joining the given {@code strings}
     * with the given {@code separator} after sorting them alphabetically.
     */
    private Optional<String> sortAndJoin(List<String> strings, String separator) {
        if (CollectionUtils.isEmpty(strings)) {
            return Optional.absent();
        }

        sortAlphabetically(strings);
        return Optional.of(Joiner.on(separator)
                .skipNulls()
                .join(strings));
    }

    /**
     * @param bons The given {@code bons} sorted by {@link Bon#getItemType()}, then by
     *             {@link Bon#getOrderTime()}.
     */
    private void sortByItemTypeAndTime(List<Bon> bons) {
        Collections.sort(bons, (lhs, rhs) -> {
            ItemDtoType lhsType = lhs.getItemType();
            ItemDtoType rhsType = rhs.getItemType();


            int typeCompare = lhsType.name().compareTo(rhsType.name());

            if (typeCompare == 0) {
                // Sort ascending by order time so the oldest order is
                // printed last.
                return rhs.getOrderTime().compareTo(lhs.getOrderTime());
            } else {
                return typeCompare;
            }
        });
    }

    /**
     * @param strings The given {@code strings} sorted alphabetically, may be null or empty.
     */
    private void sortAlphabetically(List<String> strings) {
        if (CollectionUtils.isEmpty(strings)) {
            return;
        }

        Collections.sort(strings, String::compareTo);
    }
}
