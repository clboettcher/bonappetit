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

import com.github.clboettcher.bonappetit.printing.api.PrintManager;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.SummaryDto;

import javax.print.PrintException;
import java.util.List;

public class PrintManagerConsoleTestImpl implements PrintManager {

    @Override
    public void print(List<ItemOrderDto> orders) throws PrintException {
        throw new UnsupportedOperationException("Not yet implemented: print orders"); // TODO implement: print orders
    }

    @Override
    public void print(SummaryDto summary) throws PrintException {
        throw new UnsupportedOperationException("Not yet implemented: print summary"); // TODO implement: print summary
    }

    // TODO repair
//    private final ItemOrderConverter itemOrderConverter;
//    private final PhysicalPrinter physicalPrinter;
//
//    public PrintManagerConsoleTestImpl(ItemOrderConverter itemOrderConverter, PhysicalPrinter physicalPrinter) {
//        this.itemOrderConverter = itemOrderConverter;
//        this.physicalPrinter = physicalPrinter;
//    }
//
//    @Override
//    public void printOrders(Set<ItemOrder> orders) throws PrintException {
//        Set<Bon> bons = itemOrderConverter.toBonList(orders);
//        String s = physicalPrinter.toPrintableString(bons);
//        System.out.println(s);
//    }
//
//    @Override
//    public void printSummary(Set<ItemOrder> orders, String eventName) {
//        throw new UnsupportedOperationException();
//    }
}
