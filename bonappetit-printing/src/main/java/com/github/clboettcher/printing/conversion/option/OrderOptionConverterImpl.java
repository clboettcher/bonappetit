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
package com.github.clboettcher.printing.conversion.option;

import com.github.clboettcher.bonappetit.domain.order.OptionOrder;
import com.github.clboettcher.printing.entity.OrderOptionStrings;

import java.util.Set;

/**
 * Default impl of {@link OrderOptionConverter}.
 */
public class OrderOptionConverterImpl implements OrderOptionConverter {

    @Override
    public OrderOptionStrings convert(Set<OptionOrder> orderOptions) {
        throw new UnsupportedOperationException("Not yet implemented: convert to strings"); // TODO implement: convert to strings
//        final OrderOptionStrings result = new OrderOptionStrings();
//        if (CollectionUtils.isEmpty(orderOptions)) {
//            return result;
//        }
//
//        Set<OrderOption> orderOptionsInput = Sets.newHashSet(orderOptions);
//
//        Set<String> emphasisedOptions = Sets.newHashSet();
//        Set<String> defaultOptions = Sets.newHashSet();
//
//        for (OrderOption orderOption : orderOptionsInput) {
//            if (orderOption instanceof RadioOrderOption) {
//                RadioOrderOption radioOrderOption = (RadioOrderOption) orderOption;
//                final RadioItem selectedItem = radioOrderOption.getSelectedItem();
//                switch (selectedItem.getPrintStrategy()) {
//                    case EMPHASISE:
//                        emphasisedOptions.add(selectedItem.getName());
//                        break;
//                    case DEFAULT:
//                        defaultOptions.add(selectedItem.getName());
//                        break;
//                    case NOT_PRINTED:
//                        // Nothing to do
//                        break;
//                    default:
//                        throw new IllegalArgumentException(String.format("Unknown print strategy: %s", selectedItem.getPrintStrategy()));
//                }
//            } else if (orderOption instanceof CheckboxOrderOption) {
//                CheckboxOrderOption checkboxOrderOption = (CheckboxOrderOption) orderOption;
//                if (!checkboxOrderOption.getChecked()) {
//                    // Unchecked checkbox options are never printed.
//                    continue;
//                }
//
//                final CheckboxOption checkboxOption = checkboxOrderOption.getOption();
//                switch (checkboxOption.getPrintStrategy()) {
//                    case EMPHASISE:
//                        emphasisedOptions.add(checkboxOption.getName());
//                        break;
//                    case DEFAULT:
//                        defaultOptions.add(checkboxOption.getName());
//                        break;
//                    case NOT_PRINTED:
//                        // Nothing to do
//                        break;
//                    default:
//                        throw new IllegalArgumentException(String.format("Unknown print strategy: %s", checkboxOption.getPrintStrategy()));
//                }
//            } else if (orderOption instanceof IntegerOrderOption) {
//                IntegerOrderOption integerOrderOption = (IntegerOrderOption) orderOption;
//                if (integerOrderOption.getValue() == 0) {
//                    // Integer options with value == 0 are never printed.
//                    continue;
//                }
//                String optionString = String.format("%s (%sx)",
//                        integerOrderOption.getOption().getName(),
//                        integerOrderOption.getValue());
//                switch (integerOrderOption.getOption().getPrintStrategy()) {
//                    case EMPHASISE:
//                        emphasisedOptions.add(optionString);
//                        break;
//                    case DEFAULT:
//                        defaultOptions.add(optionString);
//                        break;
//                    case NOT_PRINTED:
//                        // Nothing to do
//                        break;
//                    default:
//                        throw new IllegalArgumentException(String.format("Unknown print strategy: %s", integerOrderOption.getOption().getPrintStrategy()));
//                }
//            } else {
//                throw new IllegalArgumentException(String.format("Unknown order option type: \"%s\"", orderOption.getClass().getName()));
//            }
//        }
//
//        result.setEmphasisedOptions(emphasisedOptions);
//        result.setDefaultOptions(defaultOptions);
//        return result;
    }
}
