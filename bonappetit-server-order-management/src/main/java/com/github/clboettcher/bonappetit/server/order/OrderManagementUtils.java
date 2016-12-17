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
package com.github.clboettcher.bonappetit.server.order;

import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

public final class OrderManagementUtils {

    /**
     * No instance.
     */
    private OrderManagementUtils() {
    }

    static Predicate<ItemOrderEntity> getOrderedAtPredicate(Optional<LocalDate> orderedAtDateOpt) {
        return itemOrderEntity -> {
            if (orderedAtDateOpt.isPresent()) {
                Date orderDate = itemOrderEntity.getOrderTime();
                DateTime orderDateTime = new DateTime(orderDate);
                LocalDate orderLocalDate = orderDateTime.toLocalDate();
                return orderLocalDate.isEqual(orderedAtDateOpt.get());
            } else {
                return true;
            }
        };
    }

    static Predicate<ItemOrderEntity> getOrderedAfterPredicate(Optional<DateTime> orderedAfterTimeOpt) {
        return itemOrderEntity -> {
            if (orderedAfterTimeOpt.isPresent()) {
                Date orderDate = itemOrderEntity.getOrderTime();
                DateTime orderDateTime = new DateTime(orderDate);
                DateTime orderedAfterDateTime = orderedAfterTimeOpt.get();
                return orderDateTime.isEqual(orderedAfterDateTime) || orderDateTime.isAfter(orderedAfterDateTime);
            } else {
                return true;
            }
        };
    }

    static Predicate<ItemOrderEntity> getOrderedBeforePredicate(Optional<DateTime> orderedBeforeTimeOpt) {
        return itemOrderEntity -> {
            if (orderedBeforeTimeOpt.isPresent()) {
                Date orderDate = itemOrderEntity.getOrderTime();
                DateTime orderDateTime = new DateTime(orderDate);
                DateTime orderedBeforeDateTime = orderedBeforeTimeOpt.get();
                return orderDateTime.isEqual(orderedBeforeDateTime) || orderDateTime.isBefore(orderedBeforeDateTime);
            } else {
                return true;
            }
        };
    }
}
