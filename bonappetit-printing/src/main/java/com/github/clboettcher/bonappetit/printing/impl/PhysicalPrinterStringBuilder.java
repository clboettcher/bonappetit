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

import com.google.common.base.Preconditions;

/**
 * Builds strings that can be printed using a physical device.
 */
public class PhysicalPrinterStringBuilder {

    /**
     * Enumerates possible alignments for text.
     */
    public enum Align {

        /**
         * Aligned central.
         */
        CENTER,

        /**
         * Aligned left.
         */
        LEFT
    }

    /**
     * The bean which privides control chars used to perform special
     * operations (such as cutting the paper etc).
     */
    private ControlCharProvider controlCharProvider;

    /**
     * The bean which encodes special chars from a charset.
     */
    private SpecialCharEncoder specialCharEncoder;

    /**
     * The {@link StringBuilder} used internally to create to actual string.
     */
    private StringBuilder builder;

    /**
     * Creates a new {@link PhysicalPrinterStringBuilder} instance using the given {@code controlCharProvider}.
     *
     * @param controlCharProvider see {@link #controlCharProvider}.
     * @return A new instance of {@link PhysicalPrinterStringBuilder}.
     */
    public static PhysicalPrinterStringBuilder newInstance(ControlCharProvider controlCharProvider,
                                                           SpecialCharEncoder specialCharEncoder) {
        return new PhysicalPrinterStringBuilder(controlCharProvider, specialCharEncoder);
    }

    /**
     * Constructor setting the specified properties and initializing the internal {@link StringBuilder}.
     *
     * @param controlCharProvider see {@link #controlCharProvider}.
     * @param specialCharEncoder  see {@link #specialCharEncoder}.
     */
    private PhysicalPrinterStringBuilder(ControlCharProvider controlCharProvider, SpecialCharEncoder specialCharEncoder) {
        Preconditions.checkNotNull(controlCharProvider, "controlCharProvider");
        Preconditions.checkNotNull(specialCharEncoder, "specialCharEncoder");
        this.specialCharEncoder = specialCharEncoder;
        this.controlCharProvider = controlCharProvider;

        this.builder = new StringBuilder();
        this.builder.append(this.controlCharProvider.getInit());
    }

    /**
     * Appends the given {@code string} aligned left.
     *
     * @param string The string to append.
     * @return This builder.
     */
    public PhysicalPrinterStringBuilder appendLine(String string) {
        return this.appendLine(string, Align.LEFT);
    }

    /**
     * Appends the given {@code string} aligned according to the given {@code alignment}.
     *
     * @param string    The string to append.
     * @param alignment The alignment to print the string at.
     * @return This builder.
     */
    public PhysicalPrinterStringBuilder appendLine(String string, Align alignment) {
        switch (alignment) {
            case CENTER:
                builder.append(controlCharProvider.getAlignCenterString())
                        .append(controlCharProvider.getNormalWidthNormalHeightString())
                        .append(string);
                break;
            case LEFT:
                builder.append(controlCharProvider.getAlignLeftString())
                        .append(controlCharProvider.getNormalWidthNormalHeightString())
                        .append(string);

                break;
            default:
                throw new UnsupportedOperationException(String.format("%s %s is not supported.",
                        Align.class.getSimpleName(),
                        alignment
                ));
        }
        this.appendLineFeed();
        return this;
    }

    /**
     * Appends the given {@code string} as heading.
     *
     * @param string The heading text.
     * @return This builder.
     */
    public PhysicalPrinterStringBuilder heading(String string) {
        builder.append(controlCharProvider.getAlignCenterString())
                .append(controlCharProvider.getDoubleWidthDoubleHeightString())
                .append(string);
        return this.appendLineFeed();
    }

    /**
     * Appends a control char which causes a line feed.
     *
     * @return This builder.
     */
    public PhysicalPrinterStringBuilder appendLineFeed() {
        builder.append(controlCharProvider.getLineFeedChar());
        return this;
    }

    /**
     * Appends a control char which causes the printed to partially cut the paper.
     *
     * @return This builder.
     */
    public PhysicalPrinterStringBuilder appendPartialCut() {
        builder.append(controlCharProvider.getPartialCutString());
        return this;
    }

    /**
     * Builds and returns the final string.
     *
     * @return The created string.
     */
    public String build() {
        String rawString = builder.toString();
        return specialCharEncoder.encode(rawString);
    }
}
