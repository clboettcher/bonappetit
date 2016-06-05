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

/**
 * Builds strings that can be sent to the CITIZEN CT-S 310 II bon printer.
 */
public class BonStringBuilder {

    private ControlCharProvider controlCharProvider;
    private StringBuilder builder = new StringBuilder();

    public static BonStringBuilder createBuilder(ControlCharProvider controlCharProvider) {
        return new BonStringBuilder(controlCharProvider);
    }

    private BonStringBuilder(ControlCharProvider controlCharProvider) {
        this.controlCharProvider = controlCharProvider;
        builder.append(this.controlCharProvider.getInit());
    }

    public BonStringBuilder appendLine(String s) {
        builder.append(controlCharProvider.getAlignLeftString())
                .append(s)
                .append("\n");
        return this;
    }

    public BonStringBuilder alignCenter(String s) {
        builder.append(controlCharProvider.getAlignCenterString())
                .append(s)
                .append(controlCharProvider.getAlignLeftString())
                .append("\n");
        return this;
    }

    public BonStringBuilder doubleWidthDoubleHeight(String s) {
        builder.append(controlCharProvider.getDoubleWidthDoubleHeightString())
                .append(s)
                .append(controlCharProvider.getNormalWidthNormalHeightString())
                .append("\n");
        return this;
    }

    public BonStringBuilder doubleWidthDoubleHeightAlignCenter(String s) {
        builder.append(controlCharProvider.getDoubleWidthDoubleHeightString())
                .append(controlCharProvider.getAlignCenterString())
                .append(s)
                .append(controlCharProvider.getAlignLeftString())
                .append(controlCharProvider.getNormalWidthNormalHeightString())
                .append("\n");
        return this;
    }

    public BonStringBuilder lineFeed() {
        builder.append(controlCharProvider.getLineFeedChar());
        return this;
    }

    public BonStringBuilder newline() {
        builder.append("\n");
        return this;
    }

    public BonStringBuilder partialCut() {
        builder.append(controlCharProvider.getPartialCutString());
        return this;
    }

    public String build() {
        return controlCharProvider.encodeGermanChars(builder.toString());
    }
}
