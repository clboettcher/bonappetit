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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Provides commonly used functionality for working with dates.
 */
public final class DateUtils {

    /**
     * Format for day month year hour minute.
     */
    private static final DateTimeFormatter DAY_MONTH_YEAR_HOUR_MINUTE = DateTimeFormat.forPattern("dd.MM.yyyy, HH:mm 'Uhr'");

    /**
     * Format for day month year.
     */
    private static final DateTimeFormatter DAY_MONTH_YEAR = DateTimeFormat.forPattern("dd.MM.yyyy");

    /**
     * Format for day month hour minute.
     */
    private static final DateTimeFormatter DAY_MONTH_HOUR_MINUTE = DateTimeFormat.forPattern("dd.MM., HH:mm 'Uhr'");

    /**
     * Format for day month hour minute second.
     */
    private static final DateTimeFormatter DAY_MONTH_HOUR_MINUTE_SECOND = DateTimeFormat.forPattern("dd.MM., HH:mm:ss 'Uhr'");

    /**
     * No instances.
     */
    private DateUtils() {
    }

    /**
     * @param dateTime A {@link DateTime}.
     * @return The date time as string following the format {@link #DAY_MONTH_HOUR_MINUTE}.
     */
    public static String formatDayMonthTime(DateTime dateTime) {
        return dateTime.toString(DAY_MONTH_HOUR_MINUTE);
    }

    /**
     * @param dateTime A {@link DateTime}.
     * @return The date time as string following the format {@link #DAY_MONTH_YEAR_HOUR_MINUTE}.
     */
    public static String formatDayMonthYearTime(DateTime dateTime) {
        return dateTime.toString(DAY_MONTH_YEAR_HOUR_MINUTE);
    }

    /**
     * @param dateTime A {@link DateTime}.
     * @return The date time as string following the format {@link #DAY_MONTH_YEAR}.
     */
    public static String formatDayMonthYear(DateTime dateTime) {
        return dateTime.toString(DAY_MONTH_YEAR);
    }

    /**
     * @param dateTime A {@link DateTime}.
     * @return The date time as string following the format {@link #DAY_MONTH_HOUR_MINUTE_SECOND}.
     */
    public static String formatDayMonthTimeWithSeconds(DateTime dateTime) {
        return dateTime.toString(DAY_MONTH_HOUR_MINUTE_SECOND);
    }
}
