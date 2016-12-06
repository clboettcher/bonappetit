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


package com.github.clboettcher.bonappetit.printing.printing;

/**
 * @author Claudius B&ouml;ttcher claudius.boettcher@qaware.de
 */
public class PhysicalPrinterTest {
    // TODO  repair
//
//    private BonStringConverter printer = new BonStringConverterImpl(new ControlCharProviderConsoleTestImpl(), specialCharEncoder);
//
//    @Test
//    public void testPrinting() throws Exception {
//        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
//        // 02.12.2015 11:22:33 Uhr
//        calendar.set(Calendar.YEAR, 2015);
//        calendar.set(Calendar.DAY_OF_MONTH, 2);
//        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
//        calendar.set(Calendar.HOUR_OF_DAY, 11);
//        calendar.set(Calendar.MINUTE, 22);
//        calendar.set(Calendar.SECOND, 33);
//
//        Bon b = new Bon();
//        b.setItemTitle("Item-Name");
//        b.setNote("Note");
//        b.setItemType(ItemType.DRINK_ALCOHOLIC);
//        b.setCustomer("Customer");
//        b.setOrderTime(calendar);
//        b.setEmphasisedOptions(Sets.newHashSet("b-emph", "c-emph", "a-emph"));
//        b.setDefaultOptions(Sets.newHashSet("b-def", "c-def", "a-def"));
//        b.setStaffMemberName("Staff-Member");
//
//        final String s = printer.toString(Sets.newHashSet(b));
//        StringBuilder expected = new StringBuilder();
//        expected.append("Kunde: Customer").append("\n")
//                .append("Item-Name a-emph b-emph c-emph").append("\n")
//                .append("a-def, b-def, c-def").append("\n")
//                .append("Bemerkung: Note").append("\n")
//                .append("\n")
//                .append("Bedienung: Staff-Member, 02.12., 11:22:33 Uhr").append("\n")
//                .append("\n")
//                .append("--").append("\n")
//        ;
//
//        assertEquals(expected.toString(), s);
//    }
}
