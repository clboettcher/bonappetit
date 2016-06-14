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
package com.github.clboettcher.bonappetit.printing.util;

/**
 * @author Claudius B&ouml;ttcher claudius.boettcher@qaware.de
 */
public class PosUtils {

    // TODO Fix summary string
//    public static String createSummaryString(List<ItemOrder> orders, final String eventName) {
//        StringBuilder builder = new StringBuilder();
//        BigDecimal totalIncome = BigDecimal.ZERO;
//
//        ItemOrder earliest = orders.get(0);
//        ItemOrder latest = orders.get(0);
//
//        // cumulate prices of all orders & find the first and the last order in one iteration
//        for (ItemOrder order : orders) {
//            // find first and last
//            if (order.getOrderTime().compareTo(earliest.getOrderTime()) < 0) {
//                earliest = order;
//            }
//            if (order.getOrderTime().compareTo(latest.getOrderTime()) > 0) {
//                latest = order;
//            }
//
//            // calculate price
//            Item item = order.getItem();
//
//            BigDecimal orderPrice;
//            // the orders price is based on the item price or the value supplied for blank orders
//            ItemOrderDto itemOrderDto = MappingHelper.mapToItemOrderDto(order);
//            switch (item.getType()) {
//                case BLANK: // TODO: security problem: arbitrary price injection, because we use a received value for the price calculation
//                    orderPrice = PriceCalculationUtil.calculateTotalPrice(order.getOverwriteItemPrice(), itemOrderDto.getOrderOptionDtos());
//                    break;
//                default:
//                    orderPrice = PriceCalculationUtil.calculateTotalPrice(item.getPrice(), itemOrderDto.getOrderOptionDtos());
//            }
//
//            totalIncome = totalIncome.add(orderPrice);
//        }
//
//        builder.append("Zusammenfassung " + eventName + "\n");
//        builder.append("Erste Bestellung: " + DateUtils.formatDayMonthYearTime(earliest.getOrderTime()) + "\n");
//        builder.append("Letzte Bestellung: " + DateUtils.formatDayMonthYearTime(latest.getOrderTime()) + "\n");
//
//        builder.append("\n\n");
//        builder.append("Einnahmen laut BonAppetit: " + NumberFormat.getCurrencyInstance().format(totalIncome));
//        builder.append("\n\n");
//        builder.append("Tatsächliches Bargeld (ohne Wechselgeld):   _____________________________ €");
//        builder.append("\n\n");
//        builder.append("Umbuchungsbelege:                           _____________________________ €");
//        builder.append("\n\n");
//        builder.append("Summe:                                      _____________________________ €");
//        builder.append("\n\n");
//
//        // orders overview
//        builder.append("Positionen (" + orders.size() + ")\n");
//        Map<String, Integer> orderCounts = createOrderCountMap(orders);
//        for (Map.Entry<String, Integer> entry : orderCounts.entrySet()) {
//            builder.append(entry.getValue() + "x " + entry.getKey() + "\n");
//        }
//
//        builder.append("\n\n");
//        builder.append("Unterschrift KüchenmitarbeiterIn: ________________________________________");
//        builder.append("\n\n");
//        builder.append("Unterschrift ServicemitarbeiterIn: _______________________________________");
//
//
//        return builder.toString();
//    }
//
//    private static Map<String, Integer> createOrderCountMap(List<ItemOrder> orders) {
//        Map<String, Integer> result = new HashMap<>();
//        for (ItemOrder order : orders) {
//            Set<OrderOption> orderOptions = (Set<OrderOption>) InvokerHelper.invokeMethod(order, "getOrderOptions", InvokerHelper.EMPTY_ARGS);
//            String orderOptionsString = PosUtils.getOrderOptionsString(orderOptions);
//            String key = order.getItem().getName() + ("".equals(orderOptionsString) ? "" : " - " + orderOptionsString);
//            if (result.containsKey(key)) {
//                result.put(key, result.get(key).intValue() + 1);
//            } else {
//                result.put(key, Integer.valueOf(1));
//            }
//        }
//        return result;
//    }
}
