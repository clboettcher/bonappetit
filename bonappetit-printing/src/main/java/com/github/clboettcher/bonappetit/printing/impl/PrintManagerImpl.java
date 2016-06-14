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

import com.github.clboettcher.bonappetit.domain.order.ItemOrder;
import com.github.clboettcher.bonappetit.printing.api.PrintManager;
import com.github.clboettcher.bonappetit.printing.conversion.BonConverter;
import com.github.clboettcher.bonappetit.printing.entity.Bon;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.PrintException;
import java.util.Set;

/**
 * Default impl of {@link PrintManager}.
 */
public class PrintManagerImpl implements PrintManager {

    /**
     * Bean that converts {@link ItemOrder}s to {@link Bon}s.
     */
    private BonConverter bonConverter;

    /**
     * Bean that converts {@link Bon}s to string.
     */
    private BonStringConverter bonStringConverter;

    /**
     * Bean that wraps talking to the physical printer.
     */
    private PhysicalPrinter physicalPrinter;

    /**
     * Constructor setting the specified properties.
     *
     * @param bonConverter    see {@link #bonConverter}.
     * @param bonStringConverter      see {@link #bonStringConverter}.
     * @param physicalPrinter see {@link #physicalPrinter}.
     */
    @Autowired
    public PrintManagerImpl(BonConverter bonConverter, BonStringConverter bonStringConverter, PhysicalPrinter physicalPrinter) {
        this.bonConverter = bonConverter;
        this.bonStringConverter = bonStringConverter;
        this.physicalPrinter = physicalPrinter;
    }

    @Override
    public void print(Set<ItemOrder> orders) throws PrintException {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(orders), "orders empty");
        Set<Bon> bons = bonConverter.toBons(orders);
        String output = bonStringConverter.toString(bons);
        physicalPrinter.print(output);
    }

    // TODO repair
//    /**
//     * // TODO THIS IS A FUCKING HACK; PLEASE FUTURE ME; REPAIR IT!!!
//     *
//     * @param orders
//     * @return
//     */
//    private String toSummaryString(Set<ItemOrder> orders) {
//        BonStringBuilder builder = BonStringBuilder.newInstance(new ControlCharProviderCITIZENCTS310IIImpl());
//
//        PriceCalculator priceCalculator = new PriceCalculatorImpl();
//        builder.doubleWidthDoubleHeight("Alle Bestellungen");
//        builder.doubleWidthDoubleHeight(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
//        builder.newline();
//        BigDecimal grandTotal = BigDecimal.ZERO;
//        for (ItemOrder order : orders) {
    // // TODO MappingHelper has been removed since we dont need separate entities for price calcualtion anymore
//            final ItemOrderPrices orderPrices = de.bonappetit.posprinter.util.MappingHelper.mapToItemOrderForPriceCalculation(order);
//            final BigDecimal totalPrice = priceCalculator.calculateTotalPrice(orderPrices);
//            grandTotal = grandTotal.add(totalPrice);
//            builder.appendLine(String.format("%s %s %s EUR", order.getItem().getName(), getEmphasisedOptionsString(order), totalPrice));
//            builder.appendLine("---");
//        }
//        builder.newline();
//        builder.appendLine(String.format("Total: %s EUR", grandTotal));
//        builder.appendLineFeed().appendLineFeed().newline().appendPartialCut();
//        return builder.build();
//    }
//
//    private String getEmphasisedOptionsString(ItemOrder order) {
//        List<String> emph = new ArrayList<>();
//        for (OrderOption orderOption : (Set<OrderOption>)InvokerHelper.invokeMethod(order, "getOrderOptions", InvokerHelper.EMPTY_ARGS)) {
//            if (orderOption instanceof IntegerOrderOption) {
//                IntegerOrderOption integerOrderOption = (IntegerOrderOption) orderOption;
//                if (integerOrderOption.getValue() > 0 && integerOrderOption.getOption().getPrintStrategy() == PrintStrategy.EMPHASISE) {
//                    emph.add(integerOrderOption.getOption().getName());
//                }
//            } else if (orderOption instanceof CheckboxOrderOption) {
//                CheckboxOrderOption checkboxOrderOption = (CheckboxOrderOption) orderOption;
//                if (checkboxOrderOption.getChecked() && checkboxOrderOption.getOption().getPrintStrategy() == PrintStrategy.EMPHASISE) {
//                    emph.add(checkboxOrderOption.getOption().getName());
//                }
//            } else if (orderOption instanceof RadioOrderOption) {
//                RadioOrderOption radioOrderOption = (RadioOrderOption) orderOption;
//                if (radioOrderOption.getSelectedItem().getPrintStrategy() == PrintStrategy.EMPHASISE) {
//                    emph.add(radioOrderOption.getSelectedItem().getName());
//                }
//            } else {
//                throw new IllegalStateException("Unsup option type: " + orderOption.getClass().getName());
//            }
//        }
//
//        return Joiner.on(" ").join(emph);
//    }
}

