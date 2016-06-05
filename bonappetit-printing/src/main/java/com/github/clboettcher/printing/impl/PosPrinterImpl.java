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
package com.github.clboettcher.printing.impl;

import com.github.clboettcher.bonappetit.domain.order.ItemOrder;
import com.github.clboettcher.printing.api.PosPrinter;
import com.github.clboettcher.printing.conversion.item.ItemOrderConverter;
import com.github.clboettcher.printing.entity.Bon;
import com.github.clboettcher.printing.printing.BonPrinter;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

public class PosPrinterImpl implements PosPrinter {

    private ItemOrderConverter itemOrderConverter;
    private BonPrinter bonPrinter;

    public PosPrinterImpl(ItemOrderConverter itemOrderConverter, BonPrinter bonPrinter) {
        this.itemOrderConverter = itemOrderConverter;
        this.bonPrinter = bonPrinter;
    }

    @Override
    public void printOrders(Set<ItemOrder> orders) throws PrintException {
        DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        Set<Bon> bons = itemOrderConverter.toBonList(orders);
        String s = bonPrinter.toPrintableString(bons);
        InputStream inputStream = new ByteArrayInputStream(s.getBytes());
        Doc doc = new SimpleDoc(inputStream, docFlavor, null);

        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println(String.format("Trying to print to service \"%s\"", printService.getName()));

        DocPrintJob job = printService.createPrintJob();
        job.print(doc, null);
    }

    @Override
    public void printSummary(Set<ItemOrder> orders, String eventName) throws PrintException {
        throw new UnsupportedOperationException("Not yet implemented: repair"); // TODO implement: repair
//        DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

//        String s = toSummaryString(orders);
//        InputStream inputStream = new ByteArrayInputStream(s.getBytes());
//        Doc doc = new SimpleDoc(inputStream, docFlavor, null);
//
//        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
//        System.out.println(String.format("Trying to print to service \"%s\"", printService.getName()));
//
//        DocPrintJob job = printService.createPrintJob();
//        job.print(doc, null);
    }

// TODO repair
//    /**
//     * // TODO THIS IS A FUCKING HACK; PLEASE FUTURE ME; REPAIR IT!!!
//     *
//     * @param orders
//     * @return
//     */
//    private String toSummaryString(Set<ItemOrder> orders) {
//        BonStringBuilder builder = BonStringBuilder.createBuilder(new ControlCharProviderPosImpl());
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
//        builder.lineFeed().lineFeed().newline().partialCut();
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

