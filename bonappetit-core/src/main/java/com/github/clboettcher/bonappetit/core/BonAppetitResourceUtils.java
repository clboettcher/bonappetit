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
package com.github.clboettcher.bonappetit.core;

import java.io.IOException;
import java.io.InputStream;

/**
 * Common I/O functionality for easy access to resources.
 */
public final class BonAppetitResourceUtils {

    /**
     * No instances.
     */
    private BonAppetitResourceUtils() {
    }

    /**
     * Read file from resources as string using UTF 8 encoding.
     *
     * @param fileName File name.
     * @return File content.
     * @throws IOException If file could not be read.
     */
    public static String readFileContentAsString(String fileName) throws IOException {
        try (InputStream stream = openResource(fileName)) {
            return org.apache.commons.io.IOUtils.toString(stream, "UTF-8");
        }
    }

    /**
     * Open stream to file in resources.
     *
     * @param fileName File name.
     * @return Stream. Has to be closed by caller.
     * @throws IOException If file could not be read.
     */
    public static InputStream openResource(String fileName) throws IOException {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (stream == null) {
            throw new IllegalArgumentException(String.format("Resource '%s' not found", fileName));
        }
        return stream;
    }
}