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
package com.github.clboettcher.printing.util;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Provides commonly used functionality for working with dates.
 *
 * @author Claudius Boettcher (claudius.boettcher@qaware.de)
 * @since 15.11.2014
 */
public final class DateUtils {


    private static final SimpleDateFormat DAY_MONTH_YEAR_HOUR_MINUTE = new SimpleDateFormat("dd.MM.yyyy, HH:mm 'Uhr'");
    private static final SimpleDateFormat DAY_MONTH_YEAR = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat DAY_MONTH_HOUR_MINUTE = new SimpleDateFormat("dd.MM., HH:mm 'Uhr'");
    private static final SimpleDateFormat DAY_MONTH_HOUR_MINUTE_SECOND = new SimpleDateFormat("dd.MM., HH:mm:ss 'Uhr'");

    /**
     * No instances.
     */
    private DateUtils() {}

    public static String formatDayMonthTime(GregorianCalendar calendar) {
        SimpleDateFormat fmt = DAY_MONTH_HOUR_MINUTE;
        fmt.setCalendar(calendar);
        return fmt.format(calendar.getTime());
    }

    public static String formatDayMonthYearTime(GregorianCalendar calendar) {
        SimpleDateFormat fmt = DAY_MONTH_YEAR_HOUR_MINUTE;
        fmt.setCalendar(calendar);
        return fmt.format(calendar.getTime());
    }

    public static String formatDayMonthYear(GregorianCalendar calendar) {
        SimpleDateFormat fmt = DAY_MONTH_YEAR;
        fmt.setCalendar(calendar);
        return fmt.format(calendar.getTime());
    }

    public static String formatDayMonthTimeWithSeconds(GregorianCalendar calendar) {
        SimpleDateFormat fmt = DAY_MONTH_HOUR_MINUTE_SECOND;
        fmt.setCalendar(calendar);
        return fmt.format(calendar.getTime());
    }
}
