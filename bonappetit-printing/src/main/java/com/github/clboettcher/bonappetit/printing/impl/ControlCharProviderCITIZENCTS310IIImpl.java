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

import org.springframework.stereotype.Component;

/**
 * Implementation of {@link ControlCharProvider} for the CITIZEN CT-S 310 II physical printer.
 */
@Component
public class ControlCharProviderCITIZENCTS310IIImpl implements ControlCharProvider {

    /**
     * The ascii NUL (null) char.
     */
    private static final char NULL = (char) 0;

    /**
     * The ascii SOH (start of heading) char.
     */
    private static final char START_OF_HEADING = (char) 1;

    /**
     * The ascii SOT (start of text) char.
     */
    private static final char START_OF_TEXT = (char) 2;

    /**
     * The ascii LF (NL line feed, new line) char.
     */
    private static final char LF = (char) 10;

    /**
     * The ascii DC1 (device control 1) char.
     */
    private static final char DEVICE_CONTROL_ONE = (char) 17;

    /**
     * The ascii ESC (escape) char.
     */
    private static final char ESC = (char) 27;

    /**
     * The ascii GS (group separator) char.
     */
    private static final char GROUP_SEPARATOR = (char) 29;

    /**
     * The control chars that select the german charset.
     */
    private static final String SELECT_CHARSET_GER = ESC + "R" + START_OF_TEXT;

    /**
     * The control chars that initialize the printer.
     * // TODO Is all of this really required? Decide. Then document or remove.
     */
    private static final String INIT = ESC + "@" + LF + SELECT_CHARSET_GER;

    /**
     * The control chars that cause the printer to print center aligned.
     */
    private static final String ALIGN_CENTER = ESC + "a" + START_OF_HEADING;

    /**
     * The control chars that cause the printer to print left aligned.
     */
    private static final String ALIGN_LEFT = ESC + "a" + NULL;

    /**
     * The control chars that cause the printer to partially cut the paper.
     */
    private static final String PARTIAL_CUT = ESC + "m";

    /**
     * The control chars that cause the printer to print with the normal with and height.
     */
    private static final String NORMAL_WIDTH_NORMAL_HEIGHT = GROUP_SEPARATOR + "!" + NULL;

    /**
     * The control chars that cause the printer to print with double width and double height.
     */
    private static final String DOUBLE_WIDTH_DOUBLE_HEIGHT = GROUP_SEPARATOR + "!" + DEVICE_CONTROL_ONE;

    @Override
    public String getInit() {
        return INIT;
    }

    @Override
    public String getAlignCenterString() {
        return ALIGN_CENTER;
    }

    @Override
    public String getDoubleWidthDoubleHeightString() {
        return DOUBLE_WIDTH_DOUBLE_HEIGHT;
    }

    @Override
    public String getLineFeedChar() {
        return String.valueOf(LF);
    }

    @Override
    public String getAlignLeftString() {
        return ALIGN_LEFT;
    }

    @Override
    public String getNormalWidthNormalHeightString() {
        return NORMAL_WIDTH_NORMAL_HEIGHT;
    }

    @Override
    public String getPartialCutString() {
        return PARTIAL_CUT;
    }
}
