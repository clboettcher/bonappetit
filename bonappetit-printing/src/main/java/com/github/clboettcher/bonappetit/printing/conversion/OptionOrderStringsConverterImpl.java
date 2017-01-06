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

import com.github.clboettcher.bonappetit.printing.config.ConfigProvider;
import com.github.clboettcher.bonappetit.printing.entity.OptionOrderStrings;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.CheckboxOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.RadioOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ValueOptionOrderDto;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Default impl of {@link OptionOrderStringsConverter}.
 */
@Component
public class OptionOrderStringsConverterImpl implements OptionOrderStringsConverter {

    /**
     * The titles of the options which should be printed in an emphasised way.
     */
    private List<String> emphasisedOptionTitles;

    /**
     * The titles of the options which should not be printed at all.
     */
    private List<String> notPrintedOptionTitles;

    /**
     * Constructor setting the specified properties.
     *
     * @param configProvider The bean providing the modules config.
     */
    @Autowired
    public OptionOrderStringsConverterImpl(ConfigProvider configProvider) {
        Preconditions.checkNotNull(configProvider, "configProvider");
        emphasisedOptionTitles = configProvider.getEmphasisedOptionTitles();
        notPrintedOptionTitles = configProvider.getNotPrintedOptionTitles();
    }

    @Override
    public OptionOrderStrings convert(List<OptionOrderDto> optionOrders) {
        if (CollectionUtils.isEmpty(optionOrders)) {
            return new OptionOrderStrings();
        }

        List<String> emphasisedOptions = new ArrayList<>();
        List<String> defaultOptions = new ArrayList<>();

        for (OptionOrderDto optionOrder : optionOrders) {
            if (!isPrinted(optionOrder)) {
                continue;
            }
            String printed = asPrintedString(optionOrder);
            // Add the string according to the configuration.
            String title = StringUtils.trim(optionOrder.getOptionTitle());
            if (emphasisedOptionTitles.contains(title.toLowerCase())) {
                emphasisedOptions.add(printed);
            } else {
                defaultOptions.add(printed);
            }
        }

        return OptionOrderStrings.builder()
                .emphasisedOptions(emphasisedOptions)
                .defaultOptions(defaultOptions)
                .build();
    }

    private boolean isPrinted(OptionOrderDto optionOrder) {
        String title = StringUtils.trim(optionOrder.getOptionTitle());
        if (notPrintedOptionTitles.contains(title.toLowerCase())) {
            return false;
        }

        if (optionOrder instanceof RadioOptionOrderDto) {
            return true;
        } else if (optionOrder instanceof CheckboxOptionOrderDto) {
            CheckboxOptionOrderDto checkboxOptionOrderDto = (CheckboxOptionOrderDto) optionOrder;
            return checkboxOptionOrderDto.getChecked();
        } else if (optionOrder instanceof ValueOptionOrderDto) {
            ValueOptionOrderDto valueOptionOrderDto = (ValueOptionOrderDto) optionOrder;
            return valueOptionOrderDto.getValue() != 0;
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype of %s: %s",
                    OptionOrderDto.class.getName(),
                    optionOrder.getClass().getName()
            ));
        }
    }

    /**
     * Converts the given {@link OptionOrderDto} to a printable string.
     *
     * @param optionOrder The option order to convert.
     * @return The printable string.
     */
    private String asPrintedString(OptionOrderDto optionOrder) {
        if (optionOrder instanceof RadioOptionOrderDto) {
            RadioOptionOrderDto radioOrderOption = (RadioOptionOrderDto) optionOrder;
            return radioOrderOption.getOptionTitle();
        } else if (optionOrder instanceof CheckboxOptionOrderDto) {
            CheckboxOptionOrderDto checkboxOptionOrderDto = (CheckboxOptionOrderDto) optionOrder;
            return checkboxOptionOrderDto.getOptionTitle();
        } else if (optionOrder instanceof ValueOptionOrderDto) {
            ValueOptionOrderDto valueOptionOrderDto = (ValueOptionOrderDto) optionOrder;
            return String.format("%s (%sx)",
                    valueOptionOrderDto.getOptionTitle(),
                    valueOptionOrderDto.getValue());
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype of %s: %s",
                    OptionOrderDto.class.getName(),
                    optionOrder.getClass().getName()
            ));
        }
    }


}
