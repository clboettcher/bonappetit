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
package com.github.clboettcher.printing.printing;

public interface ControlCharProvider {
    String getInit();

    String getAlignCenterString();

    String getDoubleWidthDoubleHeightString();

    String getLineFeedChar();

    String getAlignLeftString();

    String getNormalWidthNormalHeightString();

    String getPartialCutString();

    /**
     * Encodes special characters from the german charset.
     *
     * @param toReplace A string to replace the german characters in, may be empty.
     * @return The input string with replaced german special characters, empty if {@code toReplace} is empty.
     */
    String encodeGermanChars(String toReplace);
}
