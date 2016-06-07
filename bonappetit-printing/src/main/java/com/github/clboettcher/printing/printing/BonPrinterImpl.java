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
package com.github.clboettcher.printing.printing;

import com.github.clboettcher.bonappetit.domain.menu.ItemType;
import com.github.clboettcher.printing.entity.Bon;
import com.github.clboettcher.printing.util.DateUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class BonPrinterImpl implements BonPrinter {
    private ControlCharProvider controlCharProvider;

    public BonPrinterImpl(ControlCharProvider controlCharProvider) {
        this.controlCharProvider = controlCharProvider;
    }


    @Override
    public String toPrintableString(Set<Bon> bons) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(bons), "bons must not be null or empty");

        List<Bon> bonsInput = Lists.newArrayList(bons);
        // Sort bons based on item type
        sortByItemType(bonsInput);
        BonStringBuilder printData = BonStringBuilder.createBuilder(controlCharProvider);

        for (Bon bon : bonsInput) {
            final String emphasisedOptionsString;
            if (!CollectionUtils.isEmpty(bon.getEmphasisedOptions())) {
                final List<String> emphasisedOptions = Lists.newArrayList(bon.getEmphasisedOptions());
                sortAlphabetically(emphasisedOptions);
                emphasisedOptionsString = " " + Joiner.on(" ").join(emphasisedOptions);
            } else {
                emphasisedOptionsString = "";
            }

            printData.alignCenter(String.format("Kunde: %s", bon.getDeliverTo()))
                    .doubleWidthDoubleHeightAlignCenter(bon.getItemName() + emphasisedOptionsString)
                    .lineFeed();
            // Append options only if present
            if (!CollectionUtils.isEmpty(bon.getDefaultOptions())) {
                List<String> defaultOptions = Lists.newArrayList(bon.getDefaultOptions());
                sortAlphabetically(defaultOptions);
                printData.appendLine(Joiner.on(", ").join(defaultOptions));
            }

            // Append note only if present
            if (StringUtils.isNotBlank(bon.getNote())) {
                printData.appendLine(String.format("Bemerkung: %s", bon.getNote()));
            }

            // Separate staff member + order time from the order to enhance readability
            printData.newline();

            // Staff member name + order time
            printData.appendLine(String.format("Bedienung: %s, %s",
                    bon.getStaffMemberName(),
                    DateUtils.formatDayMonthTimeWithSeconds(bon.getOrderTime())))
                    .lineFeed()
                    .lineFeed()
                    .newline()
                    .partialCut();
        }

        return printData.build();
    }

    public String toPrintableStringOld(Set<Bon> bons) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(bons), "bons must not be null or empty");

        List<Bon> bonsInput = Lists.newArrayList(bons);
        // Sort bons based on item type
        sortByItemType(bonsInput);
//
        StringBuilder printData = new StringBuilder();
        printData.append(controlCharProvider.getInit());

        for (Bon bon : bonsInput) {
            final String emphasisedOptionsString;
            if (!CollectionUtils.isEmpty(bon.getEmphasisedOptions())) {
                final List<String> emphasisedOptions = Lists.newArrayList(bon.getEmphasisedOptions());
                sortAlphabetically(emphasisedOptions);
                emphasisedOptionsString = " " + Joiner.on(" ").join(emphasisedOptions);
            } else {
                emphasisedOptionsString = "";
            }

            printData.append(controlCharProvider.getAlignCenterString()).append("Kunde: ").append(bon.getDeliverTo()).append("\n")
                    .append(controlCharProvider.getDoubleWidthDoubleHeightString()).append(bon.getItemName()).append(emphasisedOptionsString).append("\n")
                    .append(controlCharProvider.getLineFeedChar())
                    .append(controlCharProvider.getAlignLeftString())
                    .append(controlCharProvider.getNormalWidthNormalHeightString());

            // Append options only if present
            if (!CollectionUtils.isEmpty(bon.getDefaultOptions())) {
                List<String> defaultOptions = Lists.newArrayList(bon.getDefaultOptions());
                sortAlphabetically(defaultOptions);
                printData.append(Joiner.on(", ").join(defaultOptions)).append("\n");
            }

            // Append note only if present
            if (StringUtils.isNotBlank(bon.getNote())) {
                printData.append("Bemerkung: ").append(bon.getNote()).append("\n");
            }

            // Separate staff member + order time from the order to enhance readability
            printData.append("\n");

            // Staff member name + order time
            printData.append("Bedienung: ").append(bon.getStaffMemberName()).append(", ").append(DateUtils.formatDayMonthTimeWithSeconds(bon.getOrderTime())).append("\n")
                    .append(controlCharProvider.getLineFeedChar())
                    .append(controlCharProvider.getLineFeedChar()).append("\n")
                    .append(controlCharProvider.getPartialCutString());
        }

        return controlCharProvider.encodeGermanChars(printData.toString());
    }

    private void sortByItemType(List<Bon> bons) {
        Collections.sort(bons, new Comparator<Bon>() {
            @Override
            public int compare(Bon lhs, Bon rhs) {
                ItemType lhsType = lhs.getItemType();
                ItemType rhsType = rhs.getItemType();

                return lhsType.name().compareTo(rhsType.name());
            }
        });
    }

    private void sortAlphabetically(List<String> orderOptions) {
        if (CollectionUtils.isEmpty(orderOptions)) {
            return;
        }

        Collections.sort(orderOptions, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
    }
}
