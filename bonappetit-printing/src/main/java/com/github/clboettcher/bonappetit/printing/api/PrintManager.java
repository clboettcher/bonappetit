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
package com.github.clboettcher.bonappetit.printing.api;


import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderDto;

import javax.print.PrintException;
import java.util.List;

/**
 * Contract for the connection to the bon printer.
 */
public interface PrintManager {

    /**
     * Prints the given {@code orders}.
     *
     * @param orders The orders to print.
     * @throws PrintException If printing fails.
     */
    void print(List<ItemOrderDto> orders) throws PrintException;
}
