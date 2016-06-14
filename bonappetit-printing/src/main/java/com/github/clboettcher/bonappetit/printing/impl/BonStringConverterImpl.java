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

import com.github.clboettcher.bonappetit.domain.menu.ItemType;
import com.github.clboettcher.bonappetit.printing.entity.Bon;
import com.github.clboettcher.bonappetit.printing.util.DateUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
     * Constructor setting the specified properties.
     *
     * @param controlCharProvider see {@link #controlCharProvider}.
     * @param specialCharEncoder  see {@link #specialCharEncoder}.
     */
    public BonStringConverterImpl(ControlCharProvider controlCharProvider, SpecialCharEncoder specialCharEncoder) {
        Preconditions.checkNotNull(controlCharProvider, "controlCharProvider");
        Preconditions.checkNotNull(specialCharEncoder, "specialCharEncoder");
        this.controlCharProvider = controlCharProvider;
        this.specialCharEncoder = specialCharEncoder;
    }

    @Override
    public String toString(Set<Bon> bons) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(bons), "bons empty");

        BonStringBuilder bonStringBuilder = BonStringBuilder.newInstance(controlCharProvider, specialCharEncoder);
        List<Bon> bonsInput = Lists.newArrayList(bons);
        sortByItemType(bonsInput);
        for (Bon bon : bonsInput) {
            appendBon(bon, bonStringBuilder);
        }

        return bonStringBuilder.build();
    }

    /**
     * Appends the given {@code bon} to the given {@code bonStringBuilder}.
     *
     * @param bon              The bon.
     * @param bonStringBuilder The builder to append the {@code bon} to.
     */
    private void appendBon(Bon bon, BonStringBuilder bonStringBuilder) {
        Optional<String> emphOptionsOpt = sortAndJoin(bon.getEmphasisedOptions());
        bonStringBuilder.appendLine(String.format("Kunde: %s", bon.getDeliverTo()), BonStringBuilder.Align.CENTER)
                .heading(StringUtils.trim(String.format("%s %s",
                        bon.getItemName(),
                        emphOptionsOpt.or(""))))
                .appendLineFeed();

        // Append separate line for default options only if present
        Optional<String> defaultOptionsOpt = sortAndJoin(bon.getDefaultOptions());
        if (defaultOptionsOpt.isPresent()) {
            bonStringBuilder.appendLine(defaultOptionsOpt.get());
        }

        // Append a separate line for the note only if present
        if (StringUtils.isNotBlank(bon.getNote())) {
            bonStringBuilder.appendLine(String.format("Bemerkung: %s", bon.getNote()));
        }

        // Separate staff member + order time from the order to enhance readability
        bonStringBuilder.appendLineFeed();

        // Staff member name + order time
        bonStringBuilder.appendLine(String.format("Bedienung: %s, %s",
                bon.getStaffMemberName(),
                DateUtils.formatDayMonthTimeWithSeconds(bon.getOrderTime())))
                .appendLineFeed()
                .appendLineFeed()
                .appendLineFeed()
                .appendPartialCut();
    }

    /**
     * @param strings The strings, may be null or empty.
     * @return A string created from joining the given {@code strings} after sorting them alphabetically.
     */
    private Optional<String> sortAndJoin(Set<String> strings) {
        if (CollectionUtils.isEmpty(strings)) {
            return Optional.absent();
        }

        final List<String> asList = Lists.newArrayList(strings);
        sortAlphabetically(asList);
        return Optional.of(Joiner.on(" ")
                .skipNulls()
                .join(asList));
    }

    /**
     * @param bons The given {@code bons} sorted by {@link Bon#getItemType()}.
     */
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

    /**
     * @param strings The given {@code strings} sorted alphabetically, may be null or empty.
     */
    private void sortAlphabetically(List<String> strings) {
        if (CollectionUtils.isEmpty(strings)) {
            return;
        }

        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
    }
}
