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
package com.github.clboettcher.bonappetit.printing.conversion;

import com.github.clboettcher.bonappetit.domain.menu.CheckboxOption;
import com.github.clboettcher.bonappetit.domain.menu.RadioItem;
import com.github.clboettcher.bonappetit.domain.order.CheckboxOptionOrder;
import com.github.clboettcher.bonappetit.domain.order.OptionOrder;
import com.github.clboettcher.bonappetit.domain.order.RadioOptionOrder;
import com.github.clboettcher.bonappetit.domain.order.ValueOptionOrder;
import com.github.clboettcher.bonappetit.printing.config.ConfigProvider;
import com.github.clboettcher.bonappetit.printing.entity.OrderOptionStrings;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * Default impl of {@link OptionOrderConverter}.
 */
public class OptionOrderConverterImpl implements OptionOrderConverter {

    /**
     * The titles of the options which should be printed in an emphasised way.
     */
    private Set<String> emphasisedOptionTitles;

    /**
     * The titles of the options which should not be printed at all.
     */
    private Set<String> notPrintedOptionTitles;

    /**
     * Constructor setting the specified properties.
     *
     * @param configProvider The bean providing the modules config.
     */
    public OptionOrderConverterImpl(ConfigProvider configProvider) {
        Preconditions.checkNotNull(configProvider, "configProvider");
        emphasisedOptionTitles = configProvider.getEmphasisedOptionTitles();
        notPrintedOptionTitles = configProvider.getNotPrintedOptionTitles();
    }

    @Override
    public OrderOptionStrings convert(Set<OptionOrder> optionOrders) {
        if (CollectionUtils.isEmpty(optionOrders)) {
            return new OrderOptionStrings();
        }

        Set<OptionOrder> optionOrdersInput = Sets.newHashSet(optionOrders);
        Set<String> emphasisedOptions = Sets.newHashSet();
        Set<String> defaultOptions = Sets.newHashSet();

        for (OptionOrder optionOrder : optionOrdersInput) {
            String title = StringUtils.trim(StringUtils.lowerCase(extractTitle(optionOrder)));
            String printed = asPrintedString(optionOrder);
            // Add the string according to the configuration.
            if (emphasisedOptionTitles.contains(title)) {
                emphasisedOptions.add(printed);
            } else if (!notPrintedOptionTitles.contains(title)) {
                // not emphasised and _not_ not printed means printed normally
                defaultOptions.add(printed);
            }
        }

        return OrderOptionStrings.builder()
                .emphasisedOptions(emphasisedOptions)
                .defaultOptions(defaultOptions)
                .build();
    }

    /**
     * Extracts the title of the the given option.
     *
     * @param optionOrder The option.
     * @return The extracted title.
     */
    private String extractTitle(OptionOrder optionOrder) {
        if (optionOrder instanceof RadioOptionOrder) {
            return ((RadioOptionOrder) optionOrder).getSelectedItem().getTitle();
        } else if (optionOrder instanceof ValueOptionOrder) {
            return ((ValueOptionOrder) optionOrder).getOption().getTitle();
        } else if (optionOrder instanceof CheckboxOptionOrder) {
            return ((CheckboxOptionOrder) optionOrder).getOption().getTitle();
        } else {
            throw new IllegalArgumentException(String.format("Unsupported subtype of %s: %s",
                    OptionOrder.class.getName(),
                    optionOrder.getClass().getName()
            ));
        }
    }

    /**
     * Converts the given {@link OptionOrder} to a printable string.
     *
     * @param optionOrder The option order to convert.
     * @return The printable string.
     */
    private String asPrintedString(OptionOrder optionOrder) {
        String printedTitle;
        if (optionOrder instanceof RadioOptionOrder) {
            RadioOptionOrder radioOrderOption = (RadioOptionOrder) optionOrder;
            final RadioItem selectedItem = radioOrderOption.getSelectedItem();

            printedTitle = selectedItem.getTitle();
        } else if (optionOrder instanceof CheckboxOptionOrder) {
            CheckboxOptionOrder checkboxOptionOrder = (CheckboxOptionOrder) optionOrder;
            final CheckboxOption checkboxOption = checkboxOptionOrder.getOption();

            printedTitle = checkboxOption.getTitle();
        } else if (optionOrder instanceof ValueOptionOrder) {
            ValueOptionOrder valueOptionOrder = (ValueOptionOrder) optionOrder;
            // Value options with value == 0 are never printed.
            printedTitle = String.format("%s (%sx)",
                    valueOptionOrder.getOption().getTitle(),
                    valueOptionOrder.getValue());
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype of %s: %s",
                    OptionOrder.class.getName(),
                    optionOrder.getClass().getName()
            ));
        }
        return printedTitle;
    }


}
