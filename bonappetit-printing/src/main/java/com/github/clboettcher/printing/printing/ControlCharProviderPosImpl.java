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
 * @author Claudius B&ouml;ttcher claudius.boettcher@qaware.de
 */
public class ControlCharProviderPosImpl implements ControlCharProvider {
    private static final char ONE = (char) 1;
    private static final char TWO = (char) 2;
    private static final char ZERO = (char) 0;
    private static final char ONEONE = (char) 17;
    private static final char LF = (char) 10;
    private static final char ESC = (char) 27;
    private static final String INIT_PRINTER = ESC + "@";
    private static final String SELECT_CHARSET_GER = ESC + "R" + TWO;
    private static final String INIT = INIT_PRINTER + "\n" + SELECT_CHARSET_GER;
    private static final String ALIGN_CENTER = ESC + "a" + ONE;
    private static final String ALIGN_LEFT = ESC + "a" + ZERO;
    private static final String PARTIAL_CUT = ESC + "m";
    private static final char GS = (char) 29;
    private static final String NORMAL_WIDTH_NORMAL_HEIGHT = GS + "!" + ZERO;
    private static final String DOUBLE_WIDTH_DOUBLE_HEIGHT = GS + "!" + ONEONE;

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

    /**
     * {@inheritDoc}
     *
     * @see <a href="http://www.citizen-europe.com/sites/default/files/CommandReference015E_120607_4.pdf#page=92&zoom=180,76,791">http://www.citizen-europe.com/sites/default/files/CommandReference015E_120607_4.pdf#page=92&zoom=180,76,791</a>
     */
    @Override
    public String encodeGermanChars(String toReplace) {
        char[] chars = toReplace.toCharArray();
        StringBuilder newChars = new StringBuilder(chars.length);
        for (char aChar : chars) {
            switch (aChar) {
                case '#':
                    newChars.append((char) 0x23);
                    break;
                case '$':
                    newChars.append((char) 0x24);
                    break;
                case '§':
                    newChars.append((char) 0x40);
                    break;
                case 'Ä':
                    newChars.append((char) 0x5B);
                    break;
                case 'Ö':
                    newChars.append((char) 0x5C);
                    break;
                case 'Ü':
                    newChars.append((char) 0x5D);
                    break;
                case '^':
                    newChars.append((char) 0x5E);
                    break;
                case '\'':
                    newChars.append((char) 0x60);
                    break;
                case 'ä':
                    newChars.append((char) 0x7B);
                    break;
                case 'ö':
                    newChars.append((char) 0x7C);
                    break;
                case 'ü':
                    newChars.append((char) 0x7D);
                    break;
                case 'ß':
                    newChars.append((char) 0x7E);
                    break;
                default:
                    newChars.append(aChar);
            }
        }
        return newChars.toString();
    }
}
