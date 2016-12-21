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

import com.github.clboettcher.bonappetit.server.order.entity.OrderEntityStatus;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderManagementParamParser {

    Optional<DateTime> parseOrderedBound(String orderedBound, String paramName) {
        if (StringUtils.isBlank(orderedBound)) {
            return Optional.empty();
        }

        if ("today".equalsIgnoreCase(StringUtils.trimToEmpty(orderedBound))) {
            return Optional.of(DateTime.now(DateTimeZone.UTC).withTimeAtStartOfDay());
        }

        try {
            return Optional.of(DateTime.parse(orderedBound));
        } catch (Exception e) {
            throw new BadRequestException(String.format("Param %s with value %s was invalid: %s",
                    paramName,
                    orderedBound,
                    e.getMessage()
            ));
        }
    }

    Optional<LocalDate> parseOrderedAt(String orderedAt, String paramName) {
        if (StringUtils.isBlank(orderedAt)) {
            return Optional.empty();
        }

        if ("today".equalsIgnoreCase(StringUtils.trimToEmpty(orderedAt))) {

            LocalDate now = LocalDate.now(DateTimeZone.UTC);
            return Optional.of(now);
        }

        try {
            return Optional.of(LocalDate.parse(orderedAt));
        } catch (Exception e) {
            throw new BadRequestException(String.format("Param %s with value %s was invalid: %s",
                    paramName,
                    orderedAt,
                    e.getMessage()
            ));
        }
    }

    public EnumSet<OrderEntityStatus> parseToOrderEntityStatus(Optional<String> status) {
        if (!status.isPresent()) {
            return EnumSet.allOf(OrderEntityStatus.class);
        } else {
            List<String> statusStrings = Splitter.on(',')
                    .omitEmptyStrings()
                    .trimResults()
                    .splitToList(status.get());
            Set<OrderEntityStatus> statusEnums = statusStrings.stream()
                    .map((name) -> {
                        try {
                            return OrderEntityStatus.valueOf(name);
                        } catch (Exception e) {
                            throw new BadRequestException(String.format("Invalid param status with value '%s': %s",
                                    status.get(),
                                    e.getMessage()));
                        }
                    })

                    .collect(Collectors.toSet());
            return EnumSet.copyOf(statusEnums);
        }
    }
}
