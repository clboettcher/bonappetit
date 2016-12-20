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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Provides commonly used functionality for working with dates.
 */
public class DateFormatter {

    /**
     * Format for day month year hour minute.
     */
    private static final DateTimeFormatter DAY_MONTH_YEAR_HOUR_MINUTE = DateTimeFormat.forPattern("dd.MM.yyyy, HH:mm 'Uhr'");

    /**
     * Short format for day month hour minute.
     */
    private static final DateTimeFormatter DAY_MONTH_HOUR_MINUTE_SHORT = DateTimeFormat.forPattern("dd.MM., HH:mm");

    private DateTimeZone timeZone;

    public DateFormatter(DateTimeZone dateTimeZone) {
        this.timeZone = dateTimeZone;
    }

    /**
     * @param dateTime A {@link DateTime}.
     * @return The date time as string following the format {@link #DAY_MONTH_HOUR_MINUTE_SHORT}.
     */
    public String formatDayMonthHourMinuteShort(DateTime dateTime) {
        return dateTime.toDateTime(timeZone).toString(DAY_MONTH_HOUR_MINUTE_SHORT);
    }

    /**
     * @param dateTime A {@link DateTime}.
     * @return The date time as string following the format {@link #DAY_MONTH_YEAR_HOUR_MINUTE}.
     */
    public String formatDayMonthYearTime(DateTime dateTime) {
        return dateTime.toDateTime(timeZone).toString(DAY_MONTH_YEAR_HOUR_MINUTE);
    }
}
