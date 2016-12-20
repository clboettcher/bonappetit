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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link SpecialCharEncoder} that encodes german characters
 * for the CITIZEN CT-S 310 II printer.
 */
@Component
public class SpecialCharEncoderCITIZENCT310IIGermanImpl implements SpecialCharEncoder {
    
    /**
     * {@inheritDoc}
     *
     * @see <a href="http://www.citizen-europe.com/sites/default/files/CommandReference015E_120607_4.pdf#page=92&zoom=180,76,791">http://www.citizen-europe.com/sites/default/files/CommandReference015E_120607_4.pdf#page=92&zoom=180,76,791</a>
     */
    @Override
    public String encode(String raw) {
        Preconditions.checkArgument(StringUtils.isNotBlank(raw), "raw blank");
        char[] chars = raw.toCharArray();
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
