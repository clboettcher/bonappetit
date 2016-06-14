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

public interface ControlCharProvider {

    /**
     * @return The control chars to initialize the printer.
     */
    String getInit();

    /**
     * @return The control chars that cause the printer to print center aligned.
     */
    String getAlignCenterString();

    /**
     * Returns the control chars chars that cause the printer to print with double width and double height.
     * <p>
     * Note, that the printer must be reset to normal width and height manually when you are done.
     *
     * @return The control chars.
     * @see #getDoubleWidthDoubleHeightString()
     */
    String getDoubleWidthDoubleHeightString();

    /**
     * @return The control char which causes the printer to perform a line feed.
     */
    String getLineFeedChar();

    /**
     * @return The control chars which cause the printer to print left aligned.
     */
    String getAlignLeftString();

    /**
     * @return The control chars which cause the printer to print with normal width and height.
     * @see #getDoubleWidthDoubleHeightString()
     */
    String getNormalWidthNormalHeightString();

    /**
     * @return The control chars that cause the printer to cut the paper partially.
     */
    String getPartialCutString();
}
